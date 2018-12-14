package by.htp.kirova.task2.service.entityservice;

import by.htp.kirova.task2.dao.BookingDAO;
import by.htp.kirova.task2.dao.DAOException;
import by.htp.kirova.task2.dao.DAOFactory;
import by.htp.kirova.task2.entity.User;
import by.htp.kirova.task2.service.BookingService;
import by.htp.kirova.task2.service.ServiceException;
import by.htp.kirova.task2.service.validation.Validator;
import by.htp.kirova.task2.service.util.UserService;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Contains methods which provide application to work with users.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class UserServiceImpl implements BookingService<User> {


    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);


    @Override
    public boolean create(User user) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<User> userDAO = daoFactory.getUserDAO();

        Validator validator = Validator.getInstance();

        if (!validator.checkUsername(user.getUsername()) ||
                !validator.checkEmail(user.getEmail()) ||
                !validator.checkPassword(user.getPassword()) ||
                !validator.checkName(user.getFirstName()) ||
                !validator.checkName(user.getLastName()) ||
                !validator.checkMiddleName(user.getMiddleName()) ||
                !user.isEnabled()) {
            logger.debug("Validation of user data ended with error");
            return false;
        }

        String hashPassword = UserService.getHashPassword(user.getPassword());
        user.setPassword(hashPassword);

        boolean result;

        try {
            result = userDAO.create(user);
        } catch (DAOException e) {
            throw new ServiceException("Can't process the update request correctly: ", e);
        }

        return result;

    }

    @Override
    public List<User> read(String where) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<User> userDAO = daoFactory.getUserDAO();

        //no validation for internal queries from DB

        List<User> list;

        try {
            list = userDAO.read(where);
        } catch (DAOException e) {
            throw new ServiceException("Can't process the update request correctly: ", e);
        }

        return list;
    }

    @Override
    public boolean update(User user) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<User> userDAO = daoFactory.getUserDAO();

        Validator validator = Validator.getInstance();

        if (!validator.checkUsername(user.getUsername()) ||
                !validator.checkEmail(user.getEmail()) ||
                !validator.checkName(user.getFirstName()) ||
                !validator.checkName(user.getLastName()) ||
                !validator.checkMiddleName(user.getMiddleName())) {
            logger.debug("Validation of user data ended with error");
            return false;
        }

        boolean result;

        try {
            result = userDAO.update(user);
        } catch (DAOException e) {
            throw new ServiceException("Can't process the update request correctly: ", e);
        }

        return result;
    }

    @Override
    public boolean delete(User user) throws ServiceException {

        // Method is not used.
        // UPDATE is used to remove objects from the database with flag enabled = false.

        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<User> userDAO = daoFactory.getUserDAO();

        boolean result;

        try {
            result = userDAO.delete(user);
        } catch (DAOException e) {
            throw new ServiceException("Can't process the update request correctly: ", e);
        }

        return result;
    }
}
