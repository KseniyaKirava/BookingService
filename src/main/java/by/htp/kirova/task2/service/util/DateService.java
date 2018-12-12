package by.htp.kirova.task2.service.util;

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
public class DateService {

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

    /**
     * Create current Date in milliseconds.

     * @return date in date in miliseconds.
     */
    public static Long getCurrentDateInMiliseconds(){
        Date date = new Date();
        return date.getTime();
    }


    /**
     * Allows to find out the current date is more or less than the specified date plus 3 days

     * @return boolean {@code true} if date before current date and {@code false} otherwise.
     */
    public static boolean isDateBeforeCurrentDate(long date){
        if (date == 0) {
            return false;
        }
        Long currentDateInMiliseconds = getCurrentDateInMiliseconds();
        return currentDateInMiliseconds > (date + 3*(24*60*60*1000));
    }

}
