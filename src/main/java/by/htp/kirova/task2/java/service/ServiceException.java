package by.htp.kirova.task2.java.service;


/**
 * Custom exception used to determine exceptions in the Service Layer.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class ServiceException extends Exception {

    private static final long serialVersionUID = 1L;


    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Exception e) {
        super(message, e);
    }

    public ServiceException(Exception e) {
        super(e);
    }
}
