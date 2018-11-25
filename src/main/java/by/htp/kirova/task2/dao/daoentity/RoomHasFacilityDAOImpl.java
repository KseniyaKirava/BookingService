package by.htp.kirova.task2.dao.daoentity;


import by.htp.kirova.task2.dao.BookingDAO;
import by.htp.kirova.task2.dao.ConnectionPool;
import by.htp.kirova.task2.dao.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.dao.DAOException;
import by.htp.kirova.task2.dao.connectionpool.ConnectionPoolImpl;
import by.htp.kirova.task2.entity.RoomHasFacility;
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
public class RoomHasFacilityDAOImpl implements BookingDAO<RoomHasFacility> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(RoomHasFacilityDAOImpl.class);


    /**
     * The unique identification number of room constant.
     */
    private final static String ROOMS_ID = "rooms_id";

    /**
     * The unique identification number of facility constant.
     */
    private final static String FACILITIES_ID = "facilities_id";

    /**
     * The count of facility.
     */
    private final static String COUNT = "count";

    /**
     * The enabled state constant.
     */
    private final static String ENABLED = "enabled";

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

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.getConnection();
            connection.setAutoCommit(false);

            ps = connection.prepareStatement(SQL_CREATE_RHF);
            ps.setLong(1, roomHasFacility.getRoomsId());
            ps.setLong(2, roomHasFacility.getFacilitiesId());
            ps.setInt(3, roomHasFacility.getCount());
            ps.setBoolean(4, roomHasFacility.isEnabled());

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
            }
            if (cp != null && connection != null) {
                cp.setAutoCommitTrue(connection);
                cp.releaseConnection(connection);
            }
        }

        return true;
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
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.getConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                list.add(new RoomHasFacility(
                        resultSet.getLong(ROOMS_ID),
                        resultSet.getLong(FACILITIES_ID),
                        resultSet.getInt(COUNT),
                        resultSet.getBoolean(ENABLED)
                ));
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("ConnectionPool or SQL error: ", e);

        } finally {
            if (cp != null && statement != null) {
                cp.closeStatement(statement);
            }
            if (cp != null && connection != null) {
                cp.releaseConnection(connection);
            }
        }

        return list;
    }

    @Override
    public boolean update(RoomHasFacility roomHasFacility) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.getConnection();
            connection.setAutoCommit(false);

            ps = connection.prepareStatement(SQL_UPDATE_RHF);
            ps.setInt(1, roomHasFacility.getCount());
            ps.setBoolean(2, roomHasFacility.isEnabled());
            ps.setLong(3, roomHasFacility.getRoomsId());
            ps.setLong(4, roomHasFacility.getFacilitiesId());

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
            }
            if (cp != null && connection != null) {
                cp.setAutoCommitTrue(connection);
                cp.releaseConnection(connection);
            }
        }

        return true;
    }

    @Override
    public boolean delete(RoomHasFacility roomHasFacility) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.getConnection();

            ps = connection.prepareStatement(SQL_DELETE_RHF);
            ps.setLong(1, roomHasFacility.getRoomsId());
            ps.setLong(2, roomHasFacility.getFacilitiesId());

            int result = ps.executeUpdate();

            if (result <= 0) {
                return false;
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("ConnectionPool or SQL error: ", e);

        } finally {
            if (cp != null && ps != null) {
                cp.closePreparedStatement(ps);
            }
            if (cp != null && connection != null) {
                cp.releaseConnection(connection);
            }
        }

        return true;
    }

}
