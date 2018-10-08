package by.htp.kirova.task2.java.dao;


import by.htp.kirova.task2.java.dao.daoentity.FacilityDAOImpl;

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





	private final DAO daoFacility = new FacilityDAOImpl();

	public DAO getDaoFacility() {
		return daoFacility;
	}




}
