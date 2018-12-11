package by.htp.kirova.task2.service.entityservice;

import by.htp.kirova.task2.dao.BookingDAO;
import by.htp.kirova.task2.dao.DAOException;
import by.htp.kirova.task2.dao.DAOFactory;
import by.htp.kirova.task2.entity.ReserveByUser;
import by.htp.kirova.task2.service.BookingService;
import by.htp.kirova.task2.service.ServiceException;
import java.util.List;


/**
 * Contains methods which provide application logic to work with Reserve by User class.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class ReserveByUserServiceImpl implements BookingService<ReserveByUser> {

    @Override
    public boolean create(ReserveByUser entity) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<ReserveByUser> read(String where) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<ReserveByUser> reserveByUserDAO = daoFactory.getReserveByUserDAO();

        //no validation for internal queries from DB

        List<ReserveByUser> list;

        try {
            list = reserveByUserDAO.read(where);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return list;
    }

    @Override
    public boolean update(ReserveByUser entity) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(ReserveByUser entity) throws ServiceException {
        throw new UnsupportedOperationException();
    }
}
