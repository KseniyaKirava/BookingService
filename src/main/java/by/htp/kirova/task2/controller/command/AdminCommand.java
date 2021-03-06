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

/**
 * Implementation for a
 * particular command type - Admin.
 *
 * @author Kseniya Kirava
 * @since Oct 20, 2018
 */
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
     * The size of array attribute constant for paginator.
     */
    private final static String SIZE = "size";

    /**
     * The start position attribute constant for paginator.
     */
    private final static String START = "start";

    /**
     * The count of rows per page attribute constant for paginator.
     */
    private final static String ROW_PER_PAGE = "rowPerPage";

    /**
     * The array of users attribute constant.
     */
    private final static String USERS = "users";

    /**
     * The request method 'post' constant.
     */
    private final static String POST = "post";

    /**
     * The name of button constant.
     */
    private final static String UPDATE_BUTTON = "update";

    /**
     * The name of button constant.
     */
    private final static String DELETE_BUTTON = "delete";

    /**
     * The SQL 'where' query constant.
     */
    private static final String USERS_WHERE_QUERY = "WHERE enabled = true";

    /**
     * The SQL 'limit' query for view constant.
     */
    private static final String USERS_LIMIT_QUERY = " LIMIT %d, %d";

    @Override
    public Command execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookingService<User> userService = serviceFactory.getUserService();

        try {
            List<User> users = userService.read(USERS_WHERE_QUERY);

            request.setAttribute(SIZE, users.size());
            String strStart = request.getParameter(START);

            int startReq = 0;
            if (strStart != null) {
                startReq = Integer.parseInt(strStart);
            }

            int rowPerPage = 10;
            request.setAttribute(ROW_PER_PAGE, rowPerPage);

            String limit = String.format(USERS_LIMIT_QUERY, startReq, rowPerPage);
            String limitedUsersListQuery = USERS_WHERE_QUERY + limit;

            users = userService.read(limitedUsersListQuery);

            for (User currentUserInArray : users) {
                currentUserInArray.setPassword("");
            }

            request.setAttribute(USERS, users);
        } catch (ServiceException e) {
            throw new CommandException("Reading users data failed", e);
        }

        if (request.getMethod().equalsIgnoreCase(POST)) {

            String username = request.getParameter(USERNAME);
            String email = request.getParameter(EMAIL);
            String password = request.getParameter(PASSWORD);
            String firstName = request.getParameter(FIRST_NAME);
            String lastName = request.getParameter(LAST_NAME);
            String middleName = request.getParameter(MIDDLE_NAME);

            User currentUser = UserService.getUserByUsername(username);

            if (request.getParameter(UPDATE_BUTTON) != null) {

                Validator validator = Validator.getInstance();

                if (password.isEmpty()) {
                    String currentPassword = currentUser.getPassword();
                    currentUser.setPassword(currentPassword);
                } else {
                    if (!validator.checkPassword(password)) {
                        String messageIncorrectData =
                                MessageManager.getMessageInSessionLanguage(request.getSession(), MessageConstant.MESSAGE_INCORRECT_DATA);
                        request.setAttribute(MessageConstant.ERROR_DATA, messageIncorrectData);
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
                    String messageIncorrectData =
                            MessageManager.getMessageInSessionLanguage(request.getSession(), MessageConstant.MESSAGE_INCORRECT_DATA);
                    request.setAttribute(MessageConstant.ERROR_DATA, messageIncorrectData);
                    logger.debug("Data from form not saved");
                    return null;
                }

                return CommandType.ADMIN.getCurrentCommand();
            }

            if (request.getParameter(DELETE_BUTTON) != null) {

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
                    logger.debug(String.format("User '%s' successfully marked deleted", currentUser.getUsername()));
                } else {
                    logger.debug(String.format("User '%s' not deleted", currentUser.getUsername()));
                    return null;
                }

                return CommandType.ADMIN.getCurrentCommand();

            }
        }

        return null;

    }
}
