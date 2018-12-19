package by.htp.kirova.task2.controller.command;

import by.htp.kirova.task2.controller.MessageManager;
import by.htp.kirova.task2.entity.Authority;
import by.htp.kirova.task2.entity.User;
import by.htp.kirova.task2.service.ServiceException;
import by.htp.kirova.task2.service.ServiceFactory;
import by.htp.kirova.task2.service.util.UserService;
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
    private static final Logger logger = Logger.getLogger(SignupCommand.class);

    /**
     * The user for session attribute constant.
     */
    private final static String USER = "user";

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
     * The empty String constant.
     */
    private final static String EMPTY = "";



    //todo разбить на методы

    @Override
    public Command execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException {
//        User user = UserService.getUserFromSession(request);
//        if (user != null) {
//            if (request.getSession().getAttribute("role").equals("user")) {
//                return CommandType.PROFILE.getCurrentCommand();
//            } else if (request.getSession().getAttribute("role").equals("admin")) {
//                return CommandType.ADMIN.getCurrentCommand();
//            }
////            else if (request.getSession().getAttribute("role").equals("manager")) {
////                return CommandType.MANAGER.getCurrentCommand();
////            }
//        }


        if (request.getMethod().equalsIgnoreCase(POST)) {
            String username = request.getParameter(USERNAME);
            String email = request.getParameter(EMAIL);
            String password = request.getParameter(PASSWORD);
            String firstName = request.getParameter(FIRST_NAME);
            String lastName = request.getParameter(LAST_NAME);
            String middleName = request.getParameter(MIDDLE_NAME);


            if (UserService.isUsernameUnique(username)) {
                boolean enabled = true;

                User user = new User(username, email, password, firstName, lastName, middleName, enabled);

                ServiceFactory serviceFactory = ServiceFactory.getInstance();

                boolean isCreateUser;
                boolean isCreateAuthority = false;

                try {
                    isCreateUser = serviceFactory.getUserService().create(user);
                    if (isCreateUser) {
                        Authority authority = new Authority(USER, username, enabled);
                        isCreateAuthority = serviceFactory.getAuthorityService().create(authority);
                    }
                } catch (ServiceException e) {
                    throw new CommandException("Creating user failed", e);
                }

                if (isCreateUser && isCreateAuthority) {
                    logger.debug("User and authority successfully created");
                    return CommandType.LOGIN.getCurrentCommand();
                } else {
                    request.setAttribute(USERNAME, EMPTY);
                    request.setAttribute(EMAIL, email);
                    request.setAttribute(PASSWORD, EMPTY);
                    request.setAttribute(FIRST_NAME, firstName);
                    request.setAttribute(LAST_NAME, lastName);
                    request.setAttribute(MIDDLE_NAME, middleName);

                    String messageIncorrectData =
                            MessageManager.getMessageInSessionLanguage(request.getSession(), MessageConstant.MESSAGE_INCORRECT_DATA);
                    request.setAttribute(MessageConstant.ERROR_SIGNUP, messageIncorrectData);
                    return null;
                }

            }
            request.setAttribute(USERNAME, EMPTY);
            request.setAttribute(EMAIL, email);
            request.setAttribute(PASSWORD, EMPTY);
            request.setAttribute(FIRST_NAME, firstName);
            request.setAttribute(LAST_NAME, lastName);
            request.setAttribute(MIDDLE_NAME, middleName);

            String usernameDuplicate =
                    MessageManager.getMessageInSessionLanguage(request.getSession(), MessageConstant.MESSAGE_USERNAME_DUPLICATE);
            request.setAttribute(MessageConstant.USERNAME_DUPLICATE, usernameDuplicate);
        }

        return null;

    }
}
