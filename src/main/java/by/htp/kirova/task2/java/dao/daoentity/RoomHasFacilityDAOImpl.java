package by.htp.kirova.task2.java.dao.daoentity;


import by.htp.kirova.task2.java.connectionpool.ConnectionPool;
import by.htp.kirova.task2.java.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.dao.GenericDAO;
import by.htp.kirova.task2.java.entity.RoomHasFacility;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Provides Room Has Facility with an opportunity to retrieve, change and delete data from
 * the relevant database table.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class RoomHasFacilityDAOImpl implements GenericDAO<RoomHasFacility> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(RoomHasFacilityDAOImpl.class);

    /**
     * Constant string which represents query to create relations between facility and room.
     */
    private static final String SQL_CREATE_RHF = "INSERT INTO `rooms_has_facilities`(`rooms_id`, `facilities_id`," +
            " `count`, `enable`) VALUES (%d, %d, %d, %b)";

    /**
     * Constant string which represents query to select all relations between facility and room.
     */
    private static final String SQL_SELECT_FROM_RHF = "SELECT * FROM `rooms_has_facilities` ";

    /**
     * Constant string which represents query to update relations between facility and room.
     */
    private static final String SQL_UPDATE_RHF = "UPDATE `rooms_has_facilities` SET `count`= %d, `enable`= %b " +
            "WHERE `rooms_id`= %d AND `facilities_id`= %d";

    /**
     * Constant string which represents query to delete relations between facility and room.
     */
    private static final String SQL_DELETE_RHF = "DELETE FROM `rooms_has_facilities` WHERE `rooms_id`= %d AND" +
            "`facilities_id`= %d";



    @Override
    public boolean create(RoomHasFacility roomHasFacility) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = String.format(Locale.US, SQL_CREATE_RHF, roomHasFacility.getRooms_id(),
                roomHasFacility.getFacilities_id(), roomHasFacility.getCount(), roomHasFacility.isEnable());

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
            if (cp != null && connection != null) {
                setAutoCommitTrue(connection);
                cp.returnConnection(connection);
            }
        }

        return result == 1;
    }

    @Override
    public List<RoomHasFacility> read(String where) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String sql = SQL_SELECT_FROM_RHF + where;

        List<RoomHasFacility> list = new ArrayList<>();

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new RoomHasFacility(
                        resultSet.getLong("rooms_id"),
                        resultSet.getLong("facilities_id"),
                        resultSet.getInt("count"),
                        resultSet.getBoolean("enable")
                ));
            }

        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

        } finally {
            if (cp != null && connection != null) {
                cp.closeResultSet(resultSet);
                cp.closeStatement(statement);
                cp.returnConnection(connection);
            }
        }

        return list;
    }

    @Override
    public boolean update(RoomHasFacility roomHasFacility) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = String.format(Locale.US, SQL_UPDATE_RHF, roomHasFacility.getCount(), roomHasFacility.isEnable(),
                roomHasFacility.getRooms_id(), roomHasFacility.getFacilities_id());

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
            if (cp != null && connection != null) {
                setAutoCommitTrue(connection);
                cp.returnConnection(connection);
            }
        }

        return result == 1;
    }

    @Override
    public boolean delete(RoomHasFacility roomHasFacility) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = String.format(Locale.US, SQL_DELETE_RHF, roomHasFacility.getRooms_id(),
                roomHasFacility.getFacilities_id());

        int result;

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            result = executeUpdate(connection, sql);

        } catch (ConnectionPoolException  e) {
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

        } finally {
            if (cp != null && connection != null) {
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
    private int executeUpdate(Connection connection, String sql) {
        int result = 0;

        try (Statement statement = connection.createStatement()) {
            result = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

        } catch (SQLException e) {
            LOGGER.error("ConnectionPool error: ", e);

        }
        return result;
    }


    /**
     * Set autocommit flag is {@code true} and return connection in pool.
     *
     * @param connection current connection
     */
    private void setAutoCommitTrue(Connection connection) {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            LOGGER.error("Connection set autocommit  \"true\" operation error: ", e);
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
