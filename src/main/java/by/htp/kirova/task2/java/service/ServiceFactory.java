package by.htp.kirova.task2.java.service;

import by.htp.kirova.task2.java.entity.*;
import by.htp.kirova.task2.java.service.entityservice.*;


/**
 * The factory allows to organize a multi-layer architecture and hide
 * the implementation of services.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public final class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();

	private ServiceFactory() {}

	public static ServiceFactory getInstance() {
		return instance;
	}


	/**
	 * Implementation of Authority Service.
	 */
	private final GenericService<Authority> authorityService = new AuthorityServiceImpl();

	public GenericService<Authority> getAuthorityService() {
		return authorityService;
	}


	/**
	 * Implementation of Facility Service.
	 */
	private final GenericService<Facility> facilityService = new FacilityServiceImpl();

	public GenericService<Facility> getFacilityService() {
		return facilityService;
	}


	/**
	 * Implementation of Request Service.
	 */
	private final GenericService<Request> requestService = new RequestServiceImpl();

	public GenericService<Request> getRequestService() {
		return requestService;
	}


	/**
	 * Implementation of Reservation Service.
	 */
	private final GenericService<Reservation> reservationService = new ReservationServiceImpl();

	public GenericService<Reservation> getReservationService() {
		return reservationService;
	}


	/**
	 * Implementation of Room Class Service.
	 */
	private final GenericService<RoomClass> roomClassService = new RoomClassServiceImpl();

	public GenericService<RoomClass> getRoomClassService() {
		return roomClassService;
	}


	/**
	 * Implementation of Room-has-Facility Service.
	 */
	private final GenericService<RoomHasFacility> roomHasFacilityService = new RoomHasFacilityServiceImpl();

	public GenericService<RoomHasFacility> getRoomHasFacilityService() {
		return roomHasFacilityService;
	}


	/**
	 * Implementation of Room Service.
	 */
	private final GenericService<Room> roomService = new RoomServiceImpl();

	public GenericService<Room> getRoomService() {
		return roomService;
	}


	/**
	 * Implementation of User Service.
	 */
	private final GenericService<User> userService = new UserServiceImpl();

	public GenericService<User> getUserService() {
		return userService;
	}


	/**
	 * Implementation of View Helper Service.
	 */
	private final HelperService helperService = new ViewHelperService();

	public HelperService getHelperService(){ return helperService; }
}
