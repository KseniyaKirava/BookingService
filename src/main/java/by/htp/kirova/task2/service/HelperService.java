package by.htp.kirova.task2.service;

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
     * @param id unique identifical number of request.
     * @return List of  avaliable rooms.
     * @throws ServiceException Layer
     */
    List<String> avialiableRooms(Long id) throws ServiceException;

    /**
     * Show user's reservations.
     *
     * @param where query.
     * @return List of Arraylist user's reservations.
     * @throws ServiceException Layer
     */
    List<ArrayList<Object>> allReservations(String where) throws ServiceException;
}
