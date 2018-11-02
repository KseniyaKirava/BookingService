package by.htp.kirova.task2.dao;

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
     * @param id unique identifical number of request.
     * @return List of avaliable rooms.
     * @throws DAOException Layer
     */
    List<String> avialiableRooms(Long id) throws DAOException;

    /**
     * Show user's reservations.
     *
     * @param username unique user's name.
     * @return List of Arraylist user's reservations.
     * @throws DAOException Layer
     */
    List<ArrayList<Object>> allReservations(String username) throws DAOException;
}
