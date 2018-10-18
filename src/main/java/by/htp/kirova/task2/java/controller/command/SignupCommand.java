package by.htp.kirova.task2.java.controller.command;

import by.htp.kirova.task2.java.entity.Authority;
import by.htp.kirova.task2.java.entity.User;
import by.htp.kirova.task2.java.logic.UserLogic;
import by.htp.kirova.task2.java.service.ServiceException;
import by.htp.kirova.task2.java.service.ServiceFactory;
import by.htp.kirova.task2.java.service.validation.Validator;
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
    public Command execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        if (req.getMethod().equalsIgnoreCase("post")) {
            String username = req.getParameter(USERNAME);
            String email = req.getParameter(EMAIL);
            String password = req.getParameter(PASSWORD);
            String first_name = req.getParameter(FIRST_NAME);
            String last_name = req.getParameter(LAST_NAME);
            String middle_name = req.getParameter(MIDDLE_NAME);

            if (!Validator.checkUsername(username) || !Validator.checkEmail(email) ||
                    !Validator.checkPassword(password) || !Validator.checkPassword(password) ||
                    !Validator.checkName(first_name) || !Validator.checkName(last_name) ||
                    !Validator.checkMiddleName(middle_name)) {
                return CommandType.SIGNUP.command;
            } else {
                String hashPassword = UserLogic.getHashPassword(password);
                User user = new User(username, email, hashPassword, first_name, last_name, middle_name, true);
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
                    return CommandType.LOGIN.command;
                }
            }

        }
        return null;
    }
}
