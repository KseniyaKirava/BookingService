package by.htp.kirova.task2.java.util;

import by.htp.kirova.task2.java.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Util {
    public static User getUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object objUser = session.getAttribute("user");
        if (objUser != null) {
            return (User) objUser;
        }
        return null;
    }

    public static double getTotalCost(long checkin, long checkout, double cost) {
        int daysCount = (int) ((checkout - checkin) / (1000 * 60 * 60 * 24));
        double totalCost = daysCount * cost;
        return totalCost;
    }

}
