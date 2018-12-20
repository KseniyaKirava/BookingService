package by.htp.kirova.task2.dao.connectionpool;

import java.util.ResourceBundle;

/**
 * Database resource manager for properties.
 *
 * @author Kirava Kseniya
 * @since Sep 24, 2018
 */
public class DBResourceManager {

    /**
     * The resource name constant.
     */
    private static final String DB = "db";

    /**
     * Resource bundle constant for DataBase.
     */
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(DB);

    /**
     * The private constructor by default because this class is not intended
     * to create an instance of the class or inheritance.
     */
    private DBResourceManager() {
    }

    /**
     * Returns a key value from a db.properties file
     *
     * @param key by which the value is searched
     * @return java.lang.String value by key on db.properties file
     */
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }

}

