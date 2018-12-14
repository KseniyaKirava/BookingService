package by.htp.kirova.task2.controller;

import java.util.ResourceBundle;

/**
 * Configuration manager for properties.
 *
 * @author Kirava Kseniya
 * @since Oct 12, 2018
 */
public class ConfigurationManager {

    /**
     * The resource name constant.
     */
    private final static String CONFIG = "config";


    /**
     * Resource bundle constant for config.properties.
     */
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(CONFIG);

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
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
