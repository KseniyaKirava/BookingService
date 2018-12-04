package by.htp.kirova.task2.controller.command;

import by.htp.kirova.task2.entity.Authority;
import by.htp.kirova.task2.entity.User;
import by.htp.kirova.task2.controller.MessageManager;
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
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

    /**
     * The unique identification name constant.
     */
    private final static String USERNAME = "username";

    /**
     * The user's password constant.
     */
    private final static String PASSWORD = "password";

    @Override
    public Command execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = UserService.getUserFromSession(request);
        String userRole= (String) request.getAttribute("role");
        if (user != null) {
            if (userRole.equals("admin")) {
                return CommandType.ADMIN.getCurrentCommand();
            }
            return CommandType.MAIN.getCurrentCommand();
        }
        if (request.getMethod().equalsIgnoreCase("post")) {
            if (request.getParameter("loginbutton") != null) {
                String username = request.getParameter(USERNAME);
                String password = request.getParameter(PASSWORD);

                Validator validator = Validator.getInstance();
                if (!validator.checkUsername(username) || !validator.checkPassword(password)) {
                    LOGGER.info("Validation by username & password failed");
                    return null;
                }
                LOGGER.info("Validation by username & password passed");

                user = null;
                try {
                    user = UserService.checkLogin(username, password);
                } catch (CommandException e) {
                    LOGGER.error("Check login & password error", e);
                }

                if (user != null) {
                    request.getSession().setAttribute("user", user);
                    request.getSession().setAttribute("username", user.getUsername());
                    request.getSession().setMaxInactiveInterval(120);
                    LOGGER.info("Session for user " + username + " successfully created");

                    ServiceFactory serviceFactory = ServiceFactory.getInstance();
                    BookingService<Authority> authorityService = serviceFactory.getAuthorityService();

                    String role = "user";

                    try {
                        List<Authority> authorities = authorityService.read("WHERE username like '" + username + "'");
                        for (Authority authority: authorities) {
                            String currentAuthority = authority.getAuthority();
                            if (currentAuthority.equals("manager")) {
                                if (role.equals("user")){
                                    role = currentAuthority;
                                }
                            }
                            if (currentAuthority.equals("admin")) {
                                role = authority.getAuthority();
                            }
                        }
                    } catch (ServiceException e) {
                        throw new CommandException("Authorities reading error", e);
                    }

                    request.getSession().setAttribute("role", role);

                    if (role.equals("admin")) {
                        return CommandType.ADMIN.getCurrentCommand();
                    }
                    return CommandType.PROFILE.getCurrentCommand();
                } else {
                    request.setAttribute("errorLoginCommand", MessageManager.getProperty("message.loginError"));
                    return null;
                }
            }

        }
        return null;
    }
}
