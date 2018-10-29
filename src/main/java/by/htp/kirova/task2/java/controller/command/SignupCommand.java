package by.htp.kirova.task2.java.controller.command;

import by.htp.kirova.task2.java.controller.MessageManager;
import by.htp.kirova.task2.java.entity.Authority;
import by.htp.kirova.task2.java.entity.User;
import by.htp.kirova.task2.java.logic.UserLogic;
import by.htp.kirova.task2.java.service.ServiceException;
import by.htp.kirova.task2.java.service.ServiceFactory;
import by.htp.kirova.task2.java.service.validation.Validator;
import by.htp.kirova.task2.java.util.Util;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Abstract class implementation for a
 * particular command type - SignUp.
 *
 * @author Kseniya Kirava
 * @since Oct 14, 2018
 */
public class SignupCommand extends Command {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(SignupCommand.class);

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
    public Command execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException {
        User user = Util.getUserFromSession(request);
        if (user != null) {
            if (user.getUsername().equals("admin")) {
                return CommandType.ADMIN.getCurrentCommand();
            }
            return CommandType.PROFILE.getCurrentCommand();
        }


        if (request.getMethod().equalsIgnoreCase("post")) {
            String username = request.getParameter(USERNAME);
            String email = request.getParameter(EMAIL);
            String password = request.getParameter(PASSWORD);
            String first_name = request.getParameter(FIRST_NAME);
            String last_name = request.getParameter(LAST_NAME);
            String middle_name = request.getParameter(MIDDLE_NAME);

            if (!Validator.checkUsername(username) || !Validator.checkEmail(email) ||
                    !Validator.checkPassword(password) || !Validator.checkPassword(password) ||
                    !Validator.checkName(first_name) || !Validator.checkName(last_name) ||
                    !Validator.checkMiddleName(middle_name)) {
                request.getSession().setAttribute("username", "");
                request.getSession().setAttribute("email", email);
                request.getSession().setAttribute("password", "");
                request.getSession().setAttribute("first_name", first_name);
                request.getSession().setAttribute("last_name", last_name);
                request.getSession().setAttribute("middle_name", middle_name);
                request.getSession().setAttribute("errorSignUpCommand", MessageManager.getProperty("message.incorrectData"));
                return null;
            } else {
                if (UserLogic.isUsernameUnique(username)) {
                    String hashPassword = UserLogic.getHashPassword(password);
                    user = new User(username, email, hashPassword, first_name, last_name, middle_name, true);
                    Authority authority = new Authority("user", username, true);
                    ServiceFactory serviceFactory = ServiceFactory.getInstance();
                    boolean isCreateUser = false;
                    boolean isCreateAuthority = false;
                    try {
                        isCreateUser = serviceFactory.getUserService().create(user);
                        isCreateAuthority = serviceFactory.getAuthorityService().create(authority);
                    } catch (ServiceException e) {
                        LOGGER.info("Creating user failed", e);
                        throw new CommandException("Creating user failed", e);
                    }
                    if (isCreateUser && isCreateAuthority) {
                        LOGGER.info("User and authority successfully created");
                        return CommandType.LOGIN.getCurrentCommand();
                    }

                }
                request.getSession().setAttribute("username", "");
                request.getSession().setAttribute("email", email);
                request.getSession().setAttribute("password", "");
                request.getSession().setAttribute("first_name", first_name);
                request.getSession().setAttribute("last_name", last_name);
                request.getSession().setAttribute("middle_name", middle_name);
                request.getSession().setAttribute("errorUsernameDuplicate", MessageManager.getProperty("message.usernameDuplicate"));
            }

        }

        return null;

    }
}
