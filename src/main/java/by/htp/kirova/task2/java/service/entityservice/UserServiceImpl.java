package by.htp.kirova.task2.java.service.entityservice;

import by.htp.kirova.task2.java.entity.User;
import by.htp.kirova.task2.java.service.GenericService;
import by.htp.kirova.task2.java.service.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Contains methods which provide application logic to work with users.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class UserServiceImpl implements GenericService<User> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);


    @Override
    public boolean create(User entity) throws ServiceException {
        return false;
    }

    @Override
    public List<User> findAll(String where) throws ServiceException {
        return null;
    }

    @Override
    public boolean update(User entity) throws ServiceException {
        return false;
    }

    @Override
    public boolean delete(User entity) throws ServiceException {
        return false;
    }
}
