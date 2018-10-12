package by.htp.kirova.task2.java.dao.daoentity;

import by.htp.kirova.task2.java.connectionpool.ConnectionPool;
import by.htp.kirova.task2.java.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.dao.GenericDAO;
import by.htp.kirova.task2.java.entity.Reservation;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Provides Reservation with an opportunity to retrieve, change and delete data from
 * the relevant database table.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class ReservationDAOImpl implements GenericDAO<Reservation> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(ReservationDAOImpl.class);

    /**
     * Constant string which represents query to create reservation.
     */
    private static final String SQL_CREATE_RESERVATION = "INSERT INTO `reservations`(`reservation_date`, " +
            "`checkin_date`, `checkout_date`, `total_cost`, `enable`, `requests_id`, `requests_users_username`, `rooms_id`, " +
            "`rooms_room_classes_id`) VALUES (%d, %d, %d, %f, %b, %d, '%s', %d, %d)";

    /**
     * Constant string which represents query to select all reservations.
     */
    private static final String SQL_SELECT_FROM_RESERVATIONS = "SELECT * FROM `reservations` ";

    /**
     * Constant string which represents query to update reservation.
     */
    private static final String SQL_UPDATE_RESERVATION = "UPDATE `reservations` SET `reservation_date`= %d," +
            "`checkin_date`= %d,`checkout_date`= %d,`total_cost`= %f, `enable`= %b, `requests_id`= %d," +
            "`requests_users_username`= '%s',`rooms_id`= %d,`rooms_room_classes_id`= %d WHERE `id`= %d";

    /**
     * Constant string which represents query to delete reservation.
     */
    private static final String SQL_DELETE_RESERVATION = "DELETE FROM `reservations` WHERE `id` = %d";


    @Override
    public boolean create(Reservation reservation) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = String.format(Locale.US, SQL_CREATE_RESERVATION, reservation.getReservation_date(),
                reservation.getCheckin_date(), reservation.getCheckout_date(), reservation.getTotal_cost(),
                reservation.isEnable(), reservation.getRequests_id(), reservation.getRequests_users_username(),
                reservation.getRooms_id(), reservation.getRooms_room_classes_id());

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            int id = cp.executeUpdate(connection, sql, true);
            if (id > 0) {
                reservation.setId(id);
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
    public List<Reservation> read(String where) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String sql = SQL_SELECT_FROM_RESERVATIONS + where;

        List<Reservation> list = new ArrayList<>();

        try {
            cp = ConnectionPool.getInstance();
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
                        resultSet.getBoolean("enable"),
                        resultSet.getLong("requests_id"),
                        resultSet.getString("requests_users_username"),
                        resultSet.getLong("rooms_id"),
                        resultSet.getLong("rooms_room_classes_id")
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
    public boolean update(Reservation reservation) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = String.format(Locale.US, SQL_UPDATE_RESERVATION, reservation.getReservation_date(),
                reservation.getCheckin_date(), reservation.getCheckout_date(), reservation.getTotal_cost(),
                reservation.isEnable(), reservation.getRequests_id(), reservation.getRequests_users_username(),
                reservation.getRooms_id(), reservation.getRooms_room_classes_id(), reservation.getId());

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
    public boolean delete(Reservation reservation) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = String.format(Locale.US, SQL_DELETE_RESERVATION, reservation.getId());

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
