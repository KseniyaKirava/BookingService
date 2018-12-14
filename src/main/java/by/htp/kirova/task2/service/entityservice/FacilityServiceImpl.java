package by.htp.kirova.task2.service.entityservice;

import by.htp.kirova.task2.dao.BookingDAO;
import by.htp.kirova.task2.dao.DAOException;
import by.htp.kirova.task2.dao.DAOFactory;
import by.htp.kirova.task2.entity.Facility;
import by.htp.kirova.task2.service.BookingService;
import by.htp.kirova.task2.service.ServiceException;
import by.htp.kirova.task2.service.validation.Validator;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Contains methods which provide application to work with facilities.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class FacilityServiceImpl implements BookingService<Facility> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger logger = Logger.getLogger(FacilityServiceImpl.class);

    @Override
    public boolean create(Facility facility) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Facility> facilityDAO = daoFactory.getFacilityDAO();

        Validator validator = Validator.getInstance();

        if (!validator.checkEntityName(facility.getName()) ||
                !facility.isEnabled()) {
            logger.debug("Validation of facility data ended with error");
            return false;
        }

        boolean result;

        try {
            result = facilityDAO.create(facility);
        } catch (DAOException e) {
            throw new ServiceException("Can't process the create request correctly: ", e);
        }

        return result;
    }

    @Override
    public List<Facility> read(String where) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Facility> facilityDAO = daoFactory.getFacilityDAO();

        //no validation for internal queries from DB

        List<Facility> list;

        try {
            list = facilityDAO.read(where);
        } catch (DAOException e) {
            throw new ServiceException("Can't process the read request correctly: ", e);
        }

        return list;
    }

    @Override
    public boolean update(Facility facility) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Facility> facilityDAO = daoFactory.getFacilityDAO();

        Validator validator = Validator.getInstance();

        if (!validator.checkEntityName(facility.getName())) {
            logger.debug("Validation of facility data ended with error");
            return false;
        }

        boolean result;

        try {
            result = facilityDAO.update(facility);
        } catch (DAOException e) {
            throw new ServiceException("Can't process the update request correctly: ", e);
        }

        return result;
    }

    @Override
    public boolean delete(Facility facility) throws ServiceException {

        // Method is not used.
        // UPDATE is used to remove objects from the database with flag enabled = false.

        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Facility> facilityDAO = daoFactory.getFacilityDAO();

        boolean result;

        try {
            result = facilityDAO.delete(facility);
        } catch (DAOException e) {
            throw new ServiceException("Can't process the delete request correctly: ", e);
        }

        return result;

    }
}
