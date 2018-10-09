package by.htp.kirova.task2.java.dao.daoentity;

import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.dao.GenericDAO;
import by.htp.kirova.task2.java.entity.Request;
import org.apache.log4j.Logger;

import java.util.List;

public class RequestDAOImpl implements GenericDAO<Request> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(RequestDAOImpl.class);

    @Override
    public boolean create(Request entity) throws DAOException {
        return false;
    }

    @Override
    public List<Request> findAll(String where) throws DAOException {
        return null;
    }

    @Override
    public boolean update(Request entity) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(Request entity) throws DAOException {
        return false;
    }
}
