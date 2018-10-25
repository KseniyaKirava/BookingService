package by.htp.kirova.task2.java.service;

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
     * @return List of ArrayList avaliable rooms.
     * @throws ServiceException Layer
     */
    List showAvialiableRooms(Long id) throws ServiceException;
}
