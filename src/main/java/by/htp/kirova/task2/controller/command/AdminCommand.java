package by.htp.kirova.task2.controller.command;

import by.htp.kirova.task2.controller.MessageManager;
import by.htp.kirova.task2.entity.User;
import by.htp.kirova.task2.service.BookingService;
import by.htp.kirova.task2.service.ServiceException;
import by.htp.kirova.task2.service.ServiceFactory;
import by.htp.kirova.task2.service.util.Util;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminCommand extends Command {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(AdminCommand.class);

    /**
     * The unique identification name constant.
     */
    private final static String USERNAME = "username";

    /**
     * The user's password constant.
     */
    private final static String PASSWORD = "password";

    /**
     * The user's e-mail constant.
     */
    private final static String EMAIL = "email";

    /**
     * The user's first name constant.
     */
    private final static String FIRST_NAME = "firstName";

    /**
     * The user's last name constant.
     */
    private final static String LAST_NAME = "lastName";

    /**
     * The user's middle name constant.
     */
    private final static String MIDDLE_NAME = "middleName";



    @Override
    public Command execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = Util.getUserFromSession(request);
        if (user == null) {
            return CommandType.LOGIN.getCurrentCommand();
        } else if (!user.getUsername().equals("admin")) {
            return CommandType.PROFILE.getCurrentCommand();
        }
        if (request.getMethod().equalsIgnoreCase("post")) {
            String username = user.getUsername();
            String currentPassword = user.getPassword();
            Cookie cookie = new Cookie(username, currentPassword);
            cookie.setMaxAge(60);
            response.addCookie(cookie);
            LOGGER.info("Cookie successfully added");

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            BookingService<User> userService = serviceFactory.getUserService();

            String username1 = request.getParameter(USERNAME);
            String email = request.getParameter(EMAIL);
            String password = request.getParameter(PASSWORD);
            String firstName = request.getParameter(FIRST_NAME);
            String lastName = request.getParameter(LAST_NAME);
            String middleName = request.getParameter(MIDDLE_NAME);


            if (request.getParameter("Update") != null) {


                //todo проверки


                User editUser = new User(username1, email, password, firstName, lastName, middleName, true);
                boolean isUpdate;
                try {
                    isUpdate = userService.update(editUser);
                } catch (ServiceException e) {
                    LOGGER.error("Updating user's failed");
                    throw new CommandException(e);
                }
                if (isUpdate) {
                    request.setAttribute("wordUser", MessageManager.getProperty("message.wordUser"));
                    request.setAttribute("currentUser", username1);
                    request.setAttribute("isUpdated", MessageManager.getProperty("message.isUpdated"));
                    LOGGER.info("User successfully updated");
                }
            } else if (request.getParameter("Delete") != null) {
                User editUser = new User(username1, email, password, firstName, lastName, middleName, false);
                boolean isDelete;
                try {
                    isDelete = userService.update(editUser);
                } catch (ServiceException e) {
                    LOGGER.error("Deleting user's failed");
                    throw new CommandException(e);
                }
                if (isDelete) {
                    request.setAttribute("wordUser", MessageManager.getProperty("message.wordUser"));
                    request.setAttribute("currentUser", username1);
                    request.setAttribute("isDisabled", MessageManager.getProperty("message.isDisabled"));
                    LOGGER.info("User successfully marked deleted");
                }
            }
        }

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookingService<User> userService = serviceFactory.getUserService();

        try {
            List<User> users = userService.read("WHERE enabled = true");
            request.getSession().setAttribute("size", users.size());
            String strStart = request.getParameter("start");
            int startReq = 0;
            if (strStart != null) {
                startReq = Integer.parseInt(strStart);
            }
            String where = String.format(" LIMIT %d, 5", startReq);
            users = userService.read("WHERE enabled = true" + where);
            request.getSession().setAttribute("users", users);
        } catch (ServiceException e) {
            LOGGER.error("Reading user's data failed");
            throw new CommandException(e);
        }

        return null;
    }
}
