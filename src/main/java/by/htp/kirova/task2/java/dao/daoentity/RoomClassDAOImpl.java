package by.htp.kirova.task2.java.dao.daoentity;

import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.dao.GenericDAO;
import by.htp.kirova.task2.java.entity.RoomClass;
import org.apache.log4j.Logger;

import java.util.List;


/**
 * Provides Room Class with an opportunity to retrieve, change and delete data from
 * the relevant database table.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class RoomClassDAOImpl implements GenericDAO<RoomClass> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(RoomClassDAOImpl.class);


    @Override
    public boolean create(RoomClass entity) throws DAOException {
        return false;
    }

    public RoomClass findById(long id) throws DAOException {
        return null;
    }

    @Override
    public List<RoomClass> findAll(String where) throws DAOException {
        return null;
    }

    @Override
    public boolean update(RoomClass entity) throws DAOException {
        return false;
    }

    public boolean deleteById(long id) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(RoomClass entity) throws DAOException {
        return false;
    }
}
