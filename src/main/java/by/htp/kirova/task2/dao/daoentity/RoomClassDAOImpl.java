package by.htp.kirova.task2.dao.daoentity;

import by.htp.kirova.task2.dao.BookingDAO;
import by.htp.kirova.task2.dao.ConnectionPool;
import by.htp.kirova.task2.dao.DAOException;
import by.htp.kirova.task2.dao.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.dao.connectionpool.ConnectionPoolImpl;
import by.htp.kirova.task2.entity.RoomClass;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Provides Room Class with an opportunity to retrieve, change and delete data from
 * the relevant database table.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class RoomClassDAOImpl implements BookingDAO<RoomClass> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger logger = Logger.getLogger(RoomClassDAOImpl.class);

    /**
     * The unique identification number constant.
     */
    private final static String ID = "id";

    /**
     * The name of class of the room constant.
     */
    private final static String NAME = "name";

    /**
     * The enabled state constant.
     */
    private final static String ENABLED = "enabled";

    /**
     * Constant string which represents query to create room class.
     */
    private static final String SQL_CREATE_ROOM_CLASS = "INSERT INTO room_classes(name, enabled) VALUES (?, ?)";

    /**
     * Constant string which represents query to select all room classes.
     */
    private static final String SQL_SELECT_FROM_ROOM_CLASSES = "SELECT * FROM room_classes ";

    /**
     * Constant string which represents query to update room class.
     */
    private static final String SQL_UPDATE_ROOM_CLASS = "UPDATE room_classes SET name=?, enabled= ? WHERE id= ?";

    /**
     * Constant string which represents query to delete room class.
     */
    private static final String SQL_DELETE_ROOM_CLASS = "DELETE FROM room_classes WHERE id = ?";


    @Override
    public boolean create(RoomClass roomClass) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.getConnection();
            connection.setAutoCommit(false);

            ps = connection.prepareStatement(SQL_CREATE_ROOM_CLASS, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, roomClass.getName());
            ps.setBoolean(2, roomClass.isEnabled());

            int result = ps.executeUpdate();

            if (result <= 0) {
                return false;
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                roomClass.setId(id);
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
    public List<RoomClass> read(String where) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;

        String sql = SQL_SELECT_FROM_ROOM_CLASSES + where;

        List<RoomClass> list = new ArrayList<>();

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.getConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                list.add(new RoomClass(
                        resultSet.getLong(ID),
                        resultSet.getString(NAME),
                        resultSet.getBoolean(ENABLED)
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

        return list;
    }

    @Override
    public boolean update(RoomClass roomClass) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.getConnection();
            connection.setAutoCommit(false);

            ps = connection.prepareStatement(SQL_UPDATE_ROOM_CLASS);
            ps.setString(1, roomClass.getName());
            ps.setBoolean(2, roomClass.isEnabled());
            ps.setLong(3, roomClass.getId());

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
    public boolean delete(RoomClass roomClass) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.getConnection();

            ps = connection.prepareStatement(SQL_DELETE_ROOM_CLASS);
            ps.setLong(1, roomClass.getId());

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
