package by.htp.kirova.task2.java.service.entityservice;


import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.dao.DAOFactory;
import by.htp.kirova.task2.java.dao.GenericDAO;
import by.htp.kirova.task2.java.entity.Reservation;
import by.htp.kirova.task2.java.service.GenericService;
import by.htp.kirova.task2.java.service.ServiceException;

import java.util.List;

/**
 * Contains methods which provide application logic to work with reservations.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class ReservationServiceImpl implements GenericService<Reservation> {

    @Override
    public boolean create(Reservation reservation) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        GenericDAO<Reservation> reservationDAO = daoFactory.getReservationDAO();

        boolean result;

        try {
            result = reservationDAO.create(reservation);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;

    }

    @Override
    public List<Reservation> read(String where) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        GenericDAO<Reservation> reservationDAO = daoFactory.getReservationDAO();

        List<Reservation> list;

        try {
            list = reservationDAO.read(where);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return list;

    }

    @Override
    public boolean update(Reservation reservation) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        GenericDAO<Reservation> reservationDAO = daoFactory.getReservationDAO();

        boolean result;

        try {
            result = reservationDAO.update(reservation);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;

    }

    @Override
    public boolean delete(Reservation reservation) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        GenericDAO<Reservation> reservationDAO = daoFactory.getReservationDAO();

        boolean result;

        try {
            result = reservationDAO.delete(reservation);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;

    }

}
