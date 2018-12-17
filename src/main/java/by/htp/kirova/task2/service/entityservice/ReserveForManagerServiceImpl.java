package by.htp.kirova.task2.service.entityservice;

import by.htp.kirova.task2.dao.BookingDAO;
import by.htp.kirova.task2.dao.DAOException;
import by.htp.kirova.task2.dao.DAOFactory;
import by.htp.kirova.task2.entity.ReserveForManager;
import by.htp.kirova.task2.service.BookingService;
import by.htp.kirova.task2.service.ServiceException;

import java.util.List;

/**
 * Contains methods which provide application to work with Reserve for Manager class.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class ReserveForManagerServiceImpl implements BookingService<ReserveForManager> {


    @Override
    public boolean create(ReserveForManager entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<ReserveForManager> read(String where) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<ReserveForManager> reserveForManagerDAO = daoFactory.getReserveForManagerDAO();

        //no validation for internal queries from DB

        List<ReserveForManager> list;

        try {
            list = reserveForManagerDAO.read(where);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return list;
    }

    @Override
    public boolean update(ReserveForManager entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(ReserveForManager entity) {
        throw new UnsupportedOperationException();
    }
}
