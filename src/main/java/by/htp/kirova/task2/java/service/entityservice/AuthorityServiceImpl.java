package by.htp.kirova.task2.java.service.entityservice;

import by.htp.kirova.task2.java.entity.Authority;
import by.htp.kirova.task2.java.service.GenericService;
import by.htp.kirova.task2.java.service.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Contains methods which provide application logic to work with authorities.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class AuthorityServiceImpl implements GenericService<Authority> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(AuthorityServiceImpl.class);

    @Override
    public Authority findById(long id) throws ServiceException {
        return null;
    }

    @Override
    public boolean deleteById(long id) throws ServiceException {
        return false;
    }

    @Override
    public boolean create(Authority entity) throws ServiceException {
        return false;
    }

    @Override
    public List<Authority> findAll(String where) throws ServiceException {
        return null;
    }

    @Override
    public boolean update(Authority entity) throws ServiceException {
        return false;
    }

    @Override
    public boolean delete(Authority entity) throws ServiceException {
        return false;
    }
}
