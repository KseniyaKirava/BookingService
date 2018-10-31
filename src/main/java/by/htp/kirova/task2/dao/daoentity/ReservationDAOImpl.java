package by.htp.kirova.task2.dao.daoentity;

import by.htp.kirova.task2.dao.BookingDAO;
import by.htp.kirova.task2.dao.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.dao.connectionpool.ConnectionPoolImpl;
import by.htp.kirova.task2.entity.Reservation;
import by.htp.kirova.task2.dao.DAOException;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Provides Reservation with an opportunity to retrieve, change and delete data from
 * the relevant database table.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class ReservationDAOImpl implements BookingDAO<Reservation> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(ReservationDAOImpl.class);

    /**
     * Constant string which represents query to create reservation.
     */
    private static final String SQL_CREATE_RESERVATION = "INSERT INTO reservations(reservation_date, " +
            "checkin_date, checkout_date, total_cost, enabled, requests_id, requests_users_username, rooms_id, " +
            "rooms_room_classes_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    /**
     * Constant string which represents query to select all reservations.
     */
    private static final String SQL_SELECT_FROM_RESERVATIONS = "SELECT * FROM reservations ";

    /**
     * Constant string which represents query to update reservation.
     */
    private static final String SQL_UPDATE_RESERVATION = "UPDATE reservations SET reservation_date= ?," +
            "checkin_date= ?,checkout_date= ?,total_cost= ?, enabled= ?, requests_id= ?," +
            "requests_users_username= ?,rooms_id= ?,rooms_room_classes_id= ? WHERE id= ?";

    /**
     * Constant string which represents query to delete reservation.
     */
    private static final String SQL_DELETE_RESERVATION = "DELETE FROM reservations WHERE id = ?";


    @Override
    public boolean create(Reservation reservation) throws DAOException {
        ConnectionPoolImpl cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.extractConnection();

            ps = connection.prepareStatement(SQL_CREATE_RESERVATION, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, reservation.getReservationDate());
            ps.setLong(2, reservation.getCheckinDate());
            ps.setLong(3, reservation.getCheckoutDate());
            ps.setDouble(4, reservation.getTotal_cost());
            ps.setBoolean(5, reservation.isEnabled());
            ps.setLong(6,  reservation.getRequestsId());
            ps.setString(7, reservation.getRequestsUsersUsername());
            ps.setLong(8,  reservation.getRoomsId());
            ps.setLong(9, reservation.getRoomsRoomClassesId());

            int result = ps.executeUpdate();
            int id = 0;

            if (result > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                }
            }

            if (id > 0) {
                reservation.setId(id);

                connection.setAutoCommit(false);
                connection.commit();
                return true;
            }

        } catch (ConnectionPoolException | SQLException e) {
            if (cp != null) {
                cp.rollbackConnection(connection);
            }
            LOGGER.error("ConnectionPoolImpl error: ", e);
            throw new DAOException("ConnectionPoolImpl error: ", e);

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
    public List<Reservation> read(String where) throws DAOException {
        ConnectionPoolImpl cp = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;

        String sql = SQL_SELECT_FROM_RESERVATIONS + where;

        List<Reservation> list = new ArrayList<>();

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.extractConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new Reservation(
                        resultSet.getLong("id"),
                        resultSet.getLong("reservation_date"),
                        resultSet.getLong("checkin_date"),
                        resultSet.getLong("checkout_date"),
                        resultSet.getDouble("total_cost"),
                        resultSet.getBoolean("enabled"),
                        resultSet.getLong("requests_id"),
                        resultSet.getString("requests_users_username"),
                        resultSet.getLong("rooms_id"),
                        resultSet.getLong("rooms_room_classes_id")
                ));
            }

        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.error("ConnectionPoolImpl error: ", e);
            throw new DAOException("ConnectionPoolImpl error: ", e);

        } finally {
            if (cp != null && connection != null) {
                cp.closeStatement(statement);
                cp.returnConnection(connection);
            }
        }

        return list;
    }

    @Override
    public boolean update(Reservation reservation) throws DAOException {
        ConnectionPoolImpl cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        int result;

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.extractConnection();

            ps = connection.prepareStatement(SQL_UPDATE_RESERVATION);
            ps.setLong(1, reservation.getReservationDate());
            ps.setLong(2, reservation.getCheckinDate());
            ps.setLong(3, reservation.getCheckoutDate());
            ps.setDouble(4, reservation.getTotal_cost());
            ps.setBoolean(5, reservation.isEnabled());
            ps.setLong(6,  reservation.getRequestsId());
            ps.setString(7, reservation.getRequestsUsersUsername());
            ps.setLong(8,  reservation.getRoomsId());
            ps.setLong(9, reservation.getRoomsRoomClassesId());
            ps.setLong(10, reservation.getId());

            result = ps.executeUpdate();

            connection.setAutoCommit(false);
            connection.commit();

        } catch (ConnectionPoolException | SQLException e) {
            if (cp != null) {
                cp.rollbackConnection(connection);
            }
            LOGGER.error("ConnectionPoolImpl error: ", e);
            throw new DAOException("ConnectionPoolImpl error: ", e);

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
    public boolean delete(Reservation reservation) throws DAOException {
        ConnectionPoolImpl cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        int result;

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.extractConnection();

            ps = connection.prepareStatement(SQL_DELETE_RESERVATION);
            ps.setLong(1, reservation.getId());

            result = ps.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.error("ConnectionPoolImpl error: ", e);
            throw new DAOException("ConnectionPoolImpl error: ", e);

        } finally {
            if (cp != null && connection != null && ps!= null){
                cp.closePreparedStatement(ps);
                cp.returnConnection(connection);
            }
        }

        return result == 1;
    }

}
