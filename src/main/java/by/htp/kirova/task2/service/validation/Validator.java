package by.htp.kirova.task2.service.validation;

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
    private static final Logger logger = Logger.getLogger(Validator.class);


    /**
     * Instance of Validator is used for data validation.
     */
    private static final Validator instance = new Validator();

    /**
     * Default private constructor as this class entirely utility.
     */
    private Validator() {
    }


    public static Validator getInstance() {
        return instance;
    }



    /**
     * Checks the username against user's username pattern.
     *
     * @param username user's username.
     * @return {@code true} in case of success and false otherwise.
     */
    public boolean checkUsername(String username) {
        boolean isValid = username != null && ValidationPattern.USERNAME.getPattern().matcher(username).matches();
        logger.info("Username is valid: " + isValid);
        return isValid;
    }

    /**
     * Checks the first name/last name against user's name pattern.
     *
     * @param name user's first name/last name.
     * @return {@code true} in case of success and false otherwise.
     */
    public boolean checkName(String name) {
        boolean isValid = name != null && ValidationPattern.NAME.getPattern().matcher(name).matches();
        logger.info("User name is valid: " + isValid);
        return isValid;
    }

    /**
     * Checks the middle name against user's name pattern.
     *
     * @param middleName user's middle name.
     * @return {@code true} in case of success and false otherwise.
     */
    public boolean checkMiddleName(String middleName) {
        boolean isValid = middleName == null || middleName.equals("") || ValidationPattern.MIDDLE_NAME.getPattern().
                matcher(middleName).matches();
        logger.info("User name is valid: " + isValid);
        return isValid;
    }

    /**
     * Checks the name against entity's name pattern.
     *
     * @param entityName entity name.
     * @return {@code true} in case of success and false otherwise.
     */
    public boolean checkEntityName(String entityName) {
        boolean isValid = entityName != null && ValidationPattern.ENTITY_NAME.getPattern().matcher(entityName).matches();
        logger.info("User name is valid: " + isValid);
        return isValid;
    }

    /**
     * Checks the e-mail against user's e-mail pattern.
     *
     * @param email user's e-mail.
     * @return {@code true} in case of success and false otherwise.
     */
    public boolean checkEmail(String email) {
        boolean isValid = email != null && ValidationPattern.EMAIL.getPattern().matcher(email).matches();
        logger.info("User e-mail is valid: " + isValid);
        return isValid;
    }

    /**
     * Checks the password against user's password pattern.
     *
     * @param password user's password.
     * @return {@code true} in case of success and false otherwise.
     */
    public boolean checkPassword(String password) {
        boolean isValid = password != null && ValidationPattern.PASSWORD.getPattern().matcher(password).matches();
        logger.info("User password is valid: " + isValid);
        return isValid;
    }

    /**
     * Checks the checkin/checkout/registration date against date pattern.
     *
     * @param date checkin/checkout/registration date
     * @return {@code true} in case of success and false otherwise.
     */
    public boolean checkDate(String date) {
        boolean isValid = date!=null && ValidationPattern.DATE.getPattern().matcher(date).matches();
        logger.info("Date is valid: " + isValid);
        return isValid;
    }


    public boolean checkCapacity(int capacity){
        boolean isValid = capacity > 0 && capacity < 6;
            logger.info("Capacity is valid: " + isValid);
        return isValid;
    }

}



