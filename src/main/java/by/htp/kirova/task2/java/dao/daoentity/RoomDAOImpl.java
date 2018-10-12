package by.htp.kirova.task2.java.dao.daoentity;

import by.htp.kirova.task2.java.connectionpool.ConnectionPool;
import by.htp.kirova.task2.java.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.dao.GenericDAO;
import by.htp.kirova.task2.java.entity.Room;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Provides Room with an opportunity to retrieve, change and delete data from
 * the relevant database table.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class RoomDAOImpl implements GenericDAO<Room> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(RoomDAOImpl.class);

    /**
     * Constant string which represents query to create room.
     */
    private static final String SQL_CREATE_ROOM = "INSERT INTO `rooms`(`name`, `number`, `capacity`, `cost`, " +
            "`enable`, `room_classes_id`) VALUES ('%s', '%s', %d, %f, %b, %d)";

    /**
     * Constant string which represents query to select all rooms.
     */
    private static final String SQL_SELECT_FROM_ROOMS = "SELECT * FROM `rooms` ";

    /**
     * Constant string which represents query to update room.
     */
    private static final String SQL_UPDATE_ROOM = "UPDATE `rooms` SET `name`= '%s',`number`= '%s',`capacity`= %d," +
            "`cost`= %f, `enable`=%b, `room_classes_id`= %d WHERE `id`= %d";

    /**
     * Constant string which represents query to delete room.
     */
    private static final String SQL_DELETE_ROOM = "DELETE FROM `rooms` WHERE `id` = %d";

    @Override
    public boolean create(Room room) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = String.format(Locale.US, SQL_CREATE_ROOM, room.getName(), room.getNumber(), room.getCapacity(),
                room.getCost(), room.isEnable(), room.getRoom_classes_id());

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            int id = cp.executeUpdate(connection, sql, true);
            if (id > 0) {
                room.setId(id);
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
    public List<Room> read(String where) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String sql = SQL_SELECT_FROM_ROOMS + where;

        List<Room> list = new ArrayList<>();

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new Room(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("number"),
                        resultSet.getInt("capacity"),
                        resultSet.getDouble("cost"),
                        resultSet.getBoolean("enable"),
                        resultSet.getLong("room_classes_id")
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
    public boolean update(Room room) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = String.format(Locale.US, SQL_UPDATE_ROOM, room.getName(), room.getNumber(), room.getCapacity(),
                room.getCost(), room.isEnable(), room.getRoom_classes_id(), room.getId());

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
    public boolean delete(Room room) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = String.format(Locale.US, SQL_DELETE_ROOM, room.getId());

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
