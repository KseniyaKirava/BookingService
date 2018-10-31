package by.htp.kirova.task2.service;

import by.htp.kirova.task2.entity.*;
import by.htp.kirova.task2.service.entityservice.*;


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
	private final BookingService<Authority> authorityService = new AuthorityServiceImpl();

	public BookingService<Authority> getAuthorityService() {
		return authorityService;
	}


	/**
	 * Implementation of Facility Service.
	 */
	private final BookingService<Facility> facilityService = new FacilityServiceImpl();

	public BookingService<Facility> getFacilityService() {
		return facilityService;
	}


	/**
	 * Implementation of Request Service.
	 */
	private final BookingService<Request> requestService = new RequestServiceImpl();

	public BookingService<Request> getRequestService() {
		return requestService;
	}


	/**
	 * Implementation of Reservation Service.
	 */
	private final BookingService<Reservation> reservationService = new ReservationServiceImpl();

	public BookingService<Reservation> getReservationService() {
		return reservationService;
	}


	/**
	 * Implementation of Room Class Service.
	 */
	private final BookingService<RoomClass> roomClassService = new RoomClassServiceImpl();

	public BookingService<RoomClass> getRoomClassService() {
		return roomClassService;
	}


	/**
	 * Implementation of Room-has-Facility Service.
	 */
	private final BookingService<RoomHasFacility> roomHasFacilityService = new RoomHasFacilityServiceImpl();

	public BookingService<RoomHasFacility> getRoomHasFacilityService() {
		return roomHasFacilityService;
	}


	/**
	 * Implementation of Room Service.
	 */
	private final BookingService<Room> roomService = new RoomServiceImpl();

	public BookingService<Room> getRoomService() {
		return roomService;
	}


	/**
	 * Implementation of User Service.
	 */
	private final BookingService<User> userService = new UserServiceImpl();

	public BookingService<User> getUserService() {
		return userService;
	}


	/**
	 * Implementation of View Helper Service.
	 */
	private final HelperService helperService = new ViewHelperService();

	public HelperService getHelperService(){ return helperService; }
}
