package by.htp.kirova.task2.java.dao.daoentity;

import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.dao.GenericDAO;
import by.htp.kirova.task2.java.entity.Request;
import org.apache.log4j.Logger;

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
    private static final String SQL_CREATE_REQUEST = "";

    /**
     * Constant string which represents query to select all requests.
     */
    private static final String SQL_SELECT_FROM_REQUESTS = "SELECT * FROM `requests` ";

    /**
     * Constant string which represents query to update request.
     */
    private static final String SQL_UPDATE_REQUEST = "";


    /**
     * Constant string which represents query to delete request.
     */
    private static final String SQL_DELETE_REQUEST_BY_ID = "";


    @Override
    public boolean create(Request entity) throws DAOException {
        return false;
    }

    public Request findById(long id) throws DAOException {
        return null;
    }

    @Override
    public List<Request> findAll(String where) throws DAOException {
        return null;
    }

    @Override
    public boolean update(Request entity) throws DAOException {
        return false;
    }

    public boolean deleteById(long id) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(Request entity) throws DAOException {
        return false;
    }
}
