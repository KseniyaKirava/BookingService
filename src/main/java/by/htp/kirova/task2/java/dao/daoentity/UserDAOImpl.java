package by.htp.kirova.task2.java.dao.daoentity;


import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.dao.GenericDAO;
import by.htp.kirova.task2.java.entity.User;
import org.apache.log4j.Logger;

import java.util.List;


/**
 * Provides User with an opportunity to retrieve, change and delete data from
 * the relevant database table.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class UserDAOImpl implements GenericDAO<User> {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class);

    @Override
    public User findById(long id) throws DAOException {
        return null;
    }

    @Override
    public boolean deleteById(long id) throws DAOException {
        return false;
    }

    @Override
    public boolean create(User entity) throws DAOException {
        return false;
    }

    @Override
    public List<User> findAll(String where) throws DAOException {
        return null;
    }

    @Override
    public boolean update(User entity) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(User entity) throws DAOException {
        return false;
    }
}
