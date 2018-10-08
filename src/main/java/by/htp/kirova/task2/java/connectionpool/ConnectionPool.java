package by.htp.kirova.task2.java.connectionpool;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * Provides access to Connections in the pool.
 * This class is based on Singleton pattern.
 *
 * @author Kirava Kseniya
 * @since Sep 24, 2018
 */
public final class ConnectionPool {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);


    /**
     * Instance of the class.
     */
    private static ConnectionPool instance = null;


    /**
     * Queue of available connections.
     */
    private LinkedBlockingQueue<Connection> availableConnections;

    /**
     * Queue of using connections.
     */
    private LinkedBlockingQueue<Connection> usingConnections;


    /**
     * Database driver name.
     */
    private String driverName;

    /**
     * Databse URL.
     */
    private String url;

    /**
     * Database user name.
     */
    private String user;

    /**
     * Databse password.
     */
    private String password;

    /**
     * Connection pool size.
     */
    private int poolSize;


    private ConnectionPool() throws ConnectionPoolException {
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        this.driverName = dbResourceManager.getValue(DBParameter.DB_DRIVER);
        this.url = dbResourceManager.getValue(DBParameter.DB_URL);
        this.user = dbResourceManager.getValue(DBParameter.DB_USER);
        this.password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);
        try {
            this.poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POOL_SIZE));
        } catch (NumberFormatException e) {
            poolSize = 5;
            LOGGER.error("Unexpected error of predefined data: ", e);
            throw new ConnectionPoolException("Unexpected error of predefined data", e);
        }
    }


    /**
     * Returns only one instance of the class.
     *
     * @return instance of the class.
     */
    public static ConnectionPool getInstance() throws ConnectionPoolException {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                    instance.initPoolData();
                }
            }
        }
        return instance;
    }


    /**
     * Initializes data for the connection pool.
     *
     * @throws ConnectionPoolException if can't find database
     * driver class or SQLException in ConnectionPool.
     */
    private void initPoolData() throws ConnectionPoolException {
        try {
            Class.forName(driverName);
            availableConnections = new LinkedBlockingQueue<>(poolSize);
            usingConnections = new LinkedBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                    Connection connection = DriverManager.getConnection(url, user, password);
                    PooledConnection pooledConnection = new PooledConnection(connection);
                    availableConnections.put(pooledConnection);
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error("Can't find database driver class: ", e);
            throw new ConnectionPoolException("Can't find database driver class: ", e);
        } catch (InterruptedException | SQLException e) {
            LOGGER.error("SQLException in ConnectionPool: ", e);
            throw new ConnectionPoolException("SQLException in ConnectionPool: ", e);
        }
        LOGGER.info(String.format("Connection pool for %d connections has been created successfully", poolSize));
    }


    /**
     * Extract one of connections from pool.
     *
     * @return available connection from the connection pool.
     * @throws ConnectionPoolException
     */
    public Connection extractConnection() throws ConnectionPoolException {
        Connection connection = null;
        try {
            connection = availableConnections.take();
            usingConnections.put(connection);
        } catch (InterruptedException e) {
            LOGGER.error("Error connecting to the data source: ", e);
            throw new ConnectionPoolException("Error connecting to the data source: ", e);
        }
        return connection;
    }


    /**
     * Public method closing all connections in using Collections queue
     * and avaliable Collections queue.
     *
     */
    public void clearConnectionQueue() {
        try {
            closeConnectionsQueue(usingConnections);
            closeConnectionsQueue(availableConnections);
        } catch (SQLException e) {
            LOGGER.error("Error closing the connection.", e);
        }
    }

    /**
     * Returns one of connections to Connection pool.
     *
     * @param connection Connection class object
     */
    public void returnConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Connection isn't return to the pool. ", e);
        }
    }

    /**
     * Private method Clears the queue, thus closing all connections. Method
     * additionally making the final commit, if the autocommit function was
     * turned off.
     *
     * @throws SQLException
     * @param queue is queue of connections
     */
    private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws SQLException {
        Connection connection;
        while ((connection = queue.poll()) != null) {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
            ((PooledConnection) connection).reallyClose();
        }
    }

    /**
     * Inner wrapper class for implementing a way to return a Connection back
     * to the Connection pool.
     */
    private class PooledConnection implements Connection {
        private Connection connection;

        public PooledConnection(Connection c) throws SQLException {
            this.connection = c;
            this.connection.setAutoCommit(true);
        }

        /**
         * Closes the Connection.
         *
         * @throws SQLException
         */
        public void reallyClose() throws SQLException {
            connection.close();
        }

        @Override
        public void clearWarnings() throws SQLException {
            connection.clearWarnings();
        }


        /**
         * Returns the connection to original state.
         *
         * @throws SQLException
         */
        @Override
        public void close() throws SQLException {
            if (connection.isClosed()) {
                LOGGER.error("Attempting to close closed connection.");
                throw new SQLException("Attempting to close closed connection.");
            }
            if (connection.isReadOnly()) {
                connection.setReadOnly(false);
            }
            if (!usingConnections.remove(this)) {
                LOGGER.error("Error deleting connection from the using connections pool.");
                throw new SQLException("Error deleting connection from the using connections pool.");
            }
            if (!availableConnections.offer(this)) {
                LOGGER.error("Error allocating connection in the pool.");
                throw new SQLException("Error allocating connection in the pool.");
            }
        }

        @Override
        public void commit() throws SQLException {
            connection.commit();
        }


        @Override
        public Array createArrayOf(String typeName, Object[] elements)
                throws SQLException {
            return connection.createArrayOf(typeName, elements);
        }

        @Override
        public Blob createBlob() throws SQLException {
            return connection.createBlob();
        }

        @Override
        public Clob createClob() throws SQLException {
            return connection.createClob();
        }

        @Override
        public NClob createNClob() throws SQLException {
            return connection.createNClob();
        }

        @Override
        public SQLXML createSQLXML() throws SQLException {
            return connection.createSQLXML();
        }

        @Override
        public Statement createStatement() throws SQLException {
            return connection.createStatement();
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency);
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency,
                                         int resultSetHoldability) throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
            return connection.createStruct(typeName, attributes);
        }

        @Override
        public boolean getAutoCommit() throws SQLException {
            return connection.getAutoCommit();
        }

        @Override
        public String getCatalog() throws SQLException {
            return connection.getCatalog();
        }

        @Override
        public Properties getClientInfo() throws SQLException {
            return connection.getClientInfo();
        }

        @Override
        public String getClientInfo(String name) throws SQLException {
            return connection.getClientInfo(name);
        }

        @Override
        public int getHoldability() throws SQLException {
            return connection.getHoldability();
        }

        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            return connection.getMetaData();
        }

        @Override
        public int getTransactionIsolation() throws SQLException {
            return connection.getTransactionIsolation();
        }

        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return connection.getTypeMap();
        }

        @Override
        public SQLWarning getWarnings() throws SQLException {
            return connection.getWarnings();
        }

        @Override
        public boolean isClosed() throws SQLException {
            return connection.isClosed();
        }

        @Override
        public boolean isReadOnly() throws SQLException {
            return connection.isReadOnly();
        }

        @Override
        public boolean isValid(int timeout) throws SQLException {
            return connection.isValid(timeout);
        }

        @Override
        public String nativeSQL(String sql) throws SQLException {
            return connection.nativeSQL(sql);
        }

        @Override
        public CallableStatement prepareCall(String sql) throws SQLException {
            return connection.prepareCall(sql);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.prepareCall(sql, resultSetType,
                    resultSetConcurrency);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
                                             int resultSetHoldability) throws SQLException {
            return connection.prepareCall(sql, resultSetType,
                    resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public PreparedStatement prepareStatement(String sql) throws SQLException {
            return connection.prepareStatement(sql);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
            return connection.prepareStatement(sql, autoGeneratedKeys);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
            return connection.prepareStatement(sql, columnIndexes);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
            return connection.prepareStatement(sql, columnNames);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType,
                                                  int resultSetConcurrency) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
                                                  int resultSetHoldability) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public void rollback() throws SQLException {
            connection.rollback();
        }

        @Override
        public void setAutoCommit(boolean autoCommit) throws SQLException {
            connection.setAutoCommit(autoCommit);
        }

        @Override
        public void setCatalog(String catalog) throws SQLException {
            connection.setCatalog(catalog);
        }

        @Override
        public void setClientInfo(String name, String value) throws SQLClientInfoException {
            connection.setClientInfo(name, value);
        }

        @Override
        public void setHoldability(int holdability) throws SQLException {
            connection.setHoldability(holdability);
        }

        @Override
        public void setReadOnly(boolean readOnly) throws SQLException {
            connection.setReadOnly(readOnly);
        }

        @Override
        public Savepoint setSavepoint() throws SQLException {
            return connection.setSavepoint();
        }

        @Override
        public Savepoint setSavepoint(String name) throws SQLException {
            return connection.setSavepoint(name);
        }

        @Override
        public void setTransactionIsolation(int level) throws SQLException {
            connection.setTransactionIsolation(level);
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return connection.isWrapperFor(iface);
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return connection.unwrap(iface);
        }

        @Override
        public void abort(Executor arg0) throws SQLException {
            connection.abort(arg0);
        }

        @Override
        public int getNetworkTimeout() throws SQLException {
            return connection.getNetworkTimeout();
        }

        @Override
        public String getSchema() throws SQLException {
            return connection.getSchema();
        }

        @Override
        public void releaseSavepoint(Savepoint arg0) throws SQLException {
            connection.releaseSavepoint(arg0);
        }

        @Override
        public void rollback(Savepoint arg0) throws SQLException {
            connection.rollback(arg0);
        }

        @Override
        public void setClientInfo(Properties arg0) throws SQLClientInfoException {
            connection.setClientInfo(arg0);
        }

        @Override
        public void setNetworkTimeout(Executor arg0, int arg1) throws SQLException {
            connection.setNetworkTimeout(arg0, arg1);
        }

        @Override
        public void setSchema(String arg0) throws SQLException {
            connection.setSchema(arg0);
        }

        @Override
        public void setTypeMap(Map<String, Class<?>> arg0) throws SQLException {
            connection.setTypeMap(arg0);
        }
    }
}

