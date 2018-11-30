package by.htp.kirova.task2.dao;

import by.htp.kirova.task2.dao.connectionpool.ConnectionPoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public interface ConnectionPool {

    /**
     * Extract one of connections from pool.
     *
     * @return available connection from the connection pool.
     * @throws ConnectionPoolException
     */
    Connection getConnection() throws ConnectionPoolException;

    /**
     * Returns one of connections to Connection pool.
     *
     * @param connection Connection class object
     */
    void releaseConnection(Connection connection);


    /**
     * Public method closing all connections in using Collections queue
     * and avaliable Collections queue.
     */
    void closeConnectionPool();

    /*
     * And some methods to work with connection:
     * */


    /**
     * Close statement after use. When a Statement object is closed,
     * its current ResultSet object, if one exists, is also closed.
     *
     * @param st Statement object
     */
    void closeStatement(Statement st);


    /**
     * Close prepared statement after use.  When a Prepared Statement
     * object is closed, its current ResultSet object, if one exists,
     * is also closed.
     *
     * @param ps Prepared Statement object
     */
    void closePreparedStatement(PreparedStatement ps);


    /**
     * Set autocommit flag is {@code true} and return connection in pool.
     *
     * @param connection current connection
     */
    void setAutoCommitTrue(Connection connection);

    /**
     * Rollback connection in case of unsuccessful completion of the transaction.
     *
     * @param connection current connection
     */
    void rollbackConnection(Connection connection);
}
