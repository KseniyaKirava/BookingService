package by.htp.kirova.task2.dao.daoentity;

import by.htp.kirova.task2.dao.BookingDAO;
import by.htp.kirova.task2.dao.ConnectionPool;
import by.htp.kirova.task2.dao.DAOException;
import by.htp.kirova.task2.dao.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.dao.connectionpool.ConnectionPoolImpl;
import by.htp.kirova.task2.entity.ReserveForManager;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides Reservations for manager with an opportunity to read joined data from
 * the database tables.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class ReserveForManagerDAOImpl implements BookingDAO<ReserveForManager> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger logger = Logger.getLogger(ReserveForManagerDAOImpl.class);

    /**
     * The unique identification number of reservation constant from DB.
     */
    private final static String RESERVATION_ID = "res.id";

    /**
     * The first name constant from DB.
     */
    private final static String FIRST_NAME = "users.first_name";

    /**
     * The last name constant from DB.
     */
    private final static String LAST_NAME = "users.last_name";

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
     * The SQL reservation query constant.
     */
    private static final String SQL_RESERVATIONS_FOR_MANAGER = "SELECT res.id, users.first_name, users.last_name, res.checkin_date, " +
            "res.checkout_date, res.total_cost, rooms.name, rooms.number, rooms.capacity, rc.name FROM reservations as res " +
            "JOIN room_classes as rc JOIN rooms JOIN users " +
            "WHERE users.username like res.users_username AND rooms.id=res.rooms_id AND rc.id=rooms.room_classes_id AND " +
            "res.checkin_date = ";



    @Override
    public boolean create(ReserveForManager entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<ReserveForManager> read(String where) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;

        String sql = SQL_RESERVATIONS_FOR_MANAGER + where;

        List<ReserveForManager> reservations = new ArrayList<>();

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.getConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                reservations.add(new ReserveForManager(
                        resultSet.getLong(RESERVATION_ID),
                        resultSet.getString(FIRST_NAME),
                        resultSet.getString(LAST_NAME),
                        resultSet.getLong(CHECKIN_DATE),
                        resultSet.getLong(CHECKOUT_DATE),
                        resultSet.getString(ROOM_NAME),
                        resultSet.getString(ROOM_NUMBER),
                        resultSet.getInt(ROOM_CAPACITY),
                        resultSet.getString(ROOM_CLASS_NAME),
                        resultSet.getDouble(TOTAL_COST)
                ));
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("ConnectionPoolImpl error: ", e);

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

        return reservations;
    }


    @Override
    public boolean update(ReserveForManager entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(ReserveForManager entity) throws DAOException {
        throw new UnsupportedOperationException();
    }
}
