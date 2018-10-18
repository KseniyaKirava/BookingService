package by.htp.kirova.task2.java.dao.daoentity;

import by.htp.kirova.task2.java.connectionpool.ConnectionPool;
import by.htp.kirova.task2.java.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.dao.DateConverter;
import by.htp.kirova.task2.java.dao.HelperDAO;
import by.htp.kirova.task2.java.entity.User;
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
public class ViewHelperDAO implements HelperDAO {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(ViewHelperDAO.class);

    /**
     * Constant string which represents query to create request.
     */
    private static final String SQL_SHOW_REQUESTS = "SELECT q.id, q.room_capacity, q.checkin_date, " +
            "q.checkout_date, q.room_class, r.id FROM requests q LEFT OUTER JOIN reservations r " +
            "ON q.id=r.requests_id WHERE r.users_username = ?";

    @Override
    public List<ArrayList> showRequestsByUser(User user) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;

        String sql = SQL_SHOW_REQUESTS + user.getUsername();

        List<ArrayList> result = new ArrayList<>();

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                ArrayList<String> list = new ArrayList<>();
                list.add(String.valueOf(resultSet.getLong("q.id")));
                list.add(String.valueOf(resultSet.getInt("q.room_capacity")));
                list.add(DateConverter.convertDateToString(resultSet.getLong("q.checkin_date")));
                list.add(DateConverter.convertDateToString(resultSet.getLong("q.checkout_date")));
                list.add(resultSet.getString("q.room_class"));

                String s;
                s = String.valueOf(resultSet.getLong("r.id"));
                if (s == null || s.isEmpty()) {
                    list.add("");
                }
                result.add(list);
            }

        } catch (SQLException e) {
            LOGGER.error("SQL error: ", e);

        } catch (ConnectionPoolException e) {
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

        } finally {
            if (cp != null && connection != null) {
                cp.closeStatement(statement);
                cp.returnConnection(connection);
            }
        }

        return result;
    }

}
