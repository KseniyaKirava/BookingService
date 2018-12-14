package by.htp.kirova.task2.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * This filter used locale from session for this application.
 *
 * @author Kseniya Kirava
 * @since Oct 16, 2018
 */

@WebFilter(filterName = "SessionLocaleFilter", urlPatterns = {"/*"})
public class SessionLocaleFilter implements Filter {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger logger = Logger.getLogger(SessionLocaleFilter.class);

    /**
     * The session attribute language constant.
     */
    private final static String LANG = "lang";

    /**
     * The session attribute locale constant.
     */
    private final static String SESSION_LOCALE = "sessionLocale";




    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("Locale filter has been initialized");
    }




    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getParameter(SESSION_LOCALE) != null) {
            req.getSession().setAttribute(LANG, req.getParameter(SESSION_LOCALE));
        }
        filterChain.doFilter(request, response);
    }




    @Override
    public void destroy() {
        logger.debug("Caching filter has been destroyed");
    }


}