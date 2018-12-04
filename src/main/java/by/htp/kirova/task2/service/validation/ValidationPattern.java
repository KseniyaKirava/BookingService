package by.htp.kirova.task2.service.validation;

import java.util.regex.Pattern;

/**
 * Contains precompiled patterns for validating user's data.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */

enum ValidationPattern {

    /**
     * User's full name pattern.
     */
    USERNAME("[A-Za-z._-]{4,15}"),

    /**
     * User's password pattern.
     */
    PASSWORD("[\\w]{5,15}"),

    /**
     * User's first name/last name pattern.
     */
    NAME("[A-Za-zА-Яа-яЁё-]{2,15}"),

    /**
     * User's middle name pattern.
     */
    MIDDLE_NAME("[A-Za-zА-Яа-яЁё-]{0,15}"),

    /**
     * Room name pattern.
     */
    ENTITY_NAME("[A-Za-zА-Яа-яЁё\\d -]{1,50}"),

    /**
     * Room class name pattern.
     */
    ROOM_CLASS_NAME("[A-Za-zА-Яа-яЁё\\d -]{1,15}"),

    /**
     * Room number pattern.
     */
    ROOM_NUMBER("[\\w]{1,15}"),

    /**
     * E-mail pattern.
     */
    EMAIL("([\\w\\.\\w]+)@(\\w+\\.)([a-z]{2,4})"),

    /**
     * Date pattern.
     */
    DATE("([0-9]{2})([\\.])([0-9]{2})([\\.])([0-9]{4})"),

    /**
     * Authority pattern.
     */
    AUTHORITY ("[A-Za-z]{4,10}");



    private Pattern pattern;

    ValidationPattern(String regEx){
        this.pattern = Pattern.compile(regEx);
    }

    public Pattern getPattern() {
        return pattern;
    }
}


