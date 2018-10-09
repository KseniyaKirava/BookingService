package by.htp.kirova.task2.java.service.validation;

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
    USERNAME("[[A-Za-z._-]+]{2,15}"),

    /**
     * User's password pattern.
     */
    PASSWORD("[\\w]{6,15}"),

    /**
     * User's name pattern.
     */
    NAME("[[A-Za-zА-Яа-яЁё]+]{2,15}"),

    /**
     * Entity name pattern.
     */
    ENTITY_NAME("[[A-Za-zА-Яа-яЁё\\d, -]+]{1,50}"),

    /**
     * E-mail pattern.
     */
    EMAIL("([\\w\\.\\w]+)@(\\w+\\.)([a-z]{2,4})"),

    /**
     * Date pattern.
     */
    DATE("([0-9]{2})([\\.])([0-9]{2})([\\.])([0-9]{4})");



    private Pattern pattern;

    ValidationPattern(String regEx){
        this.pattern = Pattern.compile(regEx);
    }

    public Pattern getPattern() {
        return pattern;
    }
}


