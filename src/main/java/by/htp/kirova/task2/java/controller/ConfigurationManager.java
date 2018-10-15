package by.htp.kirova.task2.java.controller;

import by.htp.kirova.task2.java.dao.PropertyCreator;

import java.util.Properties;

/**
 * Configuration manager for properties.
 *
 * @author Kirava Kseniya
 * @since Oct 12, 2018
 */
public class ConfigurationManager {

    /**
     * Config properties.
     */
    private static final Properties CONFIG_PROPERTIES = PropertyCreator.createProperties("config.properties");

    /**
     * The private constructor by default because this class is not intended
     * to create an instance of the class or inheritance.
     */
    private ConfigurationManager() {
    }

    /**
     * Returns a key value from a config.properties file
     *
     * @param key by which the value is searched
     * @return java.lang.String value by key on config.properties file
     */
    public static String getParameter(String key) {
        return CONFIG_PROPERTIES.getProperty(key);
    }
}
