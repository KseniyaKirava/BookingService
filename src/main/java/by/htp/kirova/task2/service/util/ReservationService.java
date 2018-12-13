package by.htp.kirova.task2.service.util;

public class ReservationService {

    public static double getTotalCost(long checkinDate, long checkoutDate, double cost) {
        int daysCount = (int) ((checkoutDate - checkinDate) / (1000 * 60 * 60 * 24));
        return daysCount * cost;
    }

}
