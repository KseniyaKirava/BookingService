package by.htp.kirova.task2.java.dao.daoentity;

import by.htp.kirova.task2.java.connectionpool.ConnectionPool;
import by.htp.kirova.task2.java.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.dao.GenericDAO;
import by.htp.kirova.task2.java.entity.Facility;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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
    private static final String SQL_CREATE_FACILITY = "INSERT INTO facilities(id, name, enabled) VALUES (?, ?, ?)";

    /**
     * Constant string which represents query to select all facilities.
     */
    private static final String SQL_SELECT_FROM_FACILITIES = "SELECT * FROM facilities ";

    /**
     * Constant string which represents query to update facility.
     */
    private static final String SQL_UPDATE_FACILITY = "UPDATE facilities SET name= ?, enabled=? WHERE id = ?";


    /**
     * Constant string which represents query to delete facility.
     */
    private static final String SQL_DELETE_FACILITY = "DELETE FROM facilities WHERE id = ?";


    @Override
    public boolean create(Facility facility) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            ps = connection.prepareStatement(SQL_CREATE_FACILITY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, facility.getName());
            ps.setBoolean(2, facility.isEnabled());

            int result = ps.executeUpdate();
            int id = 0;

            if (result > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                }
            }

            if (id > 0) {
                facility.setId(id);

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
    public List<Facility> read(String where) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;

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
                        resultSet.getBoolean("enabled")
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
    public boolean update(Facility facility) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        int result;

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            ps = connection.prepareStatement(SQL_UPDATE_FACILITY);
            ps.setString(1, facility.getName());
            ps.setBoolean(2, facility.isEnabled());
            ps.setLong(3, facility.getId());

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
    public boolean delete(Facility facility) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        int result;

        try {
            cp = ConnectionPool.getInstance();
            connection = cp.extractConnection();

            ps = connection.prepareStatement(SQL_DELETE_FACILITY);
            ps.setLong(1, facility.getId());

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













