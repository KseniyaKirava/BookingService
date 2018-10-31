package by.htp.kirova.task2.service.entityservice;

import by.htp.kirova.task2.dao.BookingDAO;
import by.htp.kirova.task2.dao.DAOException;
import by.htp.kirova.task2.dao.DAOFactory;
import by.htp.kirova.task2.entity.Facility;
import by.htp.kirova.task2.service.BookingService;
import by.htp.kirova.task2.service.ServiceException;
import java.util.List;

/**
 * Contains methods which provide application logic to work with facilities.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class FacilityServiceImpl implements BookingService<Facility> {

    @Override
    public boolean create(Facility facility) throws ServiceException {
//        if (!Validator.checkEntityName(facility.getName())) {
//            LOGGER.info("The entered data does not correspond to the format");
//            return false;
//        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Facility> facilityDAO = daoFactory.getFacilityDAO();

        boolean result;

        try {
            result = facilityDAO.create(facility);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public List<Facility> read(String where) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Facility> facilityDAO = daoFactory.getFacilityDAO();

        List<Facility> list;

        try {
            list = facilityDAO.read(where);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return list;
    }

    @Override
    public boolean update(Facility facility) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Facility> facilityDAO = daoFactory.getFacilityDAO();

        boolean result;

        try {
            result = facilityDAO.update(facility);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public boolean delete(Facility facility) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Facility> facilityDAO = daoFactory.getFacilityDAO();

        boolean result;

        try {
            result = facilityDAO.delete(facility);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;

    }
}
