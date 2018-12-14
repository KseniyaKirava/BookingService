package by.htp.kirova.task2.dao.daoentity;

import by.htp.kirova.task2.dao.BookingDAO;
import by.htp.kirova.task2.dao.ConnectionPool;
import by.htp.kirova.task2.dao.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.dao.connectionpool.ConnectionPoolImpl;
import by.htp.kirova.task2.entity.Room;
import by.htp.kirova.task2.dao.DAOException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Provides Room with an opportunity to retrieve, change and delete data from
 * the relevant database table.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class RoomDAOImpl implements BookingDAO<Room> {


    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger logger = Logger.getLogger(RoomDAOImpl.class);


    /**
     * The unique identification number constant.
     */
    private final static String ID = "id";

    /**
     * The name of room constant.
     */
    private final static String NAME = "name";

    /**
     * The number of room constant.
     */
    private final static String NUMBER = "number";

    /**
     * The capacity of room constant.
     */
    private final static String CAPACITY = "capacity";

    /**
     * The cost of room constant.
     */
    private final static String COST = "cost";

    /**
     * The enabled state constant.
     */
    private final static String ENABLED = "enabled";

    /**
     * The unique identification number constant.
     */
    private final static String ROOM_CLASSES_ID = "room_classes_id";

    /**
     * The average assessment mark constant.
     */
    private final static String AVERAGE_ASSESSMENT = "average_assessment";


    /**
     * Constant string which represents query to create room.
     */
    private static final String SQL_CREATE_ROOM = "INSERT INTO rooms(name, number, capacity, cost, " +
            "enabled, room_classes_id, average_assessment) VALUES (?, ?, ?, ?, ?, ?, ?)";

    /**
     * Constant string which represents query to select all rooms.
     */
    private static final String SQL_SELECT_FROM_ROOMS = "SELECT * FROM rooms ";

    /**
     * Constant string which represents query to update room.
     */
    private static final String SQL_UPDATE_ROOM = "UPDATE rooms SET name= ?, number= ?, capacity= ?," +
            "cost= ?, enabled= ?, room_classes_id= ?, average_assessment=? WHERE id= ?";

    /**
     * Constant string which represents query to delete room.
     */
    private static final String SQL_DELETE_ROOM = "DELETE FROM rooms WHERE id = ?";

    @Override
    public boolean create(Room room) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.getConnection();
            connection.setAutoCommit(false);

            ps = connection.prepareStatement(SQL_CREATE_ROOM, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, room.getName());
            ps.setString(2, room.getNumber());
            ps.setInt(3, room.getCapacity());
            ps.setDouble(4, room.getCost());
            ps.setBoolean(5, room.isEnabled());
            ps.setLong(6, room.getRoomClassesId());
            ps.setDouble(7, room.getAverageAssessment());

            int result = ps.executeUpdate();

            if (result <= 0) {
                return false;
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                room.setId(id);
            }

            connection.commit();

        } catch (ConnectionPoolException | SQLException e) {
            if (cp != null && connection != null) {
                cp.rollbackConnection(connection);
            }
            throw new DAOException("ConnectionPool or SQL error: ", e);

        } finally {
            if (cp != null && ps != null) {
                cp.closePreparedStatement(ps);
                logger.debug("Prepared statement closed");
            }
            if (cp != null && connection != null) {
                cp.setAutoCommitTrue(connection);
                cp.releaseConnection(connection);
                logger.debug("Connection released");
            }
        }

        return true;
    }

    @Override
    public List<Room> read(String where) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;

        String sql = SQL_SELECT_FROM_ROOMS + where;

        List<Room> rooms = new ArrayList<>();

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.getConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                rooms.add(new Room(
                        resultSet.getLong(ID),
                        resultSet.getString(NAME),
                        resultSet.getString(NUMBER),
                        resultSet.getInt(CAPACITY),
                        resultSet.getDouble(COST),
                        resultSet.getBoolean(ENABLED),
                        resultSet.getLong(ROOM_CLASSES_ID),
                        resultSet.getDouble(AVERAGE_ASSESSMENT)
                        ));
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("ConnectionPool or SQL error: ", e);

        } finally {
            if (cp != null && statement != null) {
                cp.closeStatement(statement);
                logger.debug("Statement and resultset closed");
            }
            if (cp != null && connection != null) {
                cp.releaseConnection(connection);
                logger.debug("Connection released");
            }
        }

        return rooms;
    }

    @Override
    public boolean update(Room room) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.getConnection();
            connection.setAutoCommit(false);

            ps = connection.prepareStatement(SQL_UPDATE_ROOM);
            ps.setString(1, room.getName());
            ps.setString(2, room.getNumber());
            ps.setInt(3, room.getCapacity());
            ps.setDouble(4, room.getCost());
            ps.setBoolean(5, room.isEnabled());
            ps.setLong(6, room.getRoomClassesId());
            ps.setDouble(7, room.getAverageAssessment());
            ps.setLong(8, room.getId());

            int result = ps.executeUpdate();

            if (result <= 0) {
                return false;
            }

            connection.commit();

        } catch (ConnectionPoolException | SQLException e) {
            if (cp != null && connection != null) {
                cp.rollbackConnection(connection);
            }
            throw new DAOException("ConnectionPool or SQL error: ", e);

        } finally {
            if (cp != null && ps != null) {
                cp.closePreparedStatement(ps);
                logger.debug("Prepared statement closed");
            }
            if (cp != null && connection != null) {
                cp.setAutoCommitTrue(connection);
                cp.releaseConnection(connection);
                logger.debug("Connection released");
            }
        }

        return true;
    }

    @Override
    public boolean delete(Room room) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.getConnection();

            ps = connection.prepareStatement(SQL_DELETE_ROOM);
            ps.setLong(1, room.getId());

            int result = ps.executeUpdate();

            if (result <= 0) {
                return false;
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("ConnectionPool or SQL error: ", e);

        } finally {
            if (cp != null && ps != null) {
                cp.closePreparedStatement(ps);
                logger.debug("Prepared statement closed");
            }
            if (cp != null && connection != null) {
                cp.releaseConnection(connection);
                logger.debug("Connection released");
            }
        }

        return true;
    }
}
