package by.htp.kirova.task2.java.service.entityservice;

import by.htp.kirova.task2.java.entity.Facility;
import by.htp.kirova.task2.java.service.GenericService;
import by.htp.kirova.task2.java.service.ServiceException;
import org.apache.log4j.Logger;

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
        return false;
    }


    public Facility findById(long id) throws ServiceException {
        return null;
    }

    @Override
    public List findAll(String where) throws ServiceException {
        return null;
    }

    @Override
    public boolean update(Facility facility) throws ServiceException {
        return false;
    }


    public boolean deleteById(long id) throws ServiceException {
        return false;
    }

    @Override
    public boolean delete(Facility facility) throws ServiceException {
        return false;
    }
}
