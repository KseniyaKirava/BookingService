package by.htp.kirova.task2.service.entityservice;

import by.htp.kirova.task2.dao.BookingDAO;
import by.htp.kirova.task2.dao.DAOException;
import by.htp.kirova.task2.dao.DAOFactory;
import by.htp.kirova.task2.entity.RoomClass;
import by.htp.kirova.task2.service.BookingService;
import by.htp.kirova.task2.service.ServiceException;
import by.htp.kirova.task2.service.validation.Validator;

import java.util.List;


/**
 * Contains methods which provide application logic to work with room class.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class RoomClassServiceImpl implements BookingService<RoomClass> {


    @Override
    public boolean create(RoomClass roomClass) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<RoomClass> roomClassDAO = daoFactory.getRoomClassDAO();
        Validator validator = Validator.getInstance();

//        if (!validator.)

        boolean result;

        try {
            result = roomClassDAO.create(roomClass);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;

    }


    @Override
    public List<RoomClass> read(String where) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<RoomClass> roomClassDAO = daoFactory.getRoomClassDAO();

        List<RoomClass> list;

        try {
            list = roomClassDAO.read(where);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return list;
    }

    @Override
    public boolean update(RoomClass roomClass) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<RoomClass> roomClassDAO = daoFactory.getRoomClassDAO();

        boolean result;

        try {
            result = roomClassDAO.update(roomClass);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }


    @Override
    public boolean delete(RoomClass roomClass) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<RoomClass> roomClassDAO = daoFactory.getRoomClassDAO();

        boolean result;

        try {
            result = roomClassDAO.delete(roomClass);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }
}
