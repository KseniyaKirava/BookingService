package by.htp.kirova.task2.java.controller;

import by.htp.kirova.task2.java.dao.PropertyCreator;

import java.util.Properties;

/**
 * Message manager for properties.
 *
 * @author Kirava Kseniya
 * @since Oct 10, 2018
 */
public class MessageManager {

    /**
     * Message properties.
     */
    private static final Properties MESSAGE_PROPERTIES = PropertyCreator.createProperties("message.properties");

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
    public static String getParameter(String key) {
        return MESSAGE_PROPERTIES.getProperty(key);
    }
}
