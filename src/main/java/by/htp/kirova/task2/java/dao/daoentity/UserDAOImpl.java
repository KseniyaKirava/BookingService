package by.htp.kirova.task2.java.dao.daoentity;


import by.htp.kirova.task2.java.connectionpool.ConnectionPool;
import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.dao.GenericDAO;
import by.htp.kirova.task2.java.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


/**
 * Provides User with an opportunity to retrieve, change and delete data from
 * the relevant database table.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class UserDAOImpl implements GenericDAO<User> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class);

    /**
     * Constant string which represents query to create user.
     */
    private static final String SQL_CREATE_USER = "";

    /**
     * Constant string which represents query to select all users.
     */
    private static final String SQL_SELECT_FROM_USERS = "SELECT * FROM `users` ";

    /**
     * Constant string which represents query to update user.
     */
    private static final String SQL_UPDATE_USER = "";

    /**
     * Constant string which represents query to delete user.
     */
    private static final String SQL_DELETE_USER = "DELETE FROM `users` WHERE ";



    @Override
    public boolean create(User entity) throws DAOException {
        return false;
    }

    @Override
    public List<User> read(String where) throws DAOException {
        return null;
    }

    @Override
    public boolean update(User entity) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(User entity) throws DAOException {
        return false;
    }



    private int executeUpdate(Connection connection, String sql, boolean generateId) throws SQLException {
        Statement statement = connection.createStatement();
        int result = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

        if (result > 0 && generateId) {
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        }

        return result;
    }


    private void setAutoCommitTrueAndReturnConnection(ConnectionPool cp, Connection connection) {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                LOGGER.error("Connection set autocommit \"true\" operation error: ", e);
            }

            cp.returnConnection(connection);
        }
    }

    private void rollbackConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException z) {
            LOGGER.error("Connection rollback operation error: ", z);
        }
    }
}
