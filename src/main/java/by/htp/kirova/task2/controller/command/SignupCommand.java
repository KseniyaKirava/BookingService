package by.htp.kirova.task2.controller.command;

import by.htp.kirova.task2.controller.MessageManager;
import by.htp.kirova.task2.entity.Authority;
import by.htp.kirova.task2.entity.User;
import by.htp.kirova.task2.service.util.UserService;
import by.htp.kirova.task2.service.ServiceException;
import by.htp.kirova.task2.service.ServiceFactory;
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
    public Command execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException {
        User user = UserService.getUserFromSession(request);
        if (user != null) {
            if (!request.getParameter("role").equals("user")) {
                return CommandType.PROFILE.getCurrentCommand();
            } else if (!request.getParameter("role").equals("admin")) {
                return CommandType.ADMIN.getCurrentCommand();
            }
//            else if (!request.getParameter("role").equals("manager")) {
//                return CommandType.MANAGER.getCurrentCommand();
//            }
        }


        if (request.getMethod().equalsIgnoreCase("post")) {
            String username = request.getParameter(USERNAME);
            String email = request.getParameter(EMAIL);
            String password = request.getParameter(PASSWORD);
            String firstName = request.getParameter(FIRST_NAME);
            String lastName = request.getParameter(LAST_NAME);
            String middleName = request.getParameter(MIDDLE_NAME);


            if (UserService.isUsernameUnique(username)) {
                boolean enabled = true;

                user = new User(username, email, password, firstName, lastName, middleName, enabled);

                ServiceFactory serviceFactory = ServiceFactory.getInstance();

                boolean isCreateUser;
                boolean isCreateAuthority = false;

                try {
                    isCreateUser = serviceFactory.getUserService().create(user);
                    if (isCreateUser) {
                        Authority authority = new Authority("user", username, enabled);
                        isCreateAuthority = serviceFactory.getAuthorityService().create(authority);
                    }
                } catch (ServiceException e) {
                    throw new CommandException("Creating user failed", e);
                }

                if (isCreateUser && isCreateAuthority) {
                    LOGGER.info("User and authority successfully created");
                    return CommandType.LOGIN.getCurrentCommand();
                } else {
                    request.setAttribute(USERNAME, "");
                    request.setAttribute(EMAIL, email);
                    request.setAttribute(PASSWORD, "");
                    request.setAttribute(FIRST_NAME, firstName);
                    request.setAttribute(LAST_NAME, lastName);
                    request.setAttribute(MIDDLE_NAME, middleName);
                    request.setAttribute("errorSignUpCommand", MessageManager.getProperty("message.incorrectData"));
                    return null;
                }

            }
            request.setAttribute(USERNAME, "");
            request.setAttribute(EMAIL, email);
            request.setAttribute(PASSWORD, "");
            request.setAttribute(FIRST_NAME, firstName);
            request.setAttribute(LAST_NAME, lastName);
            request.setAttribute(MIDDLE_NAME, middleName);
            request.setAttribute("errorUsernameDuplicate", MessageManager.getProperty("message.usernameDuplicate"));
        }

        return null;

    }
}
