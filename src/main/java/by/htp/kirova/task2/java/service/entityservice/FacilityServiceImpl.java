package by.htp.kirova.task2.java.service.entityservice;

import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.dao.DAOFactory;
import by.htp.kirova.task2.java.dao.GenericDAO;
import by.htp.kirova.task2.java.entity.Facility;
import by.htp.kirova.task2.java.service.GenericService;
import by.htp.kirova.task2.java.service.ServiceException;
import by.htp.kirova.task2.java.service.validation.Validator;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Contains methods which provide application logic to work with facilities.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class FacilityServiceImpl implements GenericService<Facility> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(FacilityServiceImpl.class);

    @Override
    public boolean create(Facility facility) throws ServiceException {
//        if (!Validator.checkEntityName(facility.getName())) {
//            LOGGER.info("The entered data does not correspond to the format");
//            return false;
//        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        GenericDAO facilityDAO = daoFactory.getFacilityDAO();
        boolean result;
        try {
            result = facilityDAO.create(facility);
        } catch (DAOException e) {
            LOGGER.error("ConnectionPool error: ", e);
            throw new ServiceException("ConnectionPool error: ", e);
        }
        return result;
    }

    @Override
    public Facility findById(long id) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        GenericDAO facilityDAO = daoFactory.getFacilityDAO();
        Facility facility = null;
        try {
            Object o = facilityDAO.findById(id);
            if (o != null) {
                facility = (Facility) o;
            }
        } catch (DAOException e) {
            LOGGER.error("ConnectionPool error: ", e);
            throw new ServiceException("ConnectionPool error: ", e);
        }
        return facility;

    }

    @Override
    public List findAll(String where) throws ServiceException {
        //todo
        return null;
    }

    @Override
    public boolean update(Facility facility) throws ServiceException {
        return false;
    }

    @Override
    public boolean deleteById(long id) throws ServiceException {
        return false;
    }

    @Override
    public boolean delete(Facility facility) throws ServiceException {
        return false;
    }
}
