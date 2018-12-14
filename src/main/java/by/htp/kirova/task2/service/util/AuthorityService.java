package by.htp.kirova.task2.service.util;

import by.htp.kirova.task2.controller.command.CommandException;
import by.htp.kirova.task2.entity.Authority;
import by.htp.kirova.task2.service.BookingService;
import by.htp.kirova.task2.service.ServiceException;
import by.htp.kirova.task2.service.ServiceFactory;
import java.util.List;


/**
 * Contains methods which provide several logic to work with authorities.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class AuthorityService {

//    public static void main(String[] args) {
//        String pass = getHashPassword("45d45d");
//        System.out.println(pass);
//
//    }

    /**
     * The user role for session attribute constant.
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
     * Constant string which represents query to find authority.
     */
    private static final String AUTHORITY_WHERE_QUERY = "WHERE username like '%s'";


    /**
     * Provides access to user roles (main role).
     *
     * @param username unique identification name.
     * @return role of user by priority.
     * @throws CommandException if authority reading end unsuccessful
     */
    public static String getUserAuthority(String username) throws CommandException {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookingService<Authority> authorityService = serviceFactory.getAuthorityService();

        String role = USER;
        String sql = String.format(AUTHORITY_WHERE_QUERY, username);

        try {
            List<Authority> authorities = authorityService.read(sql);
            for (Authority authority : authorities) {
                String currentAuthority = authority.getAuthority();
                if (currentAuthority.equals(MANAGER)) {
                    if (role.equals(USER)) {
                        role = currentAuthority;
                    }
                }
                if (currentAuthority.equals(ADMIN)) {
                    role = authority.getAuthority();
                }
            }
        } catch (ServiceException e) {
            throw new CommandException("Authorities reading error", e);
        }

        return role;

    }
}
