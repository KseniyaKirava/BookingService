package by.htp.kirova.task2.dao.daoentity;


import by.htp.kirova.task2.dao.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.dao.connectionpool.ConnectionPoolImpl;
import by.htp.kirova.task2.dao.DAOException;
import by.htp.kirova.task2.dao.BookingDAO;
import by.htp.kirova.task2.entity.Authority;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Provides Facilities with an opportunity to retrieve, change and delete data from
 * the relevant database table.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class AuthorityDAOImpl implements BookingDAO<Authority> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(AuthorityDAOImpl.class);

    /**
     * Constant string which represents query to create authority.
     */
    private static final String SQL_CREATE_AUTHORITY = "INSERT INTO authorities(authority, username, enabled) " +
            "VALUES (?, ?, ?)";

    /**
     * Constant string which represents query to select authorities.
     */
    private static final String SQL_SELECT_FROM_AUTHORITIES = "SELECT * FROM authorities ";

    /**
     * Constant string which represents query to update authority.
     */
    private static final String SQL_UPDATE_AUTHORITY = "UPDATE authorities SET authority= ? , enabled= ? " +
            "WHERE username= ?";

    /**
     * Constant string which represents query to delete authority.
     */
    private static final String SQL_DELETE_AUTHORITY = "DELETE FROM authorities WHERE username= ?";


    @Override
    public boolean create(Authority authority) throws DAOException {
        ConnectionPoolImpl cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        int result;

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.extractConnection();

            ps = connection.prepareStatement(SQL_CREATE_AUTHORITY);
            ps.setString(1, authority.getAuthority());
            ps.setString(2, authority.getUsername());
            ps.setBoolean(3, authority.isEnabled());

            result = ps.executeUpdate();

            connection.setAutoCommit(false);
            connection.commit();

        } catch (ConnectionPoolException | SQLException e) {
            if (cp != null) {
                cp.rollbackConnection(connection);
            }
            LOGGER.error("ConnectionPoolImpl error: ", e);
            throw new DAOException("ConnectionPoolImpl error: ", e);

        } finally {
            if (cp != null && connection != null && ps != null) {
                cp.setAutoCommitTrue(connection);
                cp.closePreparedStatement(ps);
                cp.returnConnection(connection);
            }
        }

        return result == 1;

    }

    @Override
    public List<Authority> read(String where) throws DAOException {
        ConnectionPoolImpl cp = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;

        String sql = SQL_SELECT_FROM_AUTHORITIES + where;

        List<Authority> list = new ArrayList<>();

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.extractConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new Authority(
                        resultSet.getString("authority"),
                        resultSet.getString("username"),
                        resultSet.getBoolean("enabled")
                ));
            }

        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.error("ConnectionPoolImpl error: ", e);
            throw new DAOException("ConnectionPoolImpl error: ", e);

        } finally {
            if (cp != null && connection != null) {
                cp.closeStatement(statement);
                cp.returnConnection(connection);
            }
        }

        return list;
    }

    @Override
    public boolean update(Authority authority) throws DAOException {
        ConnectionPoolImpl cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        int result;

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.extractConnection();

            ps = connection.prepareStatement(SQL_UPDATE_AUTHORITY);
            ps.setString(1, authority.getAuthority());
            ps.setBoolean(2, authority.isEnabled());
            ps.setString(3, authority.getUsername());

            result = ps.executeUpdate();

            connection.setAutoCommit(false);
            connection.commit();

        } catch (ConnectionPoolException | SQLException e) {
            if (cp != null) {
                cp.rollbackConnection(connection);
            }
            LOGGER.error("ConnectionPoolImpl error: ", e);
            throw new DAOException("ConnectionPoolImpl error: ", e);

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
    public boolean delete(Authority authority) throws DAOException {
        ConnectionPoolImpl cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        int result;

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.extractConnection();

            ps = connection.prepareStatement(SQL_DELETE_AUTHORITY);
            ps.setString(1, authority.getUsername());

            result = ps.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.error("ConnectionPoolImpl error: ", e);
            throw new DAOException("ConnectionPoolImpl error: ", e);

        } finally {
            if (cp != null && connection != null && ps!= null){
                cp.closePreparedStatement(ps);
                cp.returnConnection(connection);
            }
        }

        return result == 1;
    }

}

