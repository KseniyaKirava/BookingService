package by.htp.kirova.task2.dao.daoentity;


import by.htp.kirova.task2.dao.BookingDAO;
import by.htp.kirova.task2.dao.ConnectionPool;
import by.htp.kirova.task2.dao.DAOException;
import by.htp.kirova.task2.dao.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.dao.connectionpool.ConnectionPoolImpl;
import by.htp.kirova.task2.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Provides User with an opportunity to retrieve, change and delete data from
 * the relevant database table.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class UserDAOImpl implements BookingDAO<User> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);


    /**
     * The unique identification name constant.
     */
    private final static String USERNAME = "username";

    /**
     * The email constant.
     */
    private final static String EMAIL = "email";

    /**
     * The password constant.
     */
    private final static String PASSWORD = "password";

    /**
     * The first name constant.
     */
    private final static String FIRST_NAME = "first_name";

    /**
     * The last name constant.
     */
    private final static String LAST_NAME = "last_name";

    /**
     * The middle name constant.
     */
    private final static String MIDDLE_NAME = "middle_name";

    /**
     * The enabled state constant.
     */
    private final static String ENABLED = "enabled";



    /**
     * Constant string which represents query to create user.
     */
    private static final String SQL_CREATE_USER = "INSERT INTO users(username, email, password, first_name, " +
            "last_name, middle_name, enabled) VALUES (?, ?, ?, ?, ?, ?, ?)";

    /**
     * Constant string which represents query to select all users.
     */
    private static final String SQL_SELECT_FROM_USERS = "SELECT * FROM users ";

    /**
     * Constant string which represents query to update user.
     */
    private static final String SQL_UPDATE_USER = "UPDATE users SET email= ?, password= ?, first_name= ?," +
            " last_name= ?, middle_name= ?, enabled= ? WHERE username= ? ";

    /**
     * Constant string which represents query to delete user.
     */
    private static final String SQL_DELETE_USER = "DELETE FROM users WHERE username= ?";


    @Override
    public boolean create(User user) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.getConnection();
            connection.setAutoCommit(false);

            ps = connection.prepareStatement(SQL_CREATE_USER);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getFirstName());
            ps.setString(5, user.getLastName());
            ps.setString(6, user.getMiddleName());
            ps.setBoolean(7, user.isEnabled());

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
    public List<User> read(String where) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;

        String sql = SQL_SELECT_FROM_USERS + where;

        List<User> list = new ArrayList<>();

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.getConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new User(
                        resultSet.getString(USERNAME),
                        resultSet.getString(EMAIL),
                        resultSet.getString(PASSWORD),
                        resultSet.getString(FIRST_NAME),
                        resultSet.getString(LAST_NAME),
                        resultSet.getString(MIDDLE_NAME),
                        resultSet.getBoolean(ENABLED)
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
    public boolean update(User user) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.getConnection();
            connection.setAutoCommit(false);

            ps = connection.prepareStatement(SQL_UPDATE_USER);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getMiddleName());
            ps.setBoolean(6, user.isEnabled());
            ps.setString(7, user.getUsername());

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
    public boolean delete(User user) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.getConnection();

            ps = connection.prepareStatement(SQL_DELETE_USER);
            ps.setString(1, user.getUsername());

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
