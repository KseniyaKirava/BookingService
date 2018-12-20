package by.htp.kirova.task2.service.util;

import by.htp.kirova.task2.controller.command.CommandException;
import by.htp.kirova.task2.entity.User;
import by.htp.kirova.task2.service.BookingService;
import by.htp.kirova.task2.service.ServiceException;
import by.htp.kirova.task2.service.ServiceFactory;
import by.htp.kirova.task2.service.validation.Validator;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/**
 * Contains methods which provide several logic to work with user.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class UserService {


//    public static void main(String[] args) {
//        String pass = getHashPassword("45d45d");
//        System.out.println(pass);
//
//    }

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger logger = Logger.getLogger(UserService.class);

    /**
     * Constant string which represents query to check login.
     */
    private static final String CHECK_LOGIN_WHERE_QUERY = "WHERE username like '%s' AND password like '%s' AND enabled=true LIMIT 0,1";

    /**
     * Constant string which represents query to check login.
     */
    private static final String USER_BY_USERNAME_WHERE_QUERY = "WHERE username like '%s' LIMIT 0,1";

    /**
     * Checks the entered login-password pair for correctness and
     * presence in the database.
     *
     * @param username unique identification name.
     * @param password user's password.
     * @return user, which found in the database.
     * @throws CommandException if user reading end unsuccessful.
     */
    public static User checkLoginAndPassword(String username, String password) throws CommandException {

        Validator validator = Validator.getInstance();
        if (!validator.checkUsername(username) || !validator.checkPassword(password)) {
            logger.debug("Validation by username & password failed or user not found");
            return null;
        }

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


    /**
     * Provides a hash password for writing to the database
     *
     * @param password user's password.
     * @return hashing password.
     */
    public static String getHashPassword(String password) {
        String salt = "rand"; // в реальных проектах происходит генерация разной соли для каждого случая
        return DigestUtils.sha256Hex(password + salt);
    }

    /**
     * Provides a user by username.
     *
     * @param username unique identification name.
     * @return user, which found in the database.
     * @throws CommandException if user reading end unsuccessful.
     */
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

    /**
     * Checks uniqueness username.
     *
     * @param username unique identification name.
     * @return boolean {@code true} in case of success and {@code false} otherwise.
     * @throws CommandException if user reading end unsuccessful.
     */
    public static boolean isUsernameUnique(String username) throws CommandException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookingService<User> userService = serviceFactory.getUserService();

        String where = String.format(USER_BY_USERNAME_WHERE_QUERY, username);

        List<User> list;

        try {
            list = userService.read(where);
        } catch (ServiceException e) {
            throw new CommandException("Reading user's data failed", e);
        }
        return list.isEmpty();
    }

    /**
     * Provides the user from the session.
     *
     * @param request HttpServletRequest.
     * @return user, which found in session.
     */
    public static User getUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object objUser = session.getAttribute("user");
        if (objUser != null) {
            return (User) objUser;
        }
        return null;
    }

}
