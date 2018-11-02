package by.htp.kirova.task2.service.entityservice;


import by.htp.kirova.task2.dao.DAOException;
import by.htp.kirova.task2.dao.DAOFactory;
import by.htp.kirova.task2.dao.BookingDAO;
import by.htp.kirova.task2.entity.Reservation;
import by.htp.kirova.task2.service.BookingService;
import by.htp.kirova.task2.service.ServiceException;
import by.htp.kirova.task2.service.validation.Validator;

import java.util.List;

/**
 * Contains methods which provide application logic to work with reservations.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class ReservationServiceImpl implements BookingService<Reservation> {

    @Override
    public boolean create(Reservation reservation) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Reservation> reservationDAO = daoFactory.getReservationDAO();
        Validator validator = Validator.getInstance();

        if (!validator.checkDateInLong(reservation.getReservationDate()) ||
                !validator.checkDateInLong(reservation.getCheckinDate()) ||
                !validator.checkDateInLong(reservation.getCheckoutDate()) ||
                !validator.checkCost(reservation.getTotalCost()) || !reservation.isEnabled() ||
                !validator.checkUsername(reservation.getUsersUsername()) ||
                !(reservation.getRoomsId() > 0) || !(reservation.getRoomsRoomClassesId() > 0)) {
            return false;
        }

        boolean result;

        try {
            result = reservationDAO.create(reservation);
        } catch (DAOException e) {
            throw new ServiceException("Can't process the create request correctly: ", e);
        }

        return result;

    }

    @Override
    public List<Reservation> read(String where) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Reservation> reservationDAO = daoFactory.getReservationDAO();

        //no validation for internal queries

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
        BookingDAO<Reservation> reservationDAO = daoFactory.getReservationDAO();
        Validator validator = Validator.getInstance();

        if (!validator.checkDateInLong(reservation.getReservationDate()) ||
                !validator.checkDateInLong(reservation.getCheckinDate()) ||
                !validator.checkDateInLong(reservation.getCheckoutDate()) ||
                !validator.checkCost(reservation.getTotalCost()) ||
                !validator.checkUsername(reservation.getUsersUsername()) ||
                !(reservation.getRoomsId() > 0) || !(reservation.getRoomsRoomClassesId() > 0)) {
            return false;
        }

        boolean result;

        try {
            result = reservationDAO.update(reservation);
        } catch (DAOException e) {
            throw new ServiceException("Can't process the update request correctly: ", e);
        }

        return result;

    }

    @Override
    public boolean delete(Reservation reservation) throws ServiceException {

        // Method is not used.
        // UPDATE is used to remove objects from the database with flag enabled = false.

        DAOFactory daoFactory = DAOFactory.getInstance();
        BookingDAO<Reservation> reservationDAO = daoFactory.getReservationDAO();

        boolean result;

        try {
            result = reservationDAO.delete(reservation);
        } catch (DAOException e) {
            throw new ServiceException("Can't process the delete request correctly: ", e);
        }

        return result;

    }

}
