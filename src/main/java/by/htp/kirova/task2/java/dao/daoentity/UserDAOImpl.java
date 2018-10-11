package by.htp.kirova.task2.java.dao.daoentity;


import by.htp.kirova.task2.java.connectionpool.ConnectionPool;
import by.htp.kirova.task2.java.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.dao.GenericDAO;
import by.htp.kirova.task2.java.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Provides User with an opportunity to retrieve, change and delete data from
 * the relevant database table.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class UserDAOImpl implements GenericDAO<User> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class);

    /**
     * Constant string which represents query to create user.
     */
    private static final String SQL_CREATE_USER = "INSERT INTO `users`(`username`, `email`, `password`, `first_name`, " +
            "`last_name`, `middle_name`, `enable`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', %b)";

    /**
     * Constant string which represents query to select all users.
     */
    private static final String SQL_SELECT_FROM_USERS = "SELECT * FROM `users` ";

    /**
     * Constant string which represents query to update user.
     */
    private static final String SQL_UPDATE_USER = "UPDATE `users` SET `email`= '%s',`password`= '%s',`first_name`= '%s'," +
            "`last_name`= '%s',`middle_name`= '%s',`enable`= '%s' WHERE `username`= '%s'";

    /**
     * Constant string which represents query to delete user.
     */
    private static final String SQL_DELETE_USER = "DELETE FROM `users` WHERE `username`='%s'";



    @Override
    public boolean create(User user) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = String.format(Locale.US, SQL_CREATE_USER, user.getUsername(), user.getEmail(), user.getPassword(),
                user.getFirst_name(), user.getLast_name(), user.getMiddle_name(), user.isEnable());

        int result;

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            result = executeUpdate(connection, sql);

            connection.setAutoCommit(false);
            connection.commit();

        } catch (ConnectionPoolException | SQLException e) {
            rollbackConnection(connection);
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

        } finally {
            setAutoCommitTrueAndReturnConnection(cp, connection);
        }

        return result == 1;
    }

    @Override
    public List<User> read(String where) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = SQL_SELECT_FROM_USERS + where;

        List<User> list = new ArrayList<>();

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new User(
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("middle_name"),
                        resultSet.getBoolean("enable")
                ));
            }

        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

        } finally {
            if (connection != null) {
                cp.returnConnection(connection);
            }
        }

        return list;
    }

    @Override
    public boolean update(User user) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = String.format(Locale.US, SQL_UPDATE_USER, user.getEmail(), user.getPassword(),
                user.getFirst_name(), user.getLast_name(), user.getMiddle_name(), user.isEnable(), user.getUsername());

        int result;

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            result = executeUpdate(connection, sql);

            connection.setAutoCommit(false);
            connection.commit();

        } catch (ConnectionPoolException | SQLException e) {
            rollbackConnection(connection);
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

        } finally {
            setAutoCommitTrueAndReturnConnection(cp, connection);
        }

        return result == 1;
    }

    @Override
    public boolean delete(User user) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = String.format(Locale.US, SQL_DELETE_USER, user.getUsername());

        int result;

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            result = executeUpdate(connection, sql);

        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

        } finally {
            if (connection != null) {
                cp.returnConnection(connection);
            }
        }

        return result == 1;
    }



    /**
     * Executes the given SQL statement.
     *
     * @param connection current connection
     * @param sql java.lang.String sql query
     * @return value 1 if the request is successful, 0 otherwise
     */
    private int executeUpdate(Connection connection, String sql) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
    }

    /**
     * Set autocommit flag is {@code true} and return connection in pool.
     *
     * @param cp connection pool
     * @param connection current connection
     */
    private void setAutoCommitTrueAndReturnConnection(ConnectionPool cp, Connection connection) {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                LOGGER.error("Connection set autocommit  \"true\" operation error: ", e);
            }

            cp.returnConnection(connection);
        }
    }

    /**
     * Rollback connection in case of unsuccessful completion of the transaction.
     *
     * @param connection current connection
     */
    private void rollbackConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException z) {
            LOGGER.error("Connection rollback operation error: ", z);
        }
    }
}
