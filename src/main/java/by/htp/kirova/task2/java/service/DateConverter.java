package by.htp.kirova.task2.java.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Converts the date from milliseconds to the String format dd.MM.yyyy for display to the user and
 * converts to milliseconds for storage in the database.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class DateConverter {

    /**
     * Сonverts String Date to milliseconds for storage in the database
     *
     * @param stringDate date in String format dd.MM.yyyy
     * @return date in miliseconds for entries in Database
     * @throws ParseException if parsing is unsuccessful
     */
    public static Long convertDateToMiliseconds(String stringDate) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        Date date = df.parse(stringDate);
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        return date.getTime();
    }

    /**
     * Converts the Date from milliseconds to the String format dd.MM.yyyy
     *
     * @param miliseconds date in miliseconds
     * @return date in String format dd.MM.yyyy
     */
    public static String convertDateToString(Long miliseconds){
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        Date date = new Date(miliseconds);
        return df.format(date);
    }

}
