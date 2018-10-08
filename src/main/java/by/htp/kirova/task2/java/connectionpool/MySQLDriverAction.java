package by.htp.kirova.task2.java.connectionpool;

import org.apache.log4j.Logger;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Registers and derigesters driver for MySQL database. Utility class, therefore
 * final as it's not designed for instantiation and extension.
 *
 * @author Kseniya Kirava
 * @since Oct 2, 2018
 */
final class MySQLDriverAction {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(MySQLDriverAction.class);

    /**
     * Private default constructor as this class is not designed to be instantiated
     * or extended.
     */
    private MySQLDriverAction() {
    }

    /**
     * Registers MySQL database drivers.
     *
     * @return True in case of success and false otherwise.
     */
    static boolean registerDrivers() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            LOGGER.warn("Drivers have not been registered", e);
            return false;
        }
        return true;
    }

    /**
     * Deregisters MySQL database drivers.
     *
     * @return True in case of success and false otherwise.
     */
    static boolean deregisterDriver() {

        DriverManager.drivers().forEach((Driver s) -> {
            try {
                DriverManager.deregisterDriver(s);
            } catch (SQLException e) {
                LOGGER.warn("Drivers have not been registered", e);
            }
        });
        return true;
    }
}

