package by.htp.kirova.task2.dao;


import by.htp.kirova.task2.dao.daoentity.*;
import by.htp.kirova.task2.entity.*;

/**
 * The factory allows to organize a multi-layer architecture and hide
 * the implementation of DAO.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public final class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();

	private DAOFactory() {}

	public static DAOFactory getInstance() {
		return instance;
	}


	/**
	 * Implementation of Authority DAO.
	 */
	private final BookingDAO<Authority> authorityDAO = new AuthorityDAOImpl();

	public BookingDAO<Authority> getAuthorityDAO() {return authorityDAO;}


	/**
	 * Implementation of Facility DAO.
	 */
	private final BookingDAO<Facility> facilityDAO = new FacilityDAOImpl();

	public BookingDAO<Facility> getFacilityDAO() {
		return facilityDAO;
	}


	/**
	 * Implementation of Reservation DAO.
	 */
	private final BookingDAO<Reservation> reservationDAO = new ReservationDAOImpl();

	public BookingDAO<Reservation> getReservationDAO() {return reservationDAO;}


	/**
	 * Implementation of Room Class DAO.
	 */
	private final BookingDAO<RoomClass> roomClassDAO = new RoomClassDAOImpl();

	public BookingDAO<RoomClass> getRoomClassDAO() {return roomClassDAO;}


	/**
	 * Implementation of Room-has-Facility DAO.
	 */
	private final BookingDAO<RoomHasFacility> roomHasFacilityDAO = new RoomHasFacilityDAOImpl();

	public BookingDAO<RoomHasFacility> getRoomHasFacilityDAO() {return roomHasFacilityDAO;}


	/**
	 * Implementation of Room DAO.
	 */
	private final BookingDAO<Room> roomDAO = new RoomDAOImpl();

	public BookingDAO<Room> getRoomDAO() {return roomDAO;}


	/**
	 * Implementation of User DAO.
	 */
	private final BookingDAO<User> userDAO = new UserDAOImpl();

	public BookingDAO<User> getUserDAO() {return userDAO;}


	/**
	 * Implementation of View Helper DAO.
	 */
	private final HelperDAO helperDAO = new ViewHelperDAO();

	public HelperDAO getHelperDAO(){ return helperDAO; }
}
