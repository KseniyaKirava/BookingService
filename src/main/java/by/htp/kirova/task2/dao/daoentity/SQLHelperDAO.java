package by.htp.kirova.task2.dao.daoentity;

import by.htp.kirova.task2.dao.DAOException;
import by.htp.kirova.task2.dao.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.dao.connectionpool.ConnectionPoolImpl;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides the implementation of methods for correct View layer.
 *
 * @author Kseniya Kirava
 * @since Oct 16, 2018
 */
public class SQLHelperDAO {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(SQLHelperDAO.class);

    /**
     * Constant string which represents query to create request.
     */
    private static final String SQL_SHOW_ALL_RESERVATIONS = "SELECT res.id, res.reservation_date, res.checkin_date, " +
            "res.checkout_date, rooms.name, rooms.number, rooms.capacity, rc.name, res.total_cost " +
            "FROM reservations as res JOIN rooms " +
            "JOIN room_classes as rc " +
            "WHERE res.rooms_id = rooms.id AND rooms.room_classes_id = rc.id AND res.enabled = true " +
            "AND res.requests_users_username like ";




//    @Override
//    public List<String> avialiableRooms(Long id) throws DAOException {
//        ConnectionPoolImpl cp = null;
//        Connection connection = null;
//        PreparedStatement ps = null;
//        ResultSet resultSet;
//
//        List<String> room = new ArrayList<>();
//
//        try {
//            cp = ConnectionPoolImpl.getInstance();
//            connection = cp.getConnection();
//
//            ps = connection.prepareStatement(SQL_SHOW_AVIALIABLE_ROOM);
//            ps.setLong(1, id);
//
//            double minCost = Double.MAX_VALUE;
//
//            resultSet = ps.executeQuery();
//            while (resultSet.next()) {
//                double currentCost = resultSet.getDouble("rooms.cost");
//                if (currentCost < minCost) {
//                    room = new ArrayList<>();
//                    room.add(String.valueOf(resultSet.getInt("req.room_capacity")));
//                    room.add(DateService.convertDateToString(resultSet.getLong("req.checkin_date")));
//                    room.add(DateService.convertDateToString(resultSet.getLong("req.checkout_date")));
//                    room.add(resultSet.getString("req.room_class"));
//                    room.add(String.valueOf(resultSet.getInt("rooms.id")));
//                    room.add(resultSet.getString("rooms.name"));
//                    room.add(resultSet.getString("rooms.number"));
//                    room.add(String.valueOf(currentCost));
//                    room.add(String.valueOf(resultSet.getLong("rooms.room_classes_id")));
//                    minCost = currentCost;
//                }
//            }
//
//        } catch (ConnectionPoolException | SQLException e) {
//            LOGGER.error("ConnectionPoolImpl error: ", e);
//            throw new DAOException("ConnectionPoolImpl error: ", e);
//
//        } finally {
//            if (cp != null && connection != null) {
//                cp.closePreparedStatement(ps);
//                cp.releaseConnection(connection);
//            }
//        }
//
//        return room;
//    }


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
