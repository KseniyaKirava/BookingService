package by.htp.kirova.task2.java.service.entityservice;

import by.htp.kirova.task2.java.entity.RoomHasFacility;
import by.htp.kirova.task2.java.service.GenericService;
import by.htp.kirova.task2.java.service.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Contains methods which provide application logic to work with room has facilities.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class RoomHasFacilityServiceImpl implements GenericService<RoomHasFacility> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(RoomHasFacilityServiceImpl.class);

    @Override
    public boolean create(RoomHasFacility entity) throws ServiceException {
        return false;
    }

    @Override
    public List<RoomHasFacility> read(String where) throws ServiceException {
        return null;
    }

    @Override
    public boolean update(RoomHasFacility entity) throws ServiceException {
        return false;
    }

    @Override
    public boolean delete(RoomHasFacility entity) throws ServiceException {
        return false;
    }
}
