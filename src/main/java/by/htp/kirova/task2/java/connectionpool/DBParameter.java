package by.htp.kirova.task2.java.connectionpool;

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
    public static final String DB_DRIVER = "db.driver";

    /**
     * Pointer to Database URL in prorerties file.
     */
    public static final String DB_URL = "db.url";

    /**
     * Pointer to Database user name in prorerties file.
     */
    public static final String DB_USER = "db.user";

    /**
     * Pointer to Database password in prorerties file.
     */
    public static final String DB_PASSWORD = "db.password";

    /**
     * Pointer to Connection Pool size in prorerties file.
     */
    public static final String DB_POOL_SIZE = "db.poolsize";

    private DBParameter() {
    }

}


//    static final String DB_DRIVER = "com.mysql.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://localhost:2016/kirova" +
//            "?useUnicode=true&characterEncoding=UTF-8" +
//            "&verifyServerCertificate=false"+
//            "&useSSL=false"+
//            "&requireSSL=false"+
//            "&useLegacyDatetimeCode=false"+
//            "&amp"+
//            "&serverTimezone=UTC";
//    static final String DB_USER = "root";
//    static final String DB_PASSWORD = "12345";
//    static final String DB_POOL_SIZE = "5";
