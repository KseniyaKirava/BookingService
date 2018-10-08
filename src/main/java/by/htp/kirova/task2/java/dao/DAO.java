package by.htp.kirova.task2.java.dao;


import java.util.List;

/**
 * Provides default model for Dao objects, which ensures uniformity.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public interface DAO<T> {


    /**
     * Creates object which in Database.
     *
     * @param entity concrete instance which is meant to be created.
     * @return boolean {@code true} in case of success and {@code false} otherwise.
     * @throws DAOException
     */
    boolean create(T entity) throws DAOException;


    /**
     * Returns all objects which match SQL search condition.
     *
     * @return java.util.List<T> All results collected in {@link java.util.List}.
     * @param where SQL search condition.
     * @throws DAOException
     */
    List<T> findAll(String where) throws DAOException;


    /**
     * Updates object based on equality with the parameter.
     *
     * @param entity parameter which specifies concrete object.
     * @return boolean {@code true} in case of success and {@code false} otherwise.
     * @throws DAOException
     */
    boolean update(T entity) throws DAOException;


    /**
     * Deletes specific object based on equality with the parameter.
     *
     * @param entity parameter which specifies concrete object.
     * @return boolean {@code true} in case of success and {@code false} otherwise.
     * @throws DAOException
     */
    boolean delete(T entity) throws DAOException;


}
