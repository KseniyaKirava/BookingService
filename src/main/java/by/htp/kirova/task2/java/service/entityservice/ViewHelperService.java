package by.htp.kirova.task2.java.service.entityservice;

import by.htp.kirova.task2.java.connectionpool.ConnectionPool;
import by.htp.kirova.task2.java.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.dao.DAOFactory;
import by.htp.kirova.task2.java.dao.DateConverter;
import by.htp.kirova.task2.java.dao.HelperDAO;
import by.htp.kirova.task2.java.entity.User;
import by.htp.kirova.task2.java.service.HelperService;
import by.htp.kirova.task2.java.service.ServiceException;
import org.apache.log4j.Logger;

import javax.sql.rowset.serial.SerialException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public List<ArrayList> showRequestsByUser(User user) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        HelperDAO helperDAO = daoFactory.getHelperDAO();

        List<ArrayList> result = null;

        try {
            result = helperDAO.showRequestsByUser(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

}
