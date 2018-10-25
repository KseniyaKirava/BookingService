package by.htp.kirova.task2.java.dao.daoentity;

import by.htp.kirova.task2.java.connectionpool.ConnectionPool;
import by.htp.kirova.task2.java.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.dao.GenericDAO;
import by.htp.kirova.task2.java.entity.Request;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides Request with an opportunity to retrieve, change and delete data from
 * the relevant database table.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class RequestDAOImpl implements GenericDAO<Request> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(RequestDAOImpl.class);

    /**
     * Constant string which represents query to create request.
     */
    private static final String SQL_CREATE_REQUEST = "INSERT INTO requests(room_capacity, checkin_date," +
            " checkout_date, room_class, enabled, users_username) VALUES (?, ?, ?, ?, ?, ?)";

    /**
     * Constant string which represents query to select all requests.
     */
    private static final String SQL_SELECT_FROM_REQUESTS = "SELECT * FROM requests ";

    /**
     * Constant string which represents query to update request.
     */
    private static final String SQL_UPDATE_REQUEST = "UPDATE requests SET room_capacity= ?," +
            "checkin_date= ?,checkout_date= ?,room_class= ?, enabled= ?, users_username=? WHERE id= ?";

    /**
     * Constant string which represents query to delete request.
     */
    private static final String SQL_DELETE_REQUEST = "DELETE FROM requests WHERE id = ?";


    @Override
    public boolean create(Request request) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            ps = connection.prepareStatement(SQL_CREATE_REQUEST, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, request.getRoom_capacity());
            ps.setLong(2, request.getCheckin_date());
            ps.setLong(3, request.getCheckout_date());
            ps.setString(4, request.getRoom_class());
            ps.setBoolean(5,request.isEnabled());
            ps.setString(6, request.getUsers_username());

            int result = ps.executeUpdate();
            int id = 0;

            if (result > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                }
            }

            if (id > 0) {
                request.setId(id);

                connection.setAutoCommit(false);
                connection.commit();
                return true;
            }

        } catch (ConnectionPoolException | SQLException e) {
            if (cp != null) {
                cp.rollbackConnection(connection);
            }
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

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
    public List<Request> read(String where) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;

        String sql = SQL_SELECT_FROM_REQUESTS + where;

        List<Request> list = new ArrayList<>();

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new Request(
                        resultSet.getLong("id"),
                        resultSet.getInt("room_capacity"),
                        resultSet.getLong("checkin_date"),
                        resultSet.getLong("checkout_date"),
                        resultSet.getString("room_class"),
                        resultSet.getBoolean("enabled"),
                        resultSet.getString("users_username")
                ));
            }

        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

        } finally {
            if (cp != null && connection != null) {
                cp.closeStatement(statement);
                cp.returnConnection(connection);
            }
        }

        return list;
    }

    @Override
    public boolean update(Request request) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        int result;

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            ps = connection.prepareStatement(SQL_UPDATE_REQUEST);
            ps.setInt(1, request.getRoom_capacity());
            ps.setLong(2, request.getCheckin_date());
            ps.setLong(3, request.getCheckout_date());
            ps.setString(4, request.getRoom_class());
            ps.setBoolean(5,request.isEnabled());
            ps.setString(6, request.getUsers_username());
            ps.setLong(7, request.getId());

            result = ps.executeUpdate();

            connection.setAutoCommit(false);
            connection.commit();

        } catch (ConnectionPoolException | SQLException e) {
            if (cp != null) {
                cp.rollbackConnection(connection);
            }
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

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
    public boolean delete(Request request) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        int result;

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            ps = connection.prepareStatement(SQL_DELETE_REQUEST);
            ps.setLong(1, request.getId());

            result = ps.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

        } finally {
            if (cp != null && connection != null && ps!= null){
                cp.closePreparedStatement(ps);
                cp.returnConnection(connection);
            }
        }

        return result == 1;
    }

}
