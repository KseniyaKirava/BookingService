package by.htp.kirova.task2.filter;

import by.htp.kirova.task2.controller.command.CommandType;
import org.apache.log4j.Logger;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class SecurityFilter implements Filter {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger logger = Logger.getLogger(SecurityFilter.class);

    /**
     * The user's role for session attribute constant.
     */
    private final static String ROLE = "role";

    /**
     * The user for session attribute constant.
     */
    private final static String USER = "user";

    /**
     * The admin role for session attribute constant.
     */
    private final static String ADMIN = "admin";

    /**
     * The manager role for session attribute constant.
     */
    private final static String MANAGER= "manager";

    /**
     * The user for session attribute constant.
     */
    private final static String GUEST = "guest";

    /**
     * The command constant.
     */
    private final static String COMMAND = "command";

    /**
     * HashMap for commands.
     */
    private HashMap<String, List<String>> avaliableCommands = new HashMap<>();


    @Override
    public void init(FilterConfig filterConfig) {

        avaliableCommands.put(CommandType.PROFILE.getCommandName(), Arrays.asList(USER, MANAGER, ADMIN));
        avaliableCommands.put(CommandType.RESERVE.getCommandName(), Arrays.asList(USER, MANAGER, ADMIN));
        avaliableCommands.put(CommandType.PAYMENT.getCommandName(), Arrays.asList(USER, MANAGER, ADMIN));
        avaliableCommands.put(CommandType.MANAGER.getCommandName(), Arrays.asList(MANAGER));
        avaliableCommands.put(CommandType.ADMIN.getCommandName(), Arrays.asList(ADMIN));
        avaliableCommands.put(CommandType.LOGIN.getCommandName(), Arrays.asList(GUEST));
        avaliableCommands.put(CommandType.SIGNUP.getCommandName(), Arrays.asList(GUEST));
        avaliableCommands.put(CommandType.MAIN.getCommandName(), Arrays.asList(GUEST, USER, MANAGER, ADMIN));
        avaliableCommands.put(CommandType.COMINGSOON.getCommandName(), Arrays.asList(GUEST, USER, MANAGER, ADMIN));
        avaliableCommands.put(CommandType.ERROR.getCommandName(), Arrays.asList(GUEST, USER, MANAGER, ADMIN));
        avaliableCommands.put(CommandType.SEARCH.getCommandName(), Arrays.asList(GUEST, USER, MANAGER, ADMIN));

        logger.debug("Security filter has been initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String role = (String) (httpServletRequest.getSession().getAttribute(ROLE));

        if (role == null)
            role = GUEST;
        else
            role = role.toLowerCase();

        String cmd = httpServletRequest.getParameter(COMMAND);
        if (cmd == null) {
            filterChain.doFilter(request, response);
        } else {
            cmd = cmd.toLowerCase();
            List<String> rolesFilter = avaliableCommands.getOrDefault(cmd, null);
            if (rolesFilter == null) {
                throw new UnsupportedOperationException("unknown command");
            } else {
                if (rolesFilter.contains(role)) {
                    filterChain.doFilter(request, response);
                } else {
                    ((HttpServletResponse) response).sendRedirect("do?command=Main");
                }
            }
        }
    }

    @Override
    public void destroy() {
        logger.debug("Security filter has been destroyed");
    }
}
