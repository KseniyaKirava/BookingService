package by.htp.kirova.task2.java.service;

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




	private final GenericService authorityService = new AuthorityServiceImpl();

	public GenericService getAuthorityService() {
		return authorityService;
	}




	private final GenericService facilityService = new FacilityServiceImpl();

	public GenericService getFacilityService() {
		return facilityService;
	}




	private final GenericService requestService = new RequestServiceImpl();

	public GenericService getRequestService() {
		return requestService;
	}




	private final GenericService reservationService = new ReservationServiceImpl();

	public GenericService getReservationService() {
		return reservationService;
	}




	private final GenericService roomClassService = new RoomClassServiceImpl();

	public GenericService getRoomClassService() {
		return roomClassService;
	}




	private final GenericService roomHasFacilityService = new RoomHasFacilityServiceImpl();

	public GenericService getRoomHasFacilityService() {
		return roomHasFacilityService;
	}




	private final GenericService roomService = new RoomServiceImpl();

	public GenericService getRoomService() {
		return roomService;
	}




	private final GenericService userService = new UserServiceImpl();

	public GenericService getUserService() {
		return userService;
	}
}
