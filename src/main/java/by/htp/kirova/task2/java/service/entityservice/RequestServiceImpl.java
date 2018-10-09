package by.htp.kirova.task2.java.service.entityservice;

import by.htp.kirova.task2.java.entity.Request;
import by.htp.kirova.task2.java.service.GenericService;
import by.htp.kirova.task2.java.service.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Contains methods which provide application logic to work with requests.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class RequestServiceImpl implements GenericService<Request> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(RequestServiceImpl.class);

    @Override
    public boolean create(Request entity) throws ServiceException {
        return false;
    }

    @Override
    public Request findById(long id) throws ServiceException {
        return null;
    }

    @Override
    public List<Request> findAll(String where) throws ServiceException {
        return null;
    }

    @Override
    public boolean update(Request entity) throws ServiceException {
        return false;
    }

    @Override
    public boolean deleteById(long id) throws ServiceException {
        return false;
    }

    @Override
    public boolean delete(Request entity) throws ServiceException {
        return false;
    }
}
