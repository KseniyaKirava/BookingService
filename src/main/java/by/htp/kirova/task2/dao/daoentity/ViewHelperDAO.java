package by.htp.kirova.task2.dao.daoentity;

import by.htp.kirova.task2.dao.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.dao.DAOException;
import by.htp.kirova.task2.dao.HelperDAO;
import by.htp.kirova.task2.dao.connectionpool.ConnectionPoolImpl;
import by.htp.kirova.task2.service.util.DateService;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides the implementation of methods for correct View layer.
 *
 * @author Kseniya Kirava
 * @since Oct 16, 2018
 */
public class ViewHelperDAO implements HelperDAO {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(ViewHelperDAO.class);

    /**
     * Constant string which represents query to create request.
     */
    private static final String SQL_SHOW_ALL_RESERVATIONS = "SELECT res.id, res.reservation_date, res.checkin_date, " +
            "res.checkout_date, rooms.name, rooms.number, rooms.capacity, rc.name, res.total_cost " +
            "FROM reservations as res JOIN rooms " +
            "JOIN room_classes as rc " +
            "WHERE res.rooms_id = rooms.id AND rooms.room_classes_id = rc.id AND res.enabled = true " +
            "AND res.requests_users_username like ";


    private static final String SQL_SHOW_AVIALIABLE_ROOM = "SELECT req.room_capacity, req.checkin_date, req.checkout_date, " +
            "req.room_class, rooms.id, rooms.name, rooms.number, rooms.cost, rooms.room_classes_id " +
            "FROM rooms JOIN requests as req " +
            "JOIN room_classes as rc " +
            "WHERE req.id = ? AND rooms.capacity >= req.room_capacity AND rc.name like req.room_class " +
            "AND rooms.room_classes_id = rc.id " +
            "AND rooms.id NOT IN " +
            "(SELECT res.rooms_id FROM reservations as res " +
            "WHERE (req.checkout_date >= res.checkin_date AND res.checkout_date >= req.checkin_date) " +
            "AND res.enabled = true)";

    @Override
    public List<String> avialiableRooms(Long id) throws DAOException {
        ConnectionPoolImpl cp = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet;

        List<String> room = new ArrayList<>();

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.getConnection();

            ps = connection.prepareStatement(SQL_SHOW_AVIALIABLE_ROOM);
            ps.setLong(1, id);

            double minCost = Double.MAX_VALUE;

            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                double currentCost = resultSet.getDouble("rooms.cost");
                if (currentCost < minCost) {
                    room = new ArrayList<>();
                    room.add(String.valueOf(resultSet.getInt("req.room_capacity")));
                    room.add(DateService.convertDateToString(resultSet.getLong("req.checkin_date")));
                    room.add(DateService.convertDateToString(resultSet.getLong("req.checkout_date")));
                    room.add(resultSet.getString("req.room_class"));
                    room.add(String.valueOf(resultSet.getInt("rooms.id")));
                    room.add(resultSet.getString("rooms.name"));
                    room.add(resultSet.getString("rooms.number"));
                    room.add(String.valueOf(currentCost));
                    room.add(String.valueOf(resultSet.getLong("rooms.room_classes_id")));
                    minCost = currentCost;
                }
            }

        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.error("ConnectionPoolImpl error: ", e);
            throw new DAOException("ConnectionPoolImpl error: ", e);

        } finally {
            if (cp != null && connection != null) {
                cp.closePreparedStatement(ps);
                cp.releaseConnection(connection);
            }
        }

        return room;
    }


    @Override
    public List<ArrayList<Object>> allReservations(String where) throws DAOException {
        ConnectionPoolImpl cp = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;

        String sql = SQL_SHOW_ALL_RESERVATIONS + where;

        List<ArrayList<Object>> reservations = new ArrayList<>();

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.getConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                ArrayList<Object> reservation = new ArrayList<>();
                reservation.add(resultSet.getInt("res.id"));
                reservation.add(resultSet.getLong("res.reservation_date"));
                reservation.add(resultSet.getLong("res.checkin_date"));
                reservation.add(resultSet.getLong("res.checkout_date"));
                reservation.add(resultSet.getString("rooms.name"));
                reservation.add(resultSet.getString("rooms.number"));
                reservation.add(resultSet.getInt("rooms.capacity"));
                reservation.add(resultSet.getString("rc.name"));
                reservation.add(resultSet.getDouble("res.total_cost"));
                reservations.add(reservation);
            }

        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.error("ConnectionPoolImpl error: ", e);
            throw new DAOException("ConnectionPoolImpl error: ", e);

        } finally {
            if (cp != null && connection != null) {
                cp.closeStatement(statement);
                cp.releaseConnection(connection);
            }
        }


        return reservations;
    }
}
