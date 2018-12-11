package by.htp.kirova.task2.dao.daoentity;

import by.htp.kirova.task2.dao.BookingDAO;
import by.htp.kirova.task2.dao.ConnectionPool;
import by.htp.kirova.task2.dao.DAOException;
import by.htp.kirova.task2.dao.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.dao.connectionpool.ConnectionPoolImpl;
import by.htp.kirova.task2.entity.ReserveByUser;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides Reservations by user  with an opportunity to read joined data from
 * the database tables.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class ReserveByUserDAOImpl implements BookingDAO<ReserveByUser> {


    /**
     * The unique identification number of reservation constant from DB.
     */
    private final static String RESERVATION_ID = "res.id";

    /**
     * The reservation date constant from DB.
     */
    private final static String RESERVATION_DATE = "res.reservation_date";

    /**
     * The check in date constant from DB.
     */
    private final static String CHECKIN_DATE = "res.checkin_date";

    /**
     * The check out date constant from DB.
     */
    private final static String CHECKOUT_DATE = "res.checkout_date";

    /**
     * The total cost of reservation constant from DB.
     */
    private final static String TOTAL_COST = "res.total_cost";

    /**
     * The room name constant from DB.
     */
    private final static String ROOM_NAME = "rooms.name";

    /**
     * The room number constant from DB.
     */
    private final static String ROOM_NUMBER = "rooms.number";

    /**
     * The room capacity (person) constant from DB.
     */
    private final static String ROOM_CAPACITY = "rooms.capacity";

    /**
     * The name of class room from DB.
     */
    private final static String ROOM_CLASS_NAME = "rc.name";

    /**
     * The name of class room from DB.
     */
    private final static String ASSESSMENT = "res.assessment";


    private static final String SQL_GET_RESERVATIONS_BY_USER = "SELECT res.id, res.reservation_date, res.checkin_date, " +
            "res.checkout_date, rooms.name, rooms.number, rooms.capacity, rc.name, res.total_cost, res.assessment " +
            "FROM reservations as res JOIN rooms " +
            "JOIN room_classes as rc " +
            "WHERE res.rooms_id = rooms.id AND rooms.room_classes_id = rc.id AND res.enabled = true " +
            "AND res.users_username like ";


    @Override
    public boolean create(ReserveByUser entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<ReserveByUser> read(String where) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;

        String sql = SQL_GET_RESERVATIONS_BY_USER + where;

        List<ReserveByUser> reservations = new ArrayList<>();

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.getConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                reservations.add(new ReserveByUser(
                        resultSet.getLong(RESERVATION_ID),
                        resultSet.getLong(RESERVATION_DATE),
                        resultSet.getLong(CHECKIN_DATE),
                        resultSet.getLong(CHECKOUT_DATE),
                        resultSet.getDouble(TOTAL_COST),
                        resultSet.getString(ROOM_NAME),
                        resultSet.getString(ROOM_NUMBER),
                        resultSet.getInt(ROOM_CAPACITY),
                        resultSet.getString(ROOM_CLASS_NAME),
                        resultSet.getByte(ASSESSMENT)
                ));
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("ConnectionPoolImpl error: ", e);

        } finally {
            if (cp != null && connection != null) {
                cp.closeStatement(statement);
                cp.releaseConnection(connection);
            }
        }

        return reservations;
    }


    @Override
    public boolean update(ReserveByUser entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(ReserveByUser entity) throws DAOException {
        throw new UnsupportedOperationException();
    }
}
