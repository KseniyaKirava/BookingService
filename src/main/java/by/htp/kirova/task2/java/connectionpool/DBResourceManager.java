package by.htp.kirova.task2.java.connectionpool;

import java.util.ResourceBundle;

/**
 * Database resource manager for properties.
 *
 * @author Kirava Kseniya
 * @since Sep 24, 2018
 */
public class DBResourceManager {

    /**
     * Instance of the class.
     */
    private final static DBResourceManager instance = new DBResourceManager();

    /**
     * Access to the file settings db.properties
     */
    private ResourceBundle bundle = ResourceBundle.getBundle("db");

    /**
     * Returns only one instance of the class.
     *
     * @return instance of the class.
     */
    public static DBResourceManager getInstance() {
        return instance;
    }

    /**
     * Returns a key value from a file db.properties
     *
     * @param key by which the value is searched
     * @return value of db.properties file
     */
    public String getValue(String key) {
        return bundle.getString(key);
    }
}
