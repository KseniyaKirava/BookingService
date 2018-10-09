package by.htp.kirova.task2.java.service.entityservice;


import by.htp.kirova.task2.java.entity.Reservation;
import by.htp.kirova.task2.java.service.GenericService;
import by.htp.kirova.task2.java.service.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Contains methods which provide application logic to work with reservations.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class ReservationServiceImpl implements GenericService<Reservation> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(ReservationServiceImpl.class);

    @Override
    public boolean create(Reservation entity) throws ServiceException {
        return false;
    }

    @Override
    public Reservation findById(long id) throws ServiceException {
        return null;
    }

    @Override
    public List<Reservation> findAll(String where) throws ServiceException {
        return null;
    }

    @Override
    public boolean update(Reservation entity) throws ServiceException {
        return false;
    }

    @Override
    public boolean deleteById(long id) throws ServiceException {
        return false;
    }

    @Override
    public boolean delete(Reservation entity) throws ServiceException {
        return false;
    }

}
