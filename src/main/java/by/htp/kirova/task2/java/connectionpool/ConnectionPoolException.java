package by.htp.kirova.task2.java.connectionpool;

/**
 * Custom exception used to determine exceptions in the connection pool.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class ConnectionPoolException extends Exception {

    private static final long serialVersionUID = 1L;



    public ConnectionPoolException() {
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }

    public ConnectionPoolException(Exception e) {
        super(e);
    }


}

