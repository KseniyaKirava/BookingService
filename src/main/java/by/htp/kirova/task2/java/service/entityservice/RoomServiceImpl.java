package by.htp.kirova.task2.java.service.entityservice;


import by.htp.kirova.task2.java.entity.Room;
import by.htp.kirova.task2.java.service.GenericService;
import by.htp.kirova.task2.java.service.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Contains methods which provide application logic to work with room.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class RoomServiceImpl implements GenericService<Room> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(RoomServiceImpl.class);

    @Override
    public boolean create(Room entity) throws ServiceException {
        return false;
    }

    public Room findById(long id) throws ServiceException {
        return null;
    }

    @Override
    public List<Room> findAll(String where) throws ServiceException {
        return null;
    }

    @Override
    public boolean update(Room entity) throws ServiceException {
        return false;
    }

    public boolean deleteById(long id) throws ServiceException {
        return false;
    }

    @Override
    public boolean delete(Room entity) throws ServiceException {
        return false;
    }
}
