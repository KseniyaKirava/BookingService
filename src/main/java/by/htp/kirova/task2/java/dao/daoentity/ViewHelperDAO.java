package by.htp.kirova.task2.java.dao.daoentity;

import by.htp.kirova.task2.java.connectionpool.ConnectionPool;
import by.htp.kirova.task2.java.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.dao.HelperDAO;
import by.htp.kirova.task2.java.util.DateConverter;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
    public List showAvialiableRooms(Long id) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet;

        List<String> room = new ArrayList<>();

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            ps = connection.prepareStatement(SQL_SHOW_AVIALIABLE_ROOM);
            ps.setLong(1, id);

            double minCost = Double.MAX_VALUE;
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                double currentCost = resultSet.getDouble("rooms.cost");
                if(currentCost < minCost){
                    room = new ArrayList<>();
                    room.add(String.valueOf(resultSet.getInt("req.room_capacity")));
                    room.add(DateConverter.convertDateToString(resultSet.getLong("req.checkin_date")));
                    room.add(DateConverter.convertDateToString(resultSet.getLong("req.checkout_date")));
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
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

        } finally {
            if (cp != null && connection != null) {
                cp.closePreparedStatement(ps);
                cp.returnConnection(connection);
            }
        }

        return room;
    }

}
