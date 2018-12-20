package by.htp.kirova.task2.dao.daoentity;

import by.htp.kirova.task2.dao.BookingDAO;
import by.htp.kirova.task2.dao.ConnectionPool;
import by.htp.kirova.task2.dao.DAOException;
import by.htp.kirova.task2.dao.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.dao.connectionpool.ConnectionPoolImpl;
import by.htp.kirova.task2.entity.Facility;
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
public class FacilityDAOImpl implements BookingDAO<Facility> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger logger = Logger.getLogger(FacilityDAOImpl.class);

    /**
     * The unique identification number constant.
     */
    private final static String ID = "id";

    /**
     * The name of facility constant.
     */
    private final static String NAME = "name";

    /**
     * The enabled state constant.
     */
    private final static String ENABLED = "enabled";

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
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.getConnection();
            connection.setAutoCommit(false);

            ps = connection.prepareStatement(SQL_CREATE_FACILITY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, facility.getName());
            ps.setBoolean(2, facility.isEnabled());

            int result = ps.executeUpdate();

            if (result <= 0) {
                return false;
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                facility.setId(id);
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
    public List<Facility> read(String where) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;

        String sql = SQL_SELECT_FROM_FACILITIES + where;

        List<Facility> list = new ArrayList<>();

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.getConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                list.add(new Facility(
                        resultSet.getLong(ID),
                        resultSet.getString(NAME),
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
    public boolean update(Facility facility) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.getConnection();
            connection.setAutoCommit(false);

            ps = connection.prepareStatement(SQL_UPDATE_FACILITY);
            ps.setString(1, facility.getName());
            ps.setBoolean(2, facility.isEnabled());
            ps.setLong(3, facility.getId());

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
    public boolean delete(Facility facility) throws DAOException {
        ConnectionPool cp = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            cp = ConnectionPoolImpl.getInstance();
            connection = cp.getConnection();

            ps = connection.prepareStatement(SQL_DELETE_FACILITY);
            ps.setLong(1, facility.getId());

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













