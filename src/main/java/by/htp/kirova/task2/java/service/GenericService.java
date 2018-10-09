package by.htp.kirova.task2.java.service;

import java.util.List;

/**
 * Provides default model for GenericService objects, which ensures uniformity.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public interface GenericService<T> {

    /**
     * Creates object T in Database.
     *
     * @param entity concrete instance which is meant to be created.
     * @return boolean {@code true} in case of success and {@code false} otherwise.
     * @throws ServiceException
     */
    boolean create(T entity) throws ServiceException;


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
     * Deletes specific object based on equality with the parameter.
     *
     * @param entity parameter which specifies concrete object.
     * @return boolean {@code true} in case of success and {@code false} otherwise.
     * @throws ServiceException
     */
    boolean delete(T entity) throws ServiceException;

}
