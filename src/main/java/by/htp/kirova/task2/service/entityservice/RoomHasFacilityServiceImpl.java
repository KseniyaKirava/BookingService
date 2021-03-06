package by.htp.kirova.task2.service.entityservice;

import by.htp.kirova.task2.dao.BookingDAO;
import by.htp.kirova.task2.dao.DAOException;
import by.htp.kirova.task2.dao.DAOFactory;
import by.htp.kirova.task2.entity.RoomHasFacility;
import by.htp.kirova.task2.service.BookingService;
import by.htp.kirova.task2.service.ServiceException;
import by.htp.kirova.task2.service.validation.Validator;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Contains methods which provide application to work with room has facilities.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class RoomHasFacilityServiceImpl implements BookingService<RoomHasFacility> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger logger = Logger.getLogger(RoomHasFacilityServiceImpl.class);

    @Override
    public boolean create(RoomHasFacility roomHasFacility) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<RoomHasFacility> roomHasFacilityDAO = daoFactory.getRoomHasFacilityDAO();

        Validator validator = Validator.getInstance();

        if (!validator.checkCount(roomHasFacility.getCount()) ||
                !roomHasFacility.isEnabled()) {
            logger.debug("Validation of room-has-facility data ended with error");
            return false;
        }

        boolean result;

        try {
            result = roomHasFacilityDAO.create(roomHasFacility);
        } catch (DAOException e) {
            throw new ServiceException("Can't process the create request correctly: ", e);
        }

        return result;

    }

    @Override
    public List<RoomHasFacility> read(String where) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<RoomHasFacility> roomHasFacilityDAO = daoFactory.getRoomHasFacilityDAO();

        //no validation for internal queries from DB

        List<RoomHasFacility> list;

        try {
            list = roomHasFacilityDAO.read(where);
        } catch (DAOException e) {
            throw new ServiceException("Can't process the update request correctly: ", e);
        }

        return list;

    }

    @Override
    public boolean update(RoomHasFacility roomHasFacility) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<RoomHasFacility> roomHasFacilityDAO = daoFactory.getRoomHasFacilityDAO();

        Validator validator = Validator.getInstance();

        if (!validator.checkCount(roomHasFacility.getCount())) {
            logger.debug("Validation of room-has-facility data ended with error");
            return false;
        }

        boolean result;

        try {
            result = roomHasFacilityDAO.update(roomHasFacility);
        } catch (DAOException e) {
            throw new ServiceException("Can't process the update request correctly: ", e);
        }

        return result;
    }

    @Override
    public boolean delete(RoomHasFacility roomHasFacility) throws ServiceException {

        // Method is not used.
        // UPDATE is used to remove objects from the database with flag enabled = false.

        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<RoomHasFacility> roomHasFacilityDAO = daoFactory.getRoomHasFacilityDAO();

        boolean result;

        try {
            result = roomHasFacilityDAO.delete(roomHasFacility);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }
}
