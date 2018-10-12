package by.htp.kirova.task2.java.dao.daoentity;

import by.htp.kirova.task2.java.connectionpool.ConnectionPool;
import by.htp.kirova.task2.java.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.dao.GenericDAO;
import by.htp.kirova.task2.java.entity.RoomClass;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Provides Room Class with an opportunity to retrieve, change and delete data from
 * the relevant database table.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class RoomClassDAOImpl implements GenericDAO<RoomClass> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(RoomClassDAOImpl.class);

    /**
     * Constant string which represents query to create room class.
     */
    private static final String SQL_CREATE_ROOM_CLASS = "INSERT INTO `room_classes`(`name`, `enable`) VALUES ('%s', %b)";

    /**
     * Constant string which represents query to select all room classes.
     */
    private static final String SQL_SELECT_FROM_ROOM_CLASSES = "SELECT * FROM `room_classes` ";

    /**
     * Constant string which represents query to update room class.
     */
    private static final String SQL_UPDATE_ROOM_CLASS = "UPDATE `room_classes` SET `name`='%s', `enable`= %b WHERE `id`= %d";

    /**
     * Constant string which represents query to delete room class.
     */
    private static final String SQL_DELETE_ROOM_CLASS = "DELETE FROM `room_classes` WHERE `id` = %d";


    @Override
    public boolean create(RoomClass roomClass) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = String.format(Locale.US, SQL_CREATE_ROOM_CLASS, roomClass.getName(), roomClass.isEnable());

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            int id = cp.executeUpdate(connection, sql, true);
            if (id > 0) {
                roomClass.setId(id);
                connection.setAutoCommit(false);
                connection.commit();
                return true;
            }

        } catch (ConnectionPoolException | SQLException e) {
            cp.rollbackConnection(connection);
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

        } finally {
            if (cp != null && connection != null) {
                cp.setAutoCommitTrue(connection);
                cp.returnConnection(connection);
            }
        }

        return false;
    }


    @Override
    public List<RoomClass> read(String where) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String sql = SQL_SELECT_FROM_ROOM_CLASSES + where;

        List<RoomClass> list = new ArrayList<>();

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new RoomClass(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
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
    public boolean update(RoomClass roomClass) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = String.format(Locale.US, SQL_UPDATE_ROOM_CLASS, roomClass.getName(), roomClass.isEnable(),
                roomClass.getId());

        int result;

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            result = cp.executeUpdate(connection, sql, false);

            connection.setAutoCommit(false);
            connection.commit();

        } catch (ConnectionPoolException | SQLException e) {
            cp.rollbackConnection(connection);
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

        } finally {
            if (cp != null && connection != null) {
                cp.setAutoCommitTrue(connection);
                cp.returnConnection(connection);
            }
        }

        return result == 1;
    }

    @Override
    public boolean delete(RoomClass roomClass) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = String.format(Locale.US, SQL_DELETE_ROOM_CLASS, roomClass.getId());

        int result;

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            result = cp.executeUpdate(connection, sql, false);

        } catch (ConnectionPoolException e) {
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

        } finally {
            if (cp != null && connection != null) {
                cp.returnConnection(connection);
            }
        }

        return result == 1;
    }

}
