package by.htp.kirova.task2.java.service.entityservice;

import by.htp.kirova.task2.java.entity.RoomClass;
import by.htp.kirova.task2.java.service.GenericService;
import by.htp.kirova.task2.java.service.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;


/**
 * Contains methods which provide application logic to work with room class.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class RoomClassServiceImpl implements GenericService<RoomClass> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(RoomClassServiceImpl.class);


    @Override
    public boolean create(RoomClass entity) throws ServiceException {
        return false;
    }


    public RoomClass findById(long id) throws ServiceException {
        return null;
    }

    @Override
    public List<RoomClass> findAll(String where) throws ServiceException {
        return null;
    }

    @Override
    public boolean update(RoomClass entity) throws ServiceException {
        return false;
    }


    public boolean deleteById(long id) throws ServiceException {
        return false;
    }

    @Override
    public boolean delete(RoomClass entity) throws ServiceException {
        return false;
    }
}
