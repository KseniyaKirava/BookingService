package by.htp.kirova.task2.service.entityservice;


import by.htp.kirova.task2.dao.BookingDAO;
import by.htp.kirova.task2.dao.DAOException;
import by.htp.kirova.task2.dao.DAOFactory;
import by.htp.kirova.task2.entity.Room;
import by.htp.kirova.task2.service.BookingService;
import by.htp.kirova.task2.service.ServiceException;
import by.htp.kirova.task2.service.validation.Validator;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Contains methods which provide application to work with room.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class RoomServiceImpl implements BookingService<Room> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger logger = Logger.getLogger(RoomServiceImpl.class);


    @Override
    public boolean create(Room room) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Room> roomDAO = daoFactory.getRoomDAO();

        Validator validator = Validator.getInstance();

        if (!validator.checkEntityName(room.getName()) ||
                !validator.checkRoomNumber(room.getNumber()) ||
                !validator.checkCapacity(room.getCapacity()) ||
                !validator.checkCost(room.getCost()) ||
                !room.isEnabled()) {
            logger.debug("Validation of room data ended with error");
            return false;
        }

        boolean result;

        try {
            result = roomDAO.create(room);
        } catch (DAOException e) {
            throw new ServiceException("Can't process the create request correctly: ", e);
        }

        return result;

    }

    @Override
    public List<Room> read(String where) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Room> roomDAO = daoFactory.getRoomDAO();

        //no validation for internal queries from DB

        List<Room> list;

        try {
            list = roomDAO.read(where);
        } catch (DAOException e) {
            throw new ServiceException("Can't process the update request correctly: ", e);
        }

        return list;

    }

    @Override
    public boolean update(Room room) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Room> roomDAO = daoFactory.getRoomDAO();

        Validator validator = Validator.getInstance();

        if (!validator.checkEntityName(room.getName()) ||
                !validator.checkRoomNumber(room.getNumber()) ||
                !validator.checkCapacity(room.getCapacity()) ||
                !validator.checkCost(room.getCost())) {
            logger.debug("Validation of room data ended with error");
            return false;
        }

        boolean result;

        try {
            result = roomDAO.update(room);
        } catch (DAOException e) {
            throw new ServiceException("Can't process the update request correctly: ", e);
        }

        return result;
    }

    @Override
    public boolean delete(Room room) throws ServiceException {

        // Method is not used.
        // UPDATE is used to remove objects from the database with flag enabled = false.

        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Room> roomDAO = daoFactory.getRoomDAO();

        boolean result;

        try {
            result = roomDAO.delete(room);
        } catch (DAOException e) {
            throw new ServiceException("Can't process the update request correctly: ", e);
        }

        return result;
    }
}
