package by.htp.kirova.task2.java.service.entityservice;

import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.dao.DAOFactory;
import by.htp.kirova.task2.java.dao.GenericDAO;
import by.htp.kirova.task2.java.entity.Facility;
import by.htp.kirova.task2.java.service.GenericService;
import by.htp.kirova.task2.java.service.ServiceException;
import by.htp.kirova.task2.java.service.validation.Validator;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Contains methods which provide application logic to work with facilities.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class FacilityServiceImpl implements GenericService<Facility> {

    @Override
    public boolean create(Facility facility) throws ServiceException {
//        if (!Validator.checkEntityName(facility.getName())) {
//            LOGGER.info("The entered data does not correspond to the format");
//            return false;
//        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        GenericDAO<Facility> facilityDAO = daoFactory.getFacilityDAO();

        boolean result;

        try {
            result = facilityDAO.create(facility);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public List<Facility> read(String where) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        GenericDAO<Facility> facilityDAO = daoFactory.getFacilityDAO();

        List<Facility> list;

        try {
            list = facilityDAO.read(where);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return list;
    }

    @Override
    public boolean update(Facility facility) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        GenericDAO<Facility> facilityDAO = daoFactory.getFacilityDAO();

        boolean result;

        try {
            result = facilityDAO.update(facility);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public boolean delete(Facility facility) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        GenericDAO<Facility> facilityDAO = daoFactory.getFacilityDAO();

        boolean result;

        try {
            result = facilityDAO.delete(facility);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;

    }
}
