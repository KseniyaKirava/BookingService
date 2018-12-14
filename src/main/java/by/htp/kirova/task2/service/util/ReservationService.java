package by.htp.kirova.task2.service.util;

/**
 * Contains methods which provide several logic to work with reservations.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class ReservationService {

    /**
     * Provides total cost by all nights stay.
     *
     * @param checkinDate  date of arrival.
     * @param checkoutDate date of departure.
     * @param cost         cost per night.
     * @return total cost by all nights stay.
     */
    public static double getTotalCost(long checkinDate, long checkoutDate, double cost) {
        int daysCount = (int) ((checkoutDate - checkinDate) / (1000 * 60 * 60 * 24));
        return daysCount * cost;
    }

}
