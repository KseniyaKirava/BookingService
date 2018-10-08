package by.htp.kirova.task2.java.dao;


/**
 * Custom exception used to determine exceptions in the DAO Layer.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class DAOException extends Exception {

    private static final long serialVersionUID = 1L;



    public DAOException() {
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Exception e) {
        super(message, e);
    }

    public DAOException(Exception e) {
        super(e);
    }
}
