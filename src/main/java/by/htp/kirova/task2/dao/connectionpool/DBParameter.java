package by.htp.kirova.task2.dao.connectionpool;


/**
 * Database constant parameters for Connection Pool.
 *
 * @author Kirava Kseniya
 * @since Sep 24, 2018
 */

final class DBParameter {

    /**
     * Pointer to Database driver name in prorerties file.
     */
    static final String DB_DRIVER = "db.driver";

    /**
     * Pointer to Database URL in prorerties file.
     */
    static final String DB_URL = "db.url";

    /**
     * Pointer to Database user name in prorerties file.
     */
    static final String DB_USER = "db.user";

    /**
     * Pointer to Database password in prorerties file.
     */
    static final String DB_PASSWORD = "db.password";

    /**
     * Pointer to Connection Pool size in prorerties file.
     */
    static final String DB_POOL_SIZE = "db.poolsize";

    /**
     * Private default constructor as this class is not designed to be instantiated
     * or extended.
     */
    private DBParameter() {
    }

}
