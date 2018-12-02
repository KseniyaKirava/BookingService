package by.htp.kirova.task2.service.util;

import by.htp.kirova.task2.controller.command.CommandException;
import by.htp.kirova.task2.entity.User;
import by.htp.kirova.task2.service.BookingService;
import by.htp.kirova.task2.service.ServiceException;
import by.htp.kirova.task2.service.ServiceFactory;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class Util {


//    public static void main(String[] args) {
//        String pass = getHashPassword("45d45d");
//        System.out.println(pass);
//
//    }

    public static User checkLogin(String username, String password) throws CommandException {
        String hashPassword = getHashPassword(password);
        String where = String.format("WHERE username='%s' AND password='%s' AND enabled=true LIMIT 0,1", username, hashPassword);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        User user = null;

        try {
            List<User> users = serviceFactory.getUserService().read(where);
            if (users.size() > 0) {
                user = users.get(0);
            }
        } catch (ServiceException e) {
            throw new CommandException("", e);
        }

        return user;
    }


    public static String getHashPassword(String password) {
        String salt = "rand"; // в реальных проектах происходит генерация разной соли для каждого случая
        String hashpass = DigestUtils.sha256Hex(password + salt);
        return hashpass;
    }


    public static boolean isUsernameUnique(String username) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookingService<User> userService = serviceFactory.getUserService();
        List<User> list = null;
        try {
            list = userService.read("WHERE username like '" + username + "'");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return list.isEmpty() || list == null;
    }


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
