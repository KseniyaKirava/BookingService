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

/**
 * Abstract class implementation for a
 * particular command type - Profile.
 *
 * @author Kseniya Kirava
 * @since Oct 14, 2018
 */
public class ProfileCommand extends Command {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger logger = Logger.getLogger(ProfileCommand.class);

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
     * The request method post constant.
     */
    private final static String POST = "post";

    /**
     * The name of button constant.
     */
    private final static String SAVE_BUTTON = "save";

    /**
     * The name of button constant.
     */
    private final static String LOGOUT_BUTTON = "logout";

    /**
     * The name of button constant.
     */
    private final static String DELETE_ACCOUNT_BUTTON = "deleteMyAccount";


    //todo разбить на методы

    @Override
    public Command execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = UserService.getUserFromSession(request);
        if (user == null) {
            return CommandType.LOGIN.getCurrentCommand();
        }

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            BookingService<User> userService = serviceFactory.getUserService();

            request.setAttribute(USERNAME, user.getUsername());
            request.setAttribute(EMAIL, user.getEmail());
            request.setAttribute(PASSWORD, "");
            request.setAttribute(FIRST_NAME, user.getFirstName());
            request.setAttribute(LAST_NAME, user.getLastName());
            request.setAttribute(MIDDLE_NAME, user.getMiddleName());

            if (request.getMethod().equalsIgnoreCase(POST)) {
                if (request.getParameter(SAVE_BUTTON) != null) {
                    String email = request.getParameter(EMAIL);
                    String password = request.getParameter(PASSWORD);
                    String firstName = request.getParameter(FIRST_NAME);
                    String lastName = request.getParameter(LAST_NAME);
                    String middleName = request.getParameter(MIDDLE_NAME);

                    Validator validator = Validator.getInstance();

                    if (!password.isEmpty()) {
                        if (!validator.checkPassword(password)) {
                            String messageIncorrectData =
                                    MessageManager.getMessageInSessionLanguage(request.getSession(), MessageConstant.MESSAGE_INCORRECT_DATA);
                            request.setAttribute(MessageConstant.ERROR_DATA, messageIncorrectData);
                            logger.debug("Data validation failed");
                            return null;
                        } else {
                            user.setPassword(UserService.getHashPassword(password));
                        }
                    }

                    user.setEmail(email);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setMiddleName(middleName);

                    boolean isUpdate;
                    try {
                        isUpdate = userService.update(user);

                    } catch (ServiceException e) {
                        throw new CommandException("Updating user ending with exception", e);
                    }

                    if (isUpdate) {
                        logger.debug("User successfully updated");
                    } else {
                        request.setAttribute(USERNAME, user.getUsername());
                        request.setAttribute(EMAIL, user.getEmail());
                        request.setAttribute(PASSWORD, "");
                        request.setAttribute(FIRST_NAME, user.getFirstName());
                        request.setAttribute(LAST_NAME, user.getLastName());
                        request.setAttribute(MIDDLE_NAME, user.getMiddleName());

                        String messageIncorrectData =
                                MessageManager.getMessageInSessionLanguage(request.getSession(), MessageConstant.MESSAGE_INCORRECT_DATA);
                        request.setAttribute(MessageConstant.ERROR_DATA, messageIncorrectData);
                        logger.debug("Data from form not saved");
                        return null;
                    }

                    return CommandType.PROFILE.getCurrentCommand();

                }
                if (request.getParameter(LOGOUT_BUTTON) != null) {
                    request.getSession().invalidate();
                    return CommandType.LOGIN.getCurrentCommand();
                }
                if (request.getParameter(DELETE_ACCOUNT_BUTTON) != null) {
                    user.setEnabled(false);

                    boolean isDelete;

                    try {
                        isDelete = userService.update(user);
                    } catch (ServiceException e) {
                        throw new CommandException("Deleting user failed", e);
                    }

                    if (isDelete) {
                        request.getSession().invalidate();
                        logger.debug("User successfully deleted");

                        return CommandType.LOGIN.getCurrentCommand();

                    } else {
                        logger.debug("User not deleted");
                    }

                }

            }

        return null;

    }


}



