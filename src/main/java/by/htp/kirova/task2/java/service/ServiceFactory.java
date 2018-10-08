package by.htp.kirova.task2.java.service;

import by.htp.kirova.task2.java.service.serviceentity.FacilityServiceImpl;


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





	private final Service serviceFacility = new FacilityServiceImpl();

	public Service getServiceFacility() {
		return serviceFacility;
	}




}
