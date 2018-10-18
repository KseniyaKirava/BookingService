package by.htp.kirova.task2.java.dao;

import by.htp.kirova.task2.java.entity.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides DAO methods for correct View layer.
 *
 * @author Kseniya Kirava
 * @since Oct 16, 2018
 */
public interface HelperDAO {

    /**
     * Show user's requests info.
     *
     * @param user user.
     * @return List of ArrayList requests info.
     * @throws DAOException Layer
     */
    List<ArrayList> showRequestsByUser(User user) throws DAOException;
}
