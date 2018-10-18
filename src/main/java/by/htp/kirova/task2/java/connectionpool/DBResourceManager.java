package by.htp.kirova.task2.java.connectionpool;

import by.htp.kirova.task2.java.util.PropertyCreator;

import java.util.Properties;

/**
 * Database resource manager for properties.
 *
 * @author Kirava Kseniya
 * @since Sep 24, 2018
 */
public class DBResourceManager {

    /**
     * DB resources properties.
     */
    private static final Properties DB_PROPERTIES = PropertyCreator.createProperties("db.properties");

    /**
     * The private constructor by default because this class is not intended
     * to create an instance of the class or inheritance.
     */
    private DBResourceManager() {
    }

    /**
     * Returns a key value from a db.properties file
     * @param key by which the value is searched
     * @return java.lang.String value by key on db.properties file
     */
    public static String getParameter(String key) {
        return DB_PROPERTIES.getProperty(key);
    }

}
