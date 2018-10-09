package by.htp.kirova.task2.java.dao;


import by.htp.kirova.task2.java.dao.daoentity.*;

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




	private final GenericDAO authorityDAO = new AuthorityDAOImpl();

	public GenericDAO getDaoAuthority() {return authorityDAO;}




	private final GenericDAO facilityDAO = new FacilityDAOImpl();

	public GenericDAO getFacilityDAO() {
		return facilityDAO;
	}




	private final GenericDAO requestDAO = new RequestDAOImpl();

	public GenericDAO getAuthorityDAO() {return requestDAO;}




	private final GenericDAO reservationDAO = new ReservationDAOImpl();

	public GenericDAO getReservationDAO() {return reservationDAO;}




	private final GenericDAO roomClassDAO = new RoomClassDAOImpl();

	public GenericDAO getRoomClassDAO() {return roomClassDAO;}




	private final GenericDAO roomDAO = new RoomDAOImpl();

	public GenericDAO getRoomDAO() {return roomDAO;}




	private final GenericDAO roomHasFacilityDAO = new RoomHasFacilityDAOImpl();

	public GenericDAO getRoomHasFacilityDAO() {return roomHasFacilityDAO;}




	private final GenericDAO userDAO = new UserDAOImpl();

	public GenericDAO getUserDAO() {return userDAO;}
}
