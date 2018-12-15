package by.htp.kirova.task2.controller.command;

import by.htp.kirova.task2.controller.MessageManager;
import by.htp.kirova.task2.entity.User;
import by.htp.kirova.task2.service.util.AuthorityService;
import by.htp.kirova.task2.service.util.UserService;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Abstract class implementation for a
 * particular command type - Login.
 *
 * @author Kseniya Kirava
 * @since Oct 14, 2018
 */
public class LoginCommand extends Command {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger logger = Logger.getLogger(LoginCommand.class);

    /**
     * The user for session attribute constant.
     */
    private final static String USER = "user";

    /**
     * The manager role for session attribute constant.
     */
    private final static String MANAGER = "manager";

    /**
     * The admin role for session attribute constant.
     */
    private final static String ADMIN = "admin";

    /**
     * The user's role for session attribute constant.
     */
    private final static String ROLE = "role";

    /**
     * The unique identification name constant.
     */
    private final static String USERNAME = "username";

    /**
     * The user's password constant.
     */
    private final static String PASSWORD = "password";

    /**
     * The request method 'post' constant.
     */
    private final static String POST = "post";

    /**
     * The name of button constant.
     */
    private final static String LOGIN_BUTTON = "login";

    //todo разбить на методы

    @Override
    public Command execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = UserService.getUserFromSession(request);

        if (user != null) {
            if (request.getSession().getAttribute(ROLE).equals(USER)) {
                return CommandType.PROFILE.getCurrentCommand();
            } else if (request.getSession().getAttribute(ROLE).equals(ADMIN)) {
                return CommandType.ADMIN.getCurrentCommand();
            }
//            else if (request.getSession().getAttribute(ROLE).equals(MANAGER)) {
//                return CommandType.MANAGER.getCurrentCommand();
//            }
        }
        if (request.getMethod().equalsIgnoreCase(POST)) {
            if (request.getParameter(LOGIN_BUTTON) != null) {
                String username = request.getParameter(USERNAME);
                String password = request.getParameter(PASSWORD);

                user = null;
                try {
                    user = UserService.checkLoginAndPassword(username, password);
                } catch (CommandException e) {
                    logger.error("Check login & password error", e);
                }

                if (user != null) {
                    request.getSession().setAttribute(USER, user);
                    request.getSession().setAttribute(USERNAME, username);
                    request.getSession().setMaxInactiveInterval(120);
                    logger.debug(String.format("Session for user '%s' successfully created", username));

                    String role = AuthorityService.getUserAuthority(username);

                    request.getSession().setAttribute(ROLE, role);

                    if (role.equals(ADMIN)) {
                        return CommandType.ADMIN.getCurrentCommand();
                    }
                    return CommandType.PROFILE.getCurrentCommand();
                } else {
                    String errorLoginMessage = MessageManager.getMessageInSessionLanguage(request.getSession(),MessageConstant.MESSAGE_LOGIN_ERROR);
                    request.setAttribute(MessageConstant.ERROR_LOGIN, errorLoginMessage);
                    return null;
                }
            }

        }
        return null;
    }
}
