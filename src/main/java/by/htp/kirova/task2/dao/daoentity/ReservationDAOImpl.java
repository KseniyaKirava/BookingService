package by.htp.kirova.task2.dao.daoentity;

import by.htp.kirova.task2.dao.BookingDAO;
import by.htp.kirova.task2.dao.ConnectionPool;
import by.htp.kirova.task2.dao.DAOException;
import by.htp.kirova.task2.dao.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.dao.connectionpool.ConnectionPoolImpl;
import by.htp.kirova.task2.entity.Reservation;
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
    private static final Logger logger = Logger.getLogger(ReservationDAOImpl.class);

    /**
     * The unique identification number constant.
     */
    private final static String ID = "id";

    /**
     * The reservation date constant.
     */
    private final static String RESERVATION_DATE= "reservation_date";

    /**
     * The check in date constant.
     */
    private final static String CHECKIN_DATE= "checkin_date";

    /**
     * The check out date constant.
     */
    private final static String CHECKOUT_DATE= "checkout_date";

    /**
     * The total cost constant.
     */
    private final static String TOTAL_COST= "total_cost";

    /**
     * The unique username constant.
     */
    private final static String USERNAME = "users_username";

    /**
     * The enabled state constant.
     */
    private final static String ENABLED = "enabled";

    /**
     * The enabled state constant.
     */
    private final static String ROOMS_ID = "rooms_id";

    /**
     * The assesment of the from for current reservation constant.
     */
    private final static String ASSESSMENT = "assessment";

    /**
     * Constant string which represents query to create reservation.
     */
    private static final String SQL_CREATE_RESERVATION = "INSERT INTO reservations(reservation_date, " +
            "checkin_date, checkout_date, total_cost, enabled, users_username, rooms_id, " +
            "assessment) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    /**
     * Constant string which represents query to select all reservations.
     */
    private static final String SQL_SELECT_FROM_RESERVATIONS = "SELECT * FROM reservations ";

    /**
     * Constant string which represents query to update reservation.
     */
    private static final String SQL_UPDATE_RESERVATION = "UPDATE reservations SET reservation_date= ?," +
            "checkin_date= ?,checkout_date= ?,total_cost= ?, enabled= ?, users_username= ?, " +
            "rooms_id= ?, assessment= ?  WHERE id= ?";

    /**
     * Constant string which represents query to delete reservation.
     */
    private static final String SQL_DELETE_RESERVATION = "DELETE FROM reservations WHERE id = ?";


    @Override
    public boolean create(Reservation reservation) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.getConnection();
            connection.setAutoCommit(false);

            ps = connection.prepareStatement(SQL_CREATE_RESERVATION, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, reservation.getReservationDate());
            ps.setLong(2, reservation.getCheckinDate());
            ps.setLong(3, reservation.getCheckoutDate());
            ps.setDouble(4, reservation.getTotalCost());
            ps.setBoolean(5, reservation.isEnabled());
            ps.setString(6, reservation.getUsersUsername());
            ps.setLong(7, reservation.getRoomsId());
            ps.setByte(8, reservation.getAssessment());


            int result = ps.executeUpdate();

            if (result <= 0) {
                return false;
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                reservation.setId(id);
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
                logger.debug("Prepared statement closed");
            }
            if (cp != null && connection != null) {
                cp.setAutoCommitTrue(connection);
                cp.releaseConnection(connection);
                logger.debug("Connection released");
            }
        }

        return true;
    }

    @Override
    public List<Reservation> read(String where) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;

        String sql = SQL_SELECT_FROM_RESERVATIONS + where;

        List<Reservation> list = new ArrayList<>();

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.getConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                list.add(new Reservation(
                        resultSet.getLong(ID),
                        resultSet.getLong(RESERVATION_DATE),
                        resultSet.getLong(CHECKIN_DATE),
                        resultSet.getLong(CHECKOUT_DATE),
                        resultSet.getDouble(TOTAL_COST),
                        resultSet.getBoolean(ENABLED),
                        resultSet.getString(USERNAME),
                        resultSet.getLong(ROOMS_ID),
                        resultSet.getByte(ASSESSMENT)
                ));
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("ConnectionPool or SQL error: ", e);

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

        return list;
    }

    @Override
    public boolean update(Reservation reservation) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.getConnection();
            connection.setAutoCommit(false);

            ps = connection.prepareStatement(SQL_UPDATE_RESERVATION);
            ps.setLong(1, reservation.getReservationDate());
            ps.setLong(2, reservation.getCheckinDate());
            ps.setLong(3, reservation.getCheckoutDate());
            ps.setDouble(4, reservation.getTotalCost());
            ps.setBoolean(5, reservation.isEnabled());
            ps.setString(6, reservation.getUsersUsername());
            ps.setLong(7, reservation.getRoomsId());
            ps.setByte(8, reservation.getAssessment());
            ps.setLong(9, reservation.getId());

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
                logger.debug("Prepared statement closed");
            }
            if (cp != null && connection != null) {
                cp.setAutoCommitTrue(connection);
                cp.releaseConnection(connection);
                logger.debug("Connection released");
            }
        }

        return true;
    }

    @Override
    public boolean delete(Reservation reservation) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.getConnection();

            ps = connection.prepareStatement(SQL_DELETE_RESERVATION);
            ps.setLong(1, reservation.getId());

            int result = ps.executeUpdate();

            if (result <= 0) {
                return false;
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("ConnectionPool or SQL error: ", e);

        } finally {
            if (cp != null && ps != null) {
                cp.closePreparedStatement(ps);
                logger.debug("Prepared statement closed");
            }
            if (cp != null && connection != null) {
                cp.releaseConnection(connection);
                logger.debug("Connection released");
            }
        }

        return true;
    }

}
