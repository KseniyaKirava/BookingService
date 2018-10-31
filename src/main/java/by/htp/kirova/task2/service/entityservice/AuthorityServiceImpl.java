package by.htp.kirova.task2.service.entityservice;



import by.htp.kirova.task2.dao.BookingDAO;
import by.htp.kirova.task2.entity.Authority;
import by.htp.kirova.task2.dao.DAOException;
import by.htp.kirova.task2.dao.DAOFactory;
import by.htp.kirova.task2.service.BookingService;
import by.htp.kirova.task2.service.ServiceException;

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

        boolean result;

        try {
            result = authorityDAO.create(authority);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public List<Authority> read(String where) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Authority> authorityDAO = daoFactory.getAuthorityDAO();

        List<Authority> list;

        try {
            list = authorityDAO.read(where);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return list;
    }

    @Override
    public boolean update(Authority authority) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Authority> authorityDAO = daoFactory.getAuthorityDAO();

        boolean result;

        try {
            result = authorityDAO.update(authority);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public boolean delete(Authority authority) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Authority> authorityDAO = daoFactory.getAuthorityDAO();

        boolean result;

        try {
            result = authorityDAO.delete(authority);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }
}
