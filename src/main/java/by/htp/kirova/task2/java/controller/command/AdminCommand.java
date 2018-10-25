package by.htp.kirova.task2.java.controller.command;

import by.htp.kirova.task2.java.entity.User;
import by.htp.kirova.task2.java.service.GenericService;
import by.htp.kirova.task2.java.service.ServiceException;
import by.htp.kirova.task2.java.service.ServiceFactory;
import by.htp.kirova.task2.java.util.Util;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    private final static String FIRST_NAME = "first_name";

    /**
     * The user's last name constant.
     */
    private final static String LAST_NAME = "last_name";

    /**
     * The user's middle name constant.
     */
    private final static String MIDDLE_NAME = "middle_name";



    @Override
    public Command execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = Util.getUserFromSession(request);
        if (user == null) {
            return CommandType.LOGIN.command;
        } else if (!user.getUsername().equals("admin")) {
            return CommandType.PROFILE.command;
        }
        if (request.getMethod().equalsIgnoreCase("post")) {
            String username = user.getUsername();
            String currentPassword = user.getPassword();
            Cookie cookie = new Cookie(username, currentPassword);
            cookie.setMaxAge(60);
            response.addCookie(cookie);
            LOGGER.info("Cookie successfully added");

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            GenericService<User> userService = serviceFactory.getUserService();

            String username1 = request.getParameter(USERNAME);
            String email = request.getParameter(EMAIL);
            String password = request.getParameter(PASSWORD);
            String first_name = request.getParameter(FIRST_NAME);
            String last_name = request.getParameter(LAST_NAME);
            String middle_name = request.getParameter(MIDDLE_NAME);


            if (request.getParameter("Update") != null) {
                User editUser = new User(username1, email, password, first_name, last_name, middle_name, true);
                boolean isUpdate;
                try {
                    isUpdate = userService.update(editUser);
                } catch (ServiceException e) {
                    LOGGER.error("Updating user's failed");
                    throw new CommandException(e);
                }
                if (isUpdate) {
                    LOGGER.info("User successfully updated");
                }
            } else if (request.getParameter("Delete") != null) {
                User editUser = new User(username1, email, password, first_name, last_name, middle_name, false);
                boolean isDelete;
                try {
                    isDelete = userService.update(editUser);
                } catch (ServiceException e) {
                    LOGGER.error("Deleting user's failed");
                    throw new CommandException(e);
                }
                if (isDelete) {
                    LOGGER.info("User successfully marked deleted");
                }
            }
        }

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        GenericService<User> userService = serviceFactory.getUserService();

        try {
            request.setAttribute("users", userService.read("WHERE enabled = true"));
        } catch (ServiceException e) {
            LOGGER.error("Reading user's data oe role failed");
            throw new CommandException(e);
        }

        return null;
    }
}
