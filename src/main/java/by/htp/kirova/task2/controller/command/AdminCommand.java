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
    private static final Logger logger = Logger.getLogger(AdminCommand.class);

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

    /**
     * The SQL 'where' query constant.
     */
    private static final String USERS_WHERE_QUERY = "WHERE enabled = true";

    /**
     * The SQL 'limit' query for view constant.
     */
    private static final String USERS_LIMIT_QUERY = " LIMIT %d, %d";

//todo разбить на методы

    @Override
    public Command execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = UserService.getUserFromSession(request);
        if (user == null) {
            return CommandType.LOGIN.getCurrentCommand();
        } else if (request.getSession().getAttribute("role").equals("user")) {
            return CommandType.PROFILE.getCurrentCommand();
        }
//        else if (request.getSession().getAttribute("role").equals("manager")) {
//            return CommandType.MANAGER.getCurrentCommand();
//        }

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookingService<User> userService = serviceFactory.getUserService();


        try {
            List<User> users = userService.read(USERS_WHERE_QUERY);

            request.setAttribute("size", users.size());
            String strStart = request.getParameter("start");

            int startReq = 0;
            if (strStart != null) {
                startReq = Integer.parseInt(strStart);
            }

            int rowPerPage = 10;
            request.setAttribute("rowPerPage", rowPerPage);

            String limit = String.format(USERS_LIMIT_QUERY, startReq, rowPerPage);
            String limitedUsersListQuery = USERS_WHERE_QUERY + limit;

            users = userService.read(limitedUsersListQuery);

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
                        String messageIncorrectData = MessageManager.getMessageInSessionLanguage(request.getSession(), "message.incorrectData");
                        request.setAttribute("errorData", messageIncorrectData);
                        logger.debug("Data validation failed");
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

                if (!isUpdate) {
                    String messageIncorrectData = MessageManager.getMessageInSessionLanguage(request.getSession(), "message.incorrectData");
                    request.setAttribute("errorData", messageIncorrectData);
                    logger.debug("Data from form not saved");
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
                    logger.debug("User successfully marked deleted");
                } else {
                    logger.debug("User not deleted");
                    return null;
                }

                return CommandType.ADMIN.getCurrentCommand();

            }
        }

        return null;

    }
}
