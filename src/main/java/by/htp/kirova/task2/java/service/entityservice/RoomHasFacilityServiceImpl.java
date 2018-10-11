package by.htp.kirova.task2.java.service.entityservice;

import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.dao.DAOFactory;
import by.htp.kirova.task2.java.dao.GenericDAO;
import by.htp.kirova.task2.java.entity.RoomHasFacility;
import by.htp.kirova.task2.java.service.GenericService;
import by.htp.kirova.task2.java.service.ServiceException;
import java.util.List;

/**
 * Contains methods which provide application logic to work with room has facilities.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class RoomHasFacilityServiceImpl implements GenericService<RoomHasFacility> {


    @Override
    public boolean create(RoomHasFacility roomHasFacility) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        GenericDAO<RoomHasFacility> roomHasFacilityDAO = daoFactory.getRoomHasFacilityDAO();

        boolean result;

        try {
            result = roomHasFacilityDAO.create(roomHasFacility);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;

    }

    @Override
    public List<RoomHasFacility> read(String where) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        GenericDAO<RoomHasFacility> roomHasFacilityDAO = daoFactory.getRoomHasFacilityDAO();

        List<RoomHasFacility> list;

        try {
            list = roomHasFacilityDAO.read(where);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return list;

    }

    @Override
    public boolean update(RoomHasFacility roomHasFacility) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        GenericDAO<RoomHasFacility> roomHasFacilityDAO = daoFactory.getRoomHasFacilityDAO();

        boolean result;

        try {
            result = roomHasFacilityDAO.update(roomHasFacility);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public boolean delete(RoomHasFacility roomHasFacility) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        GenericDAO<RoomHasFacility> roomHasFacilityDAO = daoFactory.getRoomHasFacilityDAO();

        boolean result;

        try {
            result = roomHasFacilityDAO.delete(roomHasFacility);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }
}
