package by.htp.kirova.task2.service.entityservice;

import by.htp.kirova.task2.dao.BookingDAO;
import by.htp.kirova.task2.dao.DAOException;
import by.htp.kirova.task2.dao.DAOFactory;
import by.htp.kirova.task2.entity.Request;
import by.htp.kirova.task2.service.BookingService;
import by.htp.kirova.task2.service.ServiceException;

import java.util.List;

/**
 * Contains methods which provide application logic to work with requests.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class RequestServiceImpl implements BookingService<Request> {

    @Override
    public boolean create(Request request) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Request> requestDAO = daoFactory.getRequestDAO();

        boolean result;

        try {
            result = requestDAO.create(request);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;

    }

    @Override
    public List<Request> read(String where) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Request> requestDAO = daoFactory.getRequestDAO();

        List<Request> list;

        try {
            list = requestDAO.read(where);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return list;

    }

    @Override
    public boolean update(Request request) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Request> requestDAO = daoFactory.getRequestDAO();

        boolean result;

        try {
            result = requestDAO.update(request);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public boolean delete(Request request) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Request> requestDAO = daoFactory.getRequestDAO();

        boolean result;

        try {
            result = requestDAO.delete(request);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }
}
