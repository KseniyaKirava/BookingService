package by.htp.kirova.task2.java.dao.daoentity;

import by.htp.kirova.task2.java.connectionpool.ConnectionPool;
import by.htp.kirova.task2.java.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.java.dao.DAO;
import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.entity.Facility;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Provides Facilities with an opportunity to retrieve, change and delete data from
 * the relevant database table.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class FacilityDAOImpl implements DAO<Facility> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    /**
     * Constant string which represents query to create facility.
     */
    private static final String SQL_CREATE_FACILITY = "INSERT INTO `facilities`(`name`) VALUES ('%s')";

    /**
     * Constant string which represents query to update facility.
     */
    private static final String SQL_UPDATE_FACILITY = "UPDATE `facilities` SET `name`='%s' WHERE `id` = %d";

    /**
     * Constant string which represents query to select all facilities.
     */
    private static final String SQL_SELECT_FROM_FACILITIES = "SELECT * FROM `facilities` ";

    /**
     * Constant string which represents query to delete facility.
     */
    private static final String SQL_DELETE_FACILITY_BY_ID = "DELETE FROM `facilities` WHERE `id` = %d";



    @Override
    public boolean create(Facility facility) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = String.format(Locale.US, SQL_CREATE_FACILITY, facility.getName());

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            int id = executeUpdate(connection, sql, true);
            if (id > 0) {
                facility.setId(id);
                connection.setAutoCommit(false);
                connection.commit();
                return true;
            }

        } catch (ConnectionPoolException | SQLException e) {
            rollbackConncetion(connection);
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

        } finally {
            setAutoCommitTrueAndReturnConnection(cp, connection);
        }

        return false;
    }

    /**
     * Returns facility which has a specific Id.
     *
     * @param id parameter which specifies Id.
     * @return facility which has a specific Id.
     * @throws DAOException
     */
    public Facility findById(long id) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        List<Facility> list;

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            list = findAll("WHERE `id` =" + id);
            if (list.size() == 1) {
                return list.get(0);
            }

        } catch (ConnectionPoolException e) {
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

        } finally {
            if (connection != null) {
                cp.returnConnection(connection);
            }
        }

        return null;
    }


    @Override
    public List<Facility> findAll(String where) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = String.format(Locale.US, SQL_SELECT_FROM_FACILITIES + where);

        List<Facility> list = new ArrayList<>();

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new Facility(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                ));
            }

        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

        } finally {
            if (connection != null) {
                cp.returnConnection(connection);
            }
        }

        return list;
    }


    @Override
    public boolean update(Facility facility) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = String.format(Locale.US, SQL_UPDATE_FACILITY, facility.getName(), facility.getId());

        int result;

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            result = executeUpdate(connection, sql, false);

            connection.setAutoCommit(false);
            connection.commit();

        } catch (ConnectionPoolException | SQLException e) {
            rollbackConncetion(connection);
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

        } finally {
            setAutoCommitTrueAndReturnConnection(cp, connection);
        }

        return result == 1;
    }


    /**
     * Deletes object based on its Id.
     *
     * @param id parameter which specifies Id.
     * @return boolean {@code true} in case of success and {@code false} otherwise.
     * @throws DAOException
     */
    public boolean deleteById(long id) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = String.format(Locale.US, SQL_DELETE_FACILITY_BY_ID, id);

        int result;

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            result = executeUpdate(connection, sql, false);

        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

        } finally {
            if (connection != null) {
                cp.returnConnection(connection);
            }
        }

        return result == 1;
    }


    @Override
    public boolean delete(Facility facility) throws DAOException {
        long id = facility.getId();
        return deleteById(id);
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

    private void rollbackConncetion(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException z) {
            LOGGER.error("Connection rollback operation error: ", z);
        }
    }


}













