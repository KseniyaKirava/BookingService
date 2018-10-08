package by.htp.kirova.task2.java.service;

import java.util.List;

/**
 * Provides default model for Service objects, which ensures uniformity.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public interface Service<T> {

    /**
     * Creates object which in Database.
     *
     * @param entity concrete instance which is meant to be created.
     * @return boolean {@code true} in case of success and {@code false} otherwise.
     * @throws ServiceException
     */
    boolean create(T entity) throws ServiceException;


    /**
     * Returns objects which has a specific Id.
     *
     * @param id parameter which specifies Id.
     * @return concrete instance result object.
     * @throws ServiceException
     */
    T findById(long id) throws ServiceException;


    /**
     * Returns all objects which match SQL search condition.
     *
     * @return java.util.List<T> All results collected in {@link java.util.List}.
     * @param where SQL search condition.
     * @throws ServiceException
     */
    List<T> findAll(String where) throws ServiceException;


    /**
     * Updates object based on equality with the parameter.
     *
     * @param entity parameter which specifies concrete object.
     * @return boolean {@code true} in case of success and {@code false} otherwise.
     * @throws ServiceException
     */
    boolean update(T entity) throws ServiceException;


    /**
     * Deletes object based on its Id.
     *
     * @param id parameter which specifies Id.
     * @return boolean {@code true} in case of success and {@code false} otherwise.
     * @throws ServiceException
     */
    boolean deleteById(long id) throws ServiceException;


    /**
     * Deletes specific object based on equality with the parameter.
     *
     * @param entity parameter which specifies concrete object.
     * @return boolean {@code true} in case of success and {@code false} otherwise.
     * @throws ServiceException
     */
    boolean delete(T entity) throws ServiceException;

}
