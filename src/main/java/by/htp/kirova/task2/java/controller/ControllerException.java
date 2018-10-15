package by.htp.kirova.task2.java.controller;

/**
 * Custom exception used to determine exceptions in the Controller Layer.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class ControllerException extends Exception {

    /**
     * The unique serial version identifier.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new controller exception with {@code null} as its
     * detail message. The cause is not initialized, and may subsequently
     * be initialized by a call to {@link #initCause}.
     */
    public ControllerException() {
    }

    /**
     * Constructs a new controller exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param   message  the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
    public ControllerException(String message) {
        super(message);
    }

    /**
     * Constructs a new controller exception with the specified detail
     * message and cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this exception's detail message.
     *
     * @param  message the detail message (which is saved for later retrieval
     *         by the {@link #getMessage()} method).
     * @param  e the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A {@code null} value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     */
    public ControllerException(String message, Exception e) {
        super(message, e);
    }

    /**
     * Constructs a new controller exception with the specified cause and a detail
     * message of {@code (cause==null ? null : cause.toString())} (which
     * typically contains the class and detail message of {@code cause}).
     * This constructor is useful for exceptions that are little more than
     * wrappers for other throwables (for example, {@link
     * java.security.PrivilegedActionException}).
     *
     * @param  e the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A {@code null} value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     */
    public ControllerException(Exception e) {
        super(e);
    }
}
