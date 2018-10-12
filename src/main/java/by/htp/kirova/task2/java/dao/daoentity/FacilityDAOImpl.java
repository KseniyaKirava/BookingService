package by.htp.kirova.task2.java.dao.daoentity;

import by.htp.kirova.task2.java.connectionpool.ConnectionPool;
import by.htp.kirova.task2.java.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.java.dao.GenericDAO;
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
 * Provides Facility with an opportunity to retrieve, change and delete data from
 * the relevant database table.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class FacilityDAOImpl implements GenericDAO<Facility> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(FacilityDAOImpl.class);

    /**
     * Constant string which represents query to create facility.
     */
    private static final String SQL_CREATE_FACILITY = "INSERT INTO `facilities`(`name`, `enable`) VALUES ('%s', %b)";

    /**
     * Constant string which represents query to select all facilities.
     */
    private static final String SQL_SELECT_FROM_FACILITIES = "SELECT * FROM `facilities` ";

    /**
     * Constant string which represents query to update facility.
     */
    private static final String SQL_UPDATE_FACILITY = "UPDATE `facilities` SET `name`='%s', `enable`=%b WHERE `id` = %d";


    /**
     * Constant string which represents query to delete facility.
     */
    private static final String SQL_DELETE_FACILITY = "DELETE FROM `facilities` WHERE `id` = %d";



    @Override
    public boolean create(Facility facility) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = String.format(Locale.US, SQL_CREATE_FACILITY, facility.getName(), facility.isEnable());

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            int id = cp.executeUpdate(connection, sql, true);
            if (id > 0) {
                facility.setId(id);
                connection.setAutoCommit(false);
                connection.commit();
                return true;
            }

        } catch (ConnectionPoolException | SQLException e) {
            cp.rollbackConnection(connection);
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

        } finally {
            if (cp != null && connection != null) {
                cp.setAutoCommitTrue(connection);
                cp.returnConnection(connection);
            }
        }

        return false;
    }

    @Override
    public List<Facility> read(String where) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String sql = SQL_SELECT_FROM_FACILITIES + where;

        List<Facility> list = new ArrayList<>();

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new Facility(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getBoolean("enable")
                ));
            }

        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

        } finally {
            if (cp != null && connection != null) {
                cp.closeResultSet(resultSet);
                cp.closeStatement(statement);
                cp.returnConnection(connection);
            }
        }

        return list;
    }


    @Override
    public boolean update(Facility facility) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = String.format(Locale.US, SQL_UPDATE_FACILITY, facility.getName(), facility.isEnable(),
                facility.getId());

        int result;

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            result = cp.executeUpdate(connection, sql, false);

            connection.setAutoCommit(false);
            connection.commit();

        } catch (ConnectionPoolException | SQLException e) {
            cp.rollbackConnection(connection);
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

        } finally {
            if (cp != null && connection != null) {
                cp.setAutoCommitTrue(connection);
                cp.returnConnection(connection);
            }
        }

        return result == 1;
    }

    @Override
    public boolean delete(Facility facility) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;

        String sql = String.format(Locale.US, SQL_DELETE_FACILITY, facility.getId());

        int result;

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            result = cp.executeUpdate(connection, sql, false);

        } catch (ConnectionPoolException e) {
            LOGGER.error("ConnectionPool error: ", e);
            throw new DAOException("ConnectionPool error: ", e);

        } finally {
            if (cp != null && connection != null) {
                cp.returnConnection(connection);
            }
        }

        return result == 1;
    }

}













