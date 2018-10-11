package by.htp.kirova.task2.java.dao;


import by.htp.kirova.task2.java.dao.daoentity.*;
import by.htp.kirova.task2.java.entity.*;

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
	private final GenericDAO<Authority> authorityDAO = new AuthorityDAOImpl();

	public GenericDAO<Authority> getAuthorityDAO() {return authorityDAO;}


	/**
	 * Implementation of Facility DAO.
	 */
	private final GenericDAO<Facility> facilityDAO = new FacilityDAOImpl();

	public GenericDAO<Facility> getFacilityDAO() {
		return facilityDAO;
	}


	/**
	 * Implementation of Request DAO.
	 */
	private final GenericDAO<Request> requestDAO = new RequestDAOImpl();

	public GenericDAO<Request> getRequestDAO() {return requestDAO;}


	/**
	 * Implementation of Reservation DAO.
	 */
	private final GenericDAO<Reservation> reservationDAO = new ReservationDAOImpl();

	public GenericDAO<Reservation> getReservationDAO() {return reservationDAO;}


	/**
	 * Implementation of Room Class DAO.
	 */
	private final GenericDAO<RoomClass> roomClassDAO = new RoomClassDAOImpl();

	public GenericDAO<RoomClass> getRoomClassDAO() {return roomClassDAO;}


	/**
	 * Implementation of Room-has-Facility DAO.
	 */
	private final GenericDAO<RoomHasFacility> roomHasFacilityDAO = new RoomHasFacilityDAOImpl();

	public GenericDAO<RoomHasFacility> getRoomHasFacilityDAO() {return roomHasFacilityDAO;}


	/**
	 * Implementation of Room DAO.
	 */
	private final GenericDAO<Room> roomDAO = new RoomDAOImpl();

	public GenericDAO<Room> getRoomDAO() {return roomDAO;}


	/**
	 * Implementation of User DAO.
	 */
	private final GenericDAO<User> userDAO = new UserDAOImpl();

	public GenericDAO<User> getUserDAO() {return userDAO;}
}
