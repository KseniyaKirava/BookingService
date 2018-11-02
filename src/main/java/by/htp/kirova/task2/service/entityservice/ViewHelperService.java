package by.htp.kirova.task2.service.entityservice;

import by.htp.kirova.task2.dao.DAOException;
import by.htp.kirova.task2.dao.DAOFactory;
import by.htp.kirova.task2.dao.HelperDAO;
import by.htp.kirova.task2.service.HelperService;
import by.htp.kirova.task2.service.ServiceException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides the implementation of methods for correct View layer.
 *
 * @author Kseniya Kirava
 * @since Oct 16, 2018
 */
public class ViewHelperService implements HelperService {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(ViewHelperService.class);


    @Override
    public List<String> avialiableRooms(Long id) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        HelperDAO helperDAO = daoFactory.getHelperDAO();

        List<String> result = null;

        try {
            result = helperDAO.avialiableRooms(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }


    @Override
    public List<ArrayList<Object>> allReservations(String where) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        HelperDAO helperDAO = daoFactory.getHelperDAO();

        List<ArrayList<Object>> result = null;

        try {
            result = helperDAO.allReservations(where);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }
}
