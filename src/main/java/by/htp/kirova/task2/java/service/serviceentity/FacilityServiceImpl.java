package by.htp.kirova.task2.java.service.serviceentity;

import by.htp.kirova.task2.java.service.Service;
import by.htp.kirova.task2.java.service.ServiceException;

import java.util.List;

/**
 * Provides Facilities with an opportunity to retrieve, change and delete data from
 * the relevant database table.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class FacilityServiceImpl implements Service {

    @Override
    public boolean create(Object entity) throws ServiceException {
        return false;
    }

    @Override
    public Object findById(long id) throws ServiceException {
        return null;
    }

    @Override
    public List findAll(String where) throws ServiceException {
        return null;
    }

    @Override
    public boolean update(Object entity) throws ServiceException {
        return false;
    }

    @Override
    public boolean deleteById(long id) throws ServiceException {
        return false;
    }

    @Override
    public boolean delete(Object entity) throws ServiceException {
        return false;
    }
}
