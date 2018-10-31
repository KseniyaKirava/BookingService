package by.htp.kirova.task2.controller;

import java.util.ResourceBundle;

/**
 * Message manager for messages.
 *
 * @author Kirava Kseniya
 * @since Oct 12, 2018
 */
public class MessageManager {

    /**
     * Resource bundle constant for message.properties.
     */
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

    /**
     * The private constructor by default because this class is not intended
     * to create an instance of the class or inheritance.
     */
    private MessageManager() {
    }

    /**
     * Returns a key value from a message.properties file
     *
     * @param key by which the value is searched
     * @return java.lang.String value by key on message.properties file
     */
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
