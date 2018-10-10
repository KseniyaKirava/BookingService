package by.htp.kirova.task2.java.service;

import by.htp.kirova.task2.java.entity.*;
import by.htp.kirova.task2.java.service.entityservice.*;


/**
 *
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




	private final GenericService<Authority> authorityService = new AuthorityServiceImpl();

	public GenericService<Authority> getAuthorityService() {
		return authorityService;
	}




	private final GenericService<Facility> facilityService = new FacilityServiceImpl();

	public GenericService<Facility> getFacilityService() {
		return facilityService;
	}




	private final GenericService<Request> requestService = new RequestServiceImpl();

	public GenericService<Request> getRequestService() {
		return requestService;
	}




	private final GenericService<Reservation> reservationService = new ReservationServiceImpl();

	public GenericService<Reservation> getReservationService() {
		return reservationService;
	}




	private final GenericService<RoomClass> roomClassService = new RoomClassServiceImpl();

	public GenericService<RoomClass> getRoomClassService() {
		return roomClassService;
	}




	private final GenericService<RoomHasFacility> roomHasFacilityService = new RoomHasFacilityServiceImpl();

	public GenericService<RoomHasFacility> getRoomHasFacilityService() {
		return roomHasFacilityService;
	}




	private final GenericService<Room> roomService = new RoomServiceImpl();

	public GenericService<Room> getRoomService() {
		return roomService;
	}




	private final GenericService<User> userService = new UserServiceImpl();

	public GenericService<User> getUserService() {
		return userService;
	}
}
