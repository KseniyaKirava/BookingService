package by.htp.kirova.task2.java.controller.command;

import by.htp.kirova.task2.java.controller.MessageManager;
import by.htp.kirova.task2.java.entity.User;
import by.htp.kirova.task2.java.logic.UserLogic;
import by.htp.kirova.task2.java.service.validation.Validator;
import by.htp.kirova.task2.java.util.Util;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    public Command execute(HttpServletRequest request, HttpServletResponse response) {
        User user = Util.getUserFromSession(request);
        if (user != null) {
            if (user.getUsername().equals("admin")) {
                return CommandType.ADMIN.getCurrentCommand();
            }
            return CommandType.PROFILE.getCurrentCommand();
        }
        if (request.getMethod().equalsIgnoreCase("post")) {
            if (request.getParameter("loginbutton") != null) {
                String username = request.getParameter(USERNAME);
                String password = request.getParameter(PASSWORD);
                if (!Validator.checkUsername(username) || !Validator.checkPassword(password)) {
                    return null;
                }
                LOGGER.info("Validation by username & password passed");

                user = null;
                try {
                    user = UserLogic.checkLogin(username, password);
                } catch (CommandException e) {
                    LOGGER.error("Check login error", e);
                }

                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    session.setMaxInactiveInterval(60);
                    LOGGER.info("Session for user " + username + " successfully created");
                    if (username.equals("admin")) {
                        return CommandType.ADMIN.getCurrentCommand();
                    }
                    return CommandType.PROFILE.getCurrentCommand();
                } else {
                    request.getSession().setAttribute("errorLoginCommand", MessageManager.getProperty("message.loginerror"));
                    return CommandType.LOGIN.getCurrentCommand();
                }
            }

        }
        return null;
    }
}
