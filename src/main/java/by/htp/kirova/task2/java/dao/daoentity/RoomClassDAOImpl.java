package by.htp.kirova.task2.java.dao.daoentity;

import by.htp.kirova.task2.java.connectionpool.ConnectionPool;
import by.htp.kirova.task2.java.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.dao.GenericDAO;
import by.htp.kirova.task2.java.entity.RoomClass;
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
public class RoomClassDAOImpl implements GenericDAO<RoomClass> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(RoomClassDAOImpl.class);

    /**
     * Constant string which represents query to create room class.
     */
    private static final String SQL_CREATE_ROOM_CLASS = "INSERT INTO room_classes(name, enable) VALUES (?, ?)";

    /**
     * Constant string which represents query to select all room classes.
     */
    private static final String SQL_SELECT_FROM_ROOM_CLASSES = "SELECT * FROM room_classes ";

    /**
     * Constant string which represents query to update room class.
     */
    private static final String SQL_UPDATE_ROOM_CLASS = "UPDATE room_classes SET name=?, enable= ? WHERE id= ?";

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
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            ps = connection.prepareStatement(SQL_CREATE_ROOM_CLASS, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, roomClass.getName());
            ps.setBoolean(2, roomClass.isEnable());

            int result = ps.executeUpdate();
            int id = 0;

            if (result > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                }
            }

            if (id > 0) {
                roomClass.setId(id);

                connection.setAutoCommit(false);
                connection.commit();
                return true;
            }

        } catch (ConnectionPoolException | SQLException e) {
            if (cp != null) {
                cp.rollbackConnection(connection);
            }
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

        } finally {
            if (cp != null && connection != null && ps != null) {
                cp.setAutoCommitTrue(connection);
                cp.closePreparedStatement(ps);
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
        ResultSet resultSet;

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
        PreparedStatement ps = null;

        int result;

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            ps = connection.prepareStatement(SQL_UPDATE_ROOM_CLASS);
            ps.setString(1, roomClass.getName());
            ps.setBoolean(2, roomClass.isEnable());
            ps.setLong(3, roomClass.getId());

            result = ps.executeUpdate();

            connection.setAutoCommit(false);
            connection.commit();

        } catch (ConnectionPoolException | SQLException e) {
            if (cp != null) {
                cp.rollbackConnection(connection);
            }
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

        } finally {
            if (cp != null && connection != null && ps!= null) {
                cp.setAutoCommitTrue(connection);
                cp.closePreparedStatement(ps);
                cp.returnConnection(connection);
            }
        }

        return result == 1;
    }

    @Override
    public boolean delete(RoomClass roomClass) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        int result;

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            ps = connection.prepareStatement(SQL_DELETE_ROOM_CLASS);
            ps.setLong(1, roomClass.getId());

            result = ps.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

        } finally {
            if (cp != null && connection != null && ps!= null){
                cp.closePreparedStatement(ps);
                cp.returnConnection(connection);
            }
        }

        return result == 1;
    }

}
