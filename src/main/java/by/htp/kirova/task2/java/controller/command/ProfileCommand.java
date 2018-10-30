package by.htp.kirova.task2.java.controller.command;


import by.htp.kirova.task2.java.entity.User;
import by.htp.kirova.task2.java.logic.UserLogic;
import by.htp.kirova.task2.java.service.GenericService;
import by.htp.kirova.task2.java.service.ServiceException;
import by.htp.kirova.task2.java.service.ServiceFactory;
import by.htp.kirova.task2.java.service.validation.Validator;
import by.htp.kirova.task2.java.util.Util;
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
    private static final Logger LOGGER = Logger.getLogger(ProfileCommand.class);

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
            return CommandType.LOGIN.getCurrentCommand();
        } else {

            String currentPassword = user.getPassword();

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            GenericService<User> userService = serviceFactory.getUserService();


            request.setAttribute(USERNAME, user.getUsername());
            request.setAttribute(EMAIL, user.getEmail());
            request.setAttribute(PASSWORD, user.getPassword());
            request.setAttribute(FIRST_NAME, user.getFirst_name());
            request.setAttribute(LAST_NAME, user.getLast_name());
            request.setAttribute(MIDDLE_NAME, user.getMiddle_name());

            if (request.getMethod().equalsIgnoreCase("post")) {
                if (request.getParameter("saveinfo") != null) {
                    String email = request.getParameter(EMAIL);
                    String password = request.getParameter(PASSWORD);
                    String first_name = request.getParameter(FIRST_NAME);
                    String last_name = request.getParameter(LAST_NAME);
                    String middle_name = request.getParameter(MIDDLE_NAME);
                    boolean passwordIsUpdated = !password.equals(currentPassword);
                    if (passwordIsUpdated && !Validator.checkPassword(password)) {
                        return null;
                    }
                    if (!Validator.checkEmail(email) || !Validator.checkName(first_name) ||
                            !Validator.checkName(last_name) || !Validator.checkMiddleName(middle_name)) {
                        return null;
                    }
                    if (passwordIsUpdated) {
                        user.setPassword(UserLogic.getHashPassword(password));
                    }
                    user.setEmail(email);
                    user.setFirst_name(first_name);
                    user.setLast_name(last_name);
                    user.setMiddle_name(middle_name);

                    boolean isUpdate;
                    try {
                        isUpdate = userService.update(user);
                    } catch (ServiceException e) {
                        LOGGER.info("Updating user failed", e);
                        throw new CommandException("Updating user failed", e);
                    }
                    if (isUpdate) {
                        LOGGER.info("Data from form successfully saved");
                    }
                    return CommandType.PROFILE.getCurrentCommand();
                }
                if (request.getParameter("logout") != null) {
                    request.getSession().invalidate();
                    return CommandType.LOGIN.getCurrentCommand();
                }
                if (request.getParameter("deletemyaccount") != null) {
                        user.setEnabled(false);
                        try {
                            userService.update(user);
                        } catch (ServiceException e) {
                            LOGGER.info("Deleting user failed", e);
                            throw new CommandException("Deleting user failed", e);
                        }
                        request.getSession().invalidate();
                        LOGGER.info("User successfully deleted");
                        return CommandType.LOGIN.getCurrentCommand();

                }

            }

        }
        return null;

    }


}



