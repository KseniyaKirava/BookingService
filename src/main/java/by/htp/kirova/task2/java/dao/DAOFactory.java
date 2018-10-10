package by.htp.kirova.task2.java.dao;


import by.htp.kirova.task2.java.dao.daoentity.*;
import by.htp.kirova.task2.java.entity.*;

/**
 *
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




	private final GenericDAO<Authority> authorityDAO = new AuthorityDAOImpl();

	public GenericDAO<Authority> getDaoAuthority() {return authorityDAO;}




	private final GenericDAO<Facility> facilityDAO = new FacilityDAOImpl();

	public GenericDAO<Facility> getFacilityDAO() {
		return facilityDAO;
	}




	private final GenericDAO<Request> requestDAO = new RequestDAOImpl();

	public GenericDAO<Request> getAuthorityDAO() {return requestDAO;}




	private final GenericDAO<Reservation> reservationDAO = new ReservationDAOImpl();

	public GenericDAO<Reservation> getReservationDAO() {return reservationDAO;}




	private final GenericDAO<RoomClass> roomClassDAO = new RoomClassDAOImpl();

	public GenericDAO<RoomClass> getRoomClassDAO() {return roomClassDAO;}




	private final GenericDAO<Room> roomDAO = new RoomDAOImpl();

	public GenericDAO<Room> getRoomDAO() {return roomDAO;}




	private final GenericDAO<RoomHasFacility> roomHasFacilityDAO = new RoomHasFacilityDAOImpl();

	public GenericDAO<RoomHasFacility> getRoomHasFacilityDAO() {return roomHasFacilityDAO;}




	private final GenericDAO<User> userDAO = new UserDAOImpl();

	public GenericDAO<User> getUserDAO() {return userDAO;}
}
