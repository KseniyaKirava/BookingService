package by.htp.kirova.task2.java.service.validation;


import by.htp.kirova.task2.java.entity.User;
import by.htp.kirova.task2.java.service.GenericService;
import by.htp.kirova.task2.java.service.ServiceFactory;
import org.apache.log4j.Logger;

/**
 * Validates data such as e-mail, name and password, by checking it against
 * predefined patterns. Utility class, therefore final as it's not designed for
 * instantiation and extension.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */

public final class Validator {


    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(Validator.class);

    /**
     * Default private constructor as this class entirely utility.
     */
    private Validator() {
    }

    /**
     * Checks the username against user's username pattern.
     *
     * @param username user's username.
     * @return {@code true} in case of success and false otherwise.
     */
    public static boolean checkUsername(String username) {
        boolean isValid = username != null && ValidationPattern.USERNAME.getPattern().matcher(username).matches();
        LOGGER.info("Username is valid: " + isValid);
        return isValid;
    }

    /**
     * Checks the first name/last name against user's name pattern.
     *
     * @param name user's first name/last name.
     * @return {@code true} in case of success and false otherwise.
     */
    public static boolean checkName(String name) {
        boolean isValid = name != null && ValidationPattern.NAME.getPattern().matcher(name).matches();
        LOGGER.info("User name is valid: " + isValid);
        return isValid;
    }

    /**
     * Checks the middle name against user's name pattern.
     *
     * @param middleName user's middle name.
     * @return {@code true} in case of success and false otherwise.
     */
    public static boolean checkMiddleName(String middleName) {
        boolean isValid = middleName == null || middleName.equals("") || ValidationPattern.MIDDLE_NAME.getPattern().
                matcher(middleName).matches();
        LOGGER.info("User name is valid: " + isValid);
        return isValid;
    }

    /**
     * Checks the name against entity's name pattern.
     *
     * @param entityName entity name.
     * @return {@code true} in case of success and false otherwise.
     */
    public static boolean checkEntityName(String entityName) {
        boolean isValid = entityName != null && ValidationPattern.ENTITY_NAME.getPattern().matcher(entityName).matches();
        LOGGER.info("User name is valid: " + isValid);
        return isValid;
    }

    /**
     * Checks the e-mail against user's e-mail pattern.
     *
     * @param email user's e-mail.
     * @return {@code true} in case of success and false otherwise.
     */
    public static boolean checkEmail(String email) {
        boolean isValid = email != null && ValidationPattern.EMAIL.getPattern().matcher(email).matches();
        LOGGER.info("User e-mail is valid: " + isValid);
        return isValid;
    }

    /**
     * Checks the password against user's password pattern.
     *
     * @param password user's password.
     * @return {@code true} in case of success and false otherwise.
     */
    public static boolean checkPassword(String password) {
        boolean isValid = password != null && ValidationPattern.PASSWORD.getPattern().matcher(password).matches();
        LOGGER.info("User password is valid: " + isValid);
        return isValid;
    }

    /**
     * Checks the checkin/checkout/registration date against date pattern.
     *
     * @param date checkin/checkout/registration date
     * @return {@code true} in case of success and false otherwise.
     */
    public static boolean checkDate(String date) {
        boolean isValid = date!=null && ValidationPattern.DATE.getPattern().matcher(date).matches();
        LOGGER.info("Date is valid: " + isValid);
        return isValid;
    }


    public static boolean checkCapacity(int capacity){
        boolean isValid = capacity > 0 && capacity < 6;
            LOGGER.info("Capacity is valid: " + isValid);
        return isValid;
    }

}



