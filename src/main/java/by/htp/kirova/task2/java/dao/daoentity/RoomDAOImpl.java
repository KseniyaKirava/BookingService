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
            "`room_classes_id`) VALUES ('%s', '%s', %d, %f, %d)";

    /**
     * Constant string which represents query to select all rooms.
     */
    private static final String SQL_SELECT_FROM_ROOMS = "SELECT * FROM `rooms` ";

    /**
     * Constant string which represents query to update room.
     */
    private static final String SQL_UPDATE_ROOM = "UPDATE `rooms` SET `name`= '%s',`number`= '%s',`capacity`= %d," +
            "`cost`= %f,`room_classes_id`= %d WHERE `id`= %d";

    /**
     * Constant string which represents query to delete room.
     */
    private static final String SQL_DELETE_ROOM = "DELETE FROM `rooms` WHERE `id` = %d";

    @Override
    public boolean create(Room room) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = String.format(Locale.US, SQL_CREATE_ROOM, room.getName(), room.getNumber(), room.getCapacity(),
                room.getCost(), room.getRoom_classes_id());

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            int id = executeUpdate(connection, sql, true);
            if (id > 0) {
                room.setId(id);
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
    public List<Room> read(String where) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = SQL_SELECT_FROM_ROOMS + where;

        List<Room> list = new ArrayList<>();

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new Room(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("number"),
                        resultSet.getInt("capacity"),
                        resultSet.getDouble("cost"),
                        resultSet.getLong("room_classes_id")
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
    public boolean update(Room room) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = String.format(Locale.US, SQL_UPDATE_ROOM, room.getName(), room.getNumber(), room.getCapacity(),
                room.getCost(), room.getRoom_classes_id(), room.getId());

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
    public boolean delete(Room room) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = String.format(Locale.US, SQL_DELETE_ROOM, room.getId());

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
                connection.rollback();
            }
        } catch (SQLException z) {
            LOGGER.error("Connection rollback operation error: ", z);
        }
    }
}
