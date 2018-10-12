package by.htp.kirova.task2.java.dao.daoentity;

import by.htp.kirova.task2.java.connectionpool.ConnectionPool;
import by.htp.kirova.task2.java.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.dao.GenericDAO;
import by.htp.kirova.task2.java.entity.Request;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Provides Request with an opportunity to retrieve, change and delete data from
 * the relevant database table.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class RequestDAOImpl implements GenericDAO<Request> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(RequestDAOImpl.class);

    /**
     * Constant string which represents query to create request.
     */
    private static final String SQL_CREATE_REQUEST = "INSERT INTO `requests`(`room_capacity`, `checkin_date`," +
            " `checkout_date`, `room_class`, `enable`, `users_username`) VALUES (%d, %d, %d, '%s', %b, '%s')";

    /**
     * Constant string which represents query to select all requests.
     */
    private static final String SQL_SELECT_FROM_REQUESTS = "SELECT * FROM `requests` ";

    /**
     * Constant string which represents query to update request.
     */
    private static final String SQL_UPDATE_REQUEST = "UPDATE `requests` SET `room_capacity`= %d," +
            "`checkin_date`= %d,`checkout_date`= %d,`room_class`= '%s', `enable`= %b, `users_username`='%s' WHERE `id`= %d";

    /**
     * Constant string which represents query to delete request.
     */
    private static final String SQL_DELETE_REQUEST = "DELETE FROM `requests` WHERE `id` = %d";


    @Override
    public boolean create(Request request) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = String.format(Locale.US, SQL_CREATE_REQUEST, request.getRoom_capacity(), request.getCheckin_date(),
                request.getCheckout_date(), request.getRoom_class(), request.isEnable(), request.getUsers_username());

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            int id = executeUpdate(connection, sql, true);
            if (id > 0) {
                request.setId(id);
                connection.setAutoCommit(false);
                connection.commit();
                return true;
            }

        } catch (ConnectionPoolException | SQLException e) {
            rollbackConnection(connection);
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

        } finally {
            setAutoCommitTrueAndReturnConnection(cp, connection);
        }

        return false;
    }

    @Override
    public List<Request> read(String where) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = SQL_SELECT_FROM_REQUESTS + where;

        List<Request> list = new ArrayList<>();

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new Request(
                        resultSet.getLong("id"),
                        resultSet.getInt("room_capacity"),
                        resultSet.getLong("checkin_date"),
                        resultSet.getLong("checkout_date"),
                        resultSet.getString("room_class"),
                        resultSet.getBoolean("enable"),
                        resultSet.getString("users_username")
                ));
            }

        } catch (ConnectionPoolException | SQLException e) {
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
    public boolean update(Request request) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = String.format(Locale.US, SQL_UPDATE_REQUEST, request.getRoom_capacity(), request.getCheckin_date(),
                request.getCheckout_date(), request.getRoom_class(), request.isEnable(), request.getUsers_username(),
                request.getId());

        int result;

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            result = executeUpdate(connection, sql, false);

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
    public boolean delete(Request request) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = String.format(Locale.US, SQL_DELETE_REQUEST, request.getId());

        int result;

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            result = executeUpdate(connection, sql, false);

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
     * @param generateId boolean indicating the need to generate an identification number.
     * {@code true} if it is needed, {@code false} otherwise
     * @return value 1 if the request is successful, 0 otherwise
     */
    private int executeUpdate(Connection connection, String sql, boolean generateId) throws SQLException {
        Statement statement = connection.createStatement();
        int result = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

        if (result > 0 && generateId) {
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        }

        return result;
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
                LOGGER.error("Connection set autocommit \"true\" operation error: ", e);
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
                connection.setAutoCommit(false);
                connection.rollback();
            }
        } catch (SQLException z) {
            LOGGER.error("Connection rollback operation error: ", z);
        }
    }
}
