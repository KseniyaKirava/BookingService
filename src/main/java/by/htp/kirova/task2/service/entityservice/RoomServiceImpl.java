package by.htp.kirova.task2.service.entityservice;


import by.htp.kirova.task2.dao.BookingDAO;
import by.htp.kirova.task2.dao.DAOException;
import by.htp.kirova.task2.dao.DAOFactory;
import by.htp.kirova.task2.entity.Room;
import by.htp.kirova.task2.service.BookingService;
import by.htp.kirova.task2.service.ServiceException;

import java.util.List;

/**
 * Contains methods which provide application logic to work with room.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class RoomServiceImpl implements BookingService<Room> {

    @Override
    public boolean create(Room room) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Room> roomDAO = daoFactory.getRoomDAO();

        boolean result;

        try {
            result = roomDAO.create(room);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;

    }

    @Override
    public List<Room> read(String where) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Room> roomDAO = daoFactory.getRoomDAO();

        List<Room> list;

        try {
            list = roomDAO.read(where);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return list;

    }

    @Override
    public boolean update(Room room) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Room> roomDAO = daoFactory.getRoomDAO();

        boolean result;

        try {
            result = roomDAO.update(room);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public boolean delete(Room room) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Room> roomDAO = daoFactory.getRoomDAO();

        boolean result;

        try {
            result = roomDAO.delete(room);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }
}
