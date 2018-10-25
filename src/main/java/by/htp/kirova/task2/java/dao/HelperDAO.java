package by.htp.kirova.task2.java.dao;

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
     * @return List of ArrayList avaliable rooms.
     * @throws DAOException Layer
     */
    List showAvialiableRooms(Long id) throws DAOException;
}
