package by.htp.kirova.task2.service.entityservice;


import by.htp.kirova.task2.dao.BookingDAO;
import by.htp.kirova.task2.dao.DAOException;
import by.htp.kirova.task2.dao.DAOFactory;
import by.htp.kirova.task2.entity.Authority;
import by.htp.kirova.task2.service.BookingService;
import by.htp.kirova.task2.service.ServiceException;
import by.htp.kirova.task2.service.validation.Validator;

import java.util.List;

/**
 * Contains methods which provide application logic to work with authorities.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class AuthorityServiceImpl implements BookingService<Authority> {

    @Override
    public boolean create(Authority authority) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Authority> authorityDAO = daoFactory.getAuthorityDAO();
        Validator validator = Validator.getInstance();

        if (!validator.checkAuthority(authority.getAuthority()) ||
                !validator.checkUsername(authority.getUsername()) || !authority.isEnabled()) {
            return false;
        }

        boolean result;

        try {
            result = authorityDAO.create(authority);
        } catch (DAOException e) {
            throw new ServiceException("Can't process the create request correctly: ", e);
        }

        return result;
    }

    @Override
    public List<Authority> read(String where) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Authority> authorityDAO = daoFactory.getAuthorityDAO();

        //no validation for internal queries from DB

        List<Authority> list;

        try {
            list = authorityDAO.read(where);
        } catch (DAOException e) {
            throw new ServiceException("Can't process the read request correctly: ", e);
        }

        return list;
    }

    @Override
    public boolean update(Authority authority) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Authority> authorityDAO = daoFactory.getAuthorityDAO();
        Validator validator = Validator.getInstance();

        if (!validator.checkAuthority(authority.getAuthority()) ||
                !validator.checkUsername(authority.getUsername())) {
            return false;
        }

        boolean result;

        try {
            result = authorityDAO.update(authority);
        } catch (DAOException e) {
            throw new ServiceException("Can't process the update request correctly: ", e);
        }

        return result;
    }

    @Override
    public boolean delete(Authority authority) throws ServiceException {

        // Method is not used.
        // UPDATE is used to remove objects from the database with flag enabled = false.

        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Authority> authorityDAO = daoFactory.getAuthorityDAO();

        boolean result;

        try {
            result = authorityDAO.delete(authority);
        } catch (DAOException e) {
            throw new ServiceException("Can't process the delete request correctly: ", e);
        }

        return result;
    }
}
