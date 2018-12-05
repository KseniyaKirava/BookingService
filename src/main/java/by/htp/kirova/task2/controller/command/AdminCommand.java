package by.htp.kirova.task2.controller.command;

import by.htp.kirova.task2.controller.MessageManager;
import by.htp.kirova.task2.entity.User;
import by.htp.kirova.task2.service.BookingService;
import by.htp.kirova.task2.service.ServiceException;
import by.htp.kirova.task2.service.ServiceFactory;
import by.htp.kirova.task2.service.util.UserService;
import by.htp.kirova.task2.service.validation.Validator;
import org.apache.log4j.Logger;

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
        User user = UserService.getUserFromSession(request);
        if (user == null) {
            return CommandType.LOGIN.getCurrentCommand();
        } else if (!user.getUsername().equals("admin")) {
            return CommandType.PROFILE.getCurrentCommand();
        }

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookingService<User> userService = serviceFactory.getUserService();

        try {
            List<User> users = userService.read("WHERE enabled = true");
            request.setAttribute("size", users.size());
            String strStart = request.getParameter("start");
            int startReq = 0;
            if (strStart != null) {
                startReq = Integer.parseInt(strStart);
            }
            String where = String.format(" LIMIT %d, 10", startReq);
            users = userService.read("WHERE enabled = true" + where);

            for (User currentUserInArray : users) {
                currentUserInArray.setPassword("");
            }

            request.setAttribute("users", users);
        } catch (ServiceException e) {
            throw new CommandException("Reading user's data failed", e);
        }

        if (request.getMethod().equalsIgnoreCase("post")) {

            String username = request.getParameter(USERNAME);
            String email = request.getParameter(EMAIL);
            String password = request.getParameter(PASSWORD);
            String firstName = request.getParameter(FIRST_NAME);
            String lastName = request.getParameter(LAST_NAME);
            String middleName = request.getParameter(MIDDLE_NAME);

            User currentUser = UserService.getUserByUsername(username);

            if (request.getParameter("Update") != null) {

                Validator validator = Validator.getInstance();

                if (password.isEmpty()) {
                    String currentPassword = currentUser.getPassword();
                    currentUser.setPassword(currentPassword);
                } else {
                    if (!validator.checkPassword(password)) {
                        request.setAttribute("errorData", MessageManager.getProperty("message.incorrectData"));
                        return null;
                    } else {
                        currentUser.setPassword(UserService.getHashPassword(password));
                    }
                }

                currentUser.setEmail(email);
                currentUser.setFirstName(firstName);
                currentUser.setLastName(lastName);
                currentUser.setMiddleName(middleName);

                boolean isUpdate;

                try {
                    isUpdate = userService.update(currentUser);

                } catch (ServiceException e) {
                    throw new CommandException("Updating current user ending with exception", e);
                }

                if (isUpdate) {
                    request.setAttribute("wordUser", MessageManager.getProperty("message.wordUser"));
                    request.setAttribute("currentUser", username);
                    request.setAttribute("isUpdated", MessageManager.getProperty("message.isUpdated"));
                    LOGGER.info("User successfully updated");
                } else {
                    LOGGER.info("Data from form not saved");
                    request.setAttribute("errorData", MessageManager.getProperty("message.incorrectData"));
                    return null;
                }

                return CommandType.ADMIN.getCurrentCommand();
            }

            if (request.getParameter("Delete") != null) {

                currentUser.setEnabled(false);

                boolean isDelete;

                try {
                    isDelete = userService.update(currentUser);
                } catch (ServiceException e) {
                    throw new CommandException("Deleting user's failed", e);
                }
                if (isDelete) {
                    if (currentUser.getUsername().equals(request.getSession().getAttribute(USERNAME))) {
                        request.getSession().invalidate();
                        return CommandType.LOGIN.getCurrentCommand();
                    }
                    request.setAttribute("wordUser", MessageManager.getProperty("message.wordUser"));
                    request.setAttribute("currentUser", username);
                    request.setAttribute("isDisabled", MessageManager.getProperty("message.isDisabled"));
                    LOGGER.info("User successfully marked deleted");
                } else {
                    LOGGER.info("User not deleted");
                    return null;
                }

                return CommandType.ADMIN.getCurrentCommand();

            }
        }

        return null;

    }
}
