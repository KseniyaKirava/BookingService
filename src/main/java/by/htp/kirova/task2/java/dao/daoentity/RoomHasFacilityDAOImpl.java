package by.htp.kirova.task2.java.dao.daoentity;


import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.dao.GenericDAO;
import by.htp.kirova.task2.java.entity.RoomHasFacility;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Provides Room Has Facility with an opportunity to retrieve, change and delete data from
 * the relevant database table.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class RoomHasFacilityDAOImpl implements GenericDAO<RoomHasFacility> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(RoomHasFacilityDAOImpl.class);

    @Override
    public RoomHasFacility findById(long id) throws DAOException {
        return null;
    }

    @Override
    public boolean deleteById(long id) throws DAOException {
        return false;
    }

    @Override
    public boolean create(RoomHasFacility entity) throws DAOException {
        return false;
    }

    @Override
    public List<RoomHasFacility> findAll(String where) throws DAOException {
        return null;
    }

    @Override
    public boolean update(RoomHasFacility entity) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(RoomHasFacility entity) throws DAOException {
        return false;
    }
}
