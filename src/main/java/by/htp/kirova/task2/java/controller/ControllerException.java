package by.htp.kirova.task2.java.controller;

/**
 * Custom exception used to determine exceptions in the Controller Layer.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class ControllerException extends Exception {

    private static final long serialVersionUID = 1L;



    public ControllerException() {
    }

    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(String message, Exception e) {
        super(message, e);
    }

    public ControllerException(Exception e) {
        super(e);
    }
}
