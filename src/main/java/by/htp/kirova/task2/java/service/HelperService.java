package by.htp.kirova.task2.java.service;

import by.htp.kirova.task2.java.entity.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides methods for correct View layer.
 *
 * @author Kseniya Kirava
 * @since Oct 16, 2018
 */
public interface HelperService {

    /**
     * Show user's requests info.
     *
     * @param user user.
     * @return List of ArrayList requests info.
     * @throws ServiceException Layer
     */
    List<ArrayList> showRequestsByUser(User user) throws ServiceException;
}
