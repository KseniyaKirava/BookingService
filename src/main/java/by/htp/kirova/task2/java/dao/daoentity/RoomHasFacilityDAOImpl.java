package by.htp.kirova.task2.java.dao.daoentity;


import by.htp.kirova.task2.java.connectionpool.ConnectionPool;
import by.htp.kirova.task2.java.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.dao.GenericDAO;
import by.htp.kirova.task2.java.entity.RoomHasFacility;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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
    private static final String SQL_CREATE_RHF = "INSERT INTO rooms_has_facilities(rooms_id, facilities_id," +
            " count, enabled) VALUES (?, ?, ?, ?)";

    /**
     * Constant string which represents query to select all relations between facility and room.
     */
    private static final String SQL_SELECT_FROM_RHF = "SELECT * FROM rooms_has_facilities ";

    /**
     * Constant string which represents query to update relations between facility and room.
     */
    private static final String SQL_UPDATE_RHF = "UPDATE rooms_has_facilities SET count= ?, enabled= ? " +
            "WHERE rooms_id= ? AND facilities_id= ?";

    /**
     * Constant string which represents query to delete relations between facility and room.
     */
    private static final String SQL_DELETE_RHF = "DELETE FROM rooms_has_facilities WHERE rooms_id= ? AND" +
            "facilities_id= ?";


    @Override
    public boolean create(RoomHasFacility roomHasFacility) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        int result;

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            ps = connection.prepareStatement(SQL_CREATE_RHF);
            ps.setLong(1, roomHasFacility.getRooms_id());
            ps.setLong(2, roomHasFacility.getFacilities_id());
            ps.setInt(3, roomHasFacility.getCount());
            ps.setBoolean(4, roomHasFacility.isEnabled());

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
            if (cp != null && connection != null && ps != null) {
                cp.setAutoCommitTrue(connection);
                cp.closePreparedStatement(ps);
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
        ResultSet resultSet;

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
                        resultSet.getBoolean("enabled")
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
    public boolean update(RoomHasFacility roomHasFacility) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        int result;

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            ps = connection.prepareStatement(SQL_UPDATE_RHF);
            ps.setInt(1, roomHasFacility.getCount());
            ps.setBoolean(2, roomHasFacility.isEnabled());
            ps.setLong(3, roomHasFacility.getRooms_id());
            ps.setLong(4, roomHasFacility.getFacilities_id());

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
            if (cp != null && connection != null && ps != null) {
                cp.setAutoCommitTrue(connection);
                cp.closePreparedStatement(ps);
                cp.returnConnection(connection);
            }
        }

        return result == 1;
    }

    @Override
    public boolean delete(RoomHasFacility roomHasFacility) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        int result;

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            ps = connection.prepareStatement(SQL_DELETE_RHF);
            ps.setLong(1, roomHasFacility.getRooms_id());
            ps.setLong(2, roomHasFacility.getFacilities_id());

            result = ps.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

        } finally {
            if (cp != null && connection != null && ps != null) {
                cp.closePreparedStatement(ps);
                cp.returnConnection(connection);
            }
        }

        return result == 1;
    }

}
