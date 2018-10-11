package by.htp.kirova.task2.java.service.entityservice;

import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.dao.DAOFactory;
import by.htp.kirova.task2.java.dao.GenericDAO;
import by.htp.kirova.task2.java.entity.User;
import by.htp.kirova.task2.java.service.GenericService;
import by.htp.kirova.task2.java.service.ServiceException;

import java.util.List;

/**
 * Contains methods which provide application logic to work with users.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class UserServiceImpl implements GenericService<User> {

    @Override
    public boolean create(User user) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        GenericDAO<User> userDAO = daoFactory.getUserDAO();

        boolean result;

        try {
            result = userDAO.create(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;

    }

    @Override
    public List<User> read(String where) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        GenericDAO<User> userDAO = daoFactory.getUserDAO();

        List<User> list;

        try {
            list = userDAO.read(where);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return list;
    }

    @Override
    public boolean update(User user) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        GenericDAO<User> userDAO = daoFactory.getUserDAO();

        boolean result;

        try {
            result = userDAO.update(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public boolean delete(User user) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        GenericDAO<User> userDAO = daoFactory.getUserDAO();

        boolean result;

        try {
            result = userDAO.delete(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }
}
