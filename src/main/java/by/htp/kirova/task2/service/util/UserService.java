package by.htp.kirova.task2.service.util;

import by.htp.kirova.task2.controller.command.CommandException;
import by.htp.kirova.task2.entity.User;
import by.htp.kirova.task2.service.BookingService;
import by.htp.kirova.task2.service.ServiceException;
import by.htp.kirova.task2.service.ServiceFactory;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class UserService {


//    public static void main(String[] args) {
//        String pass = getHashPassword("45d45d");
//        System.out.println(pass);
//
//    }

    /**
     * Constant string which represents query to check login.
     */
    private static final String CHECK_LOGIN_WHERE_QUERY = "WHERE username='%s' AND password='%s' AND enabled=true LIMIT 0,1";

    /**
     * Constant string which represents query to check login.
     */
    private static final String USER_BY_USERNAME_WHERE_QUERY = "WHERE username='%s' LIMIT 0,1";


    public static User checkLogin(String username, String password) throws CommandException {
        String hashPassword = getHashPassword(password);
        String where = String.format(CHECK_LOGIN_WHERE_QUERY, username, hashPassword);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        User user = null;

        try {
            List<User> users = serviceFactory.getUserService().read(where);
            if (!users.isEmpty()) {
                user = users.get(0);
            }
        } catch (ServiceException e) {
            throw new CommandException("Reading user's data failed", e);
        }

        return user;
    }


    public static String getHashPassword(String password) {
        String salt = "rand"; // в реальных проектах происходит генерация разной соли для каждого случая
        return DigestUtils.sha256Hex(password + salt);
    }


    public static User getUserByUsername(String username) throws CommandException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookingService<User> userService = serviceFactory.getUserService();

        String where = String.format(USER_BY_USERNAME_WHERE_QUERY, username);

        User user = null;

        try {
            List<User> users = userService.read(where);
            if (!users.isEmpty()) {
                user = users.get(0);
            }
        } catch (ServiceException e) {
            throw new CommandException("Reading user's data failed", e);
        }

        return user;
    }

    public static boolean isUsernameUnique(String username) throws CommandException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookingService<User> userService = serviceFactory.getUserService();

        String where = String.format(USER_BY_USERNAME_WHERE_QUERY, username);

        List<User> list = new ArrayList<>();

        try {
            list = userService.read(where);
        } catch (ServiceException e) {
            throw new CommandException("Reading user's data failed", e);
        }
        return list.isEmpty();
    }


    public static User getUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object objUser = session.getAttribute("user");
        if (objUser != null) {
            return (User) objUser;
        }
        return null;
    }

}
