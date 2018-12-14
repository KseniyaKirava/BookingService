package by.htp.kirova.task2.service.entityservice;

import by.htp.kirova.task2.dao.BookingDAO;
import by.htp.kirova.task2.dao.DAOException;
import by.htp.kirova.task2.dao.DAOFactory;
import by.htp.kirova.task2.entity.RoomClass;
import by.htp.kirova.task2.service.BookingService;
import by.htp.kirova.task2.service.ServiceException;
import by.htp.kirova.task2.service.validation.Validator;
import org.apache.log4j.Logger;
import java.util.List;


/**
 * Contains methods which provide application to work with room class.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class RoomClassServiceImpl implements BookingService<RoomClass> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger logger = Logger.getLogger(RoomClassServiceImpl.class);


    @Override
    public boolean create(RoomClass roomClass) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<RoomClass> roomClassDAO = daoFactory.getRoomClassDAO();
        Validator validator = Validator.getInstance();

        if (!validator.checkRoomClassName(roomClass.getName()) || !roomClass.isEnabled()) {
            logger.debug("Validation of room's class data ended with error");
            return false;
        }

        boolean result;

        try {
            result = roomClassDAO.create(roomClass);
        } catch (DAOException e) {
            throw new ServiceException("Can't process the create request correctly: ", e);
        }

        return result;

    }


    @Override
    public List<RoomClass> read(String where) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<RoomClass> roomClassDAO = daoFactory.getRoomClassDAO();

        //no validation for internal queries from DB

        List<RoomClass> list;

        try {
            list = roomClassDAO.read(where);
        } catch (DAOException e) {
            throw new ServiceException("Can't process the create request correctly: ", e);
        }

        return list;
    }

    @Override
    public boolean update(RoomClass roomClass) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<RoomClass> roomClassDAO = daoFactory.getRoomClassDAO();

        Validator validator = Validator.getInstance();

        if (!validator.checkRoomClassName(roomClass.getName())) {
            logger.debug("Validation of room's class data ended with error");
            return false;
        }

        boolean result;

        try {
            result = roomClassDAO.update(roomClass);
        } catch (DAOException e) {
            throw new ServiceException("Can't process the create request correctly: ", e);
        }

        return result;
    }


    @Override
    public boolean delete(RoomClass roomClass) throws ServiceException {

        // Method is not used.
        // UPDATE is used to remove objects from the database with flag enabled = false.

        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<RoomClass> roomClassDAO = daoFactory.getRoomClassDAO();

        boolean result;

        try {
            result = roomClassDAO.delete(roomClass);
        } catch (DAOException e) {
            throw new ServiceException("Can't process the create request correctly: ", e);
        }

        return result;
    }
}
