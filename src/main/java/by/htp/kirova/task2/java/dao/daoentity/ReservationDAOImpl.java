package by.htp.kirova.task2.java.dao.daoentity;

import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.dao.GenericDAO;
import by.htp.kirova.task2.java.entity.Reservation;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Provides Reservation with an opportunity to retrieve, change and delete data from
 * the relevant database table.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class ReservationDAOImpl implements GenericDAO<Reservation> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(ReservationDAOImpl.class);


    @Override
    public boolean create(Reservation entity) throws DAOException {
        return false;
    }

    public Reservation findById(long id) throws DAOException {
        return null;
    }

    @Override
    public List<Reservation> findAll(String where) throws DAOException {
        return null;
    }

    @Override
    public boolean update(Reservation entity) throws DAOException {
        return false;
    }

    public boolean deleteById(long id) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(Reservation entity) throws DAOException {
        return false;
    }
}
