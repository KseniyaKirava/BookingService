package by.htp.kirova.task2.java.util;

import by.htp.kirova.task2.java.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Util {
    static User getUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object objUser = session.getAttribute("user");
        if (objUser != null) {
            return (User) objUser;
        }
        return null;
    }

}
