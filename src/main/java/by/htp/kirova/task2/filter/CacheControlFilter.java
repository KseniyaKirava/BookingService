package by.htp.kirova.task2.filter;

import org.apache.log4j.Logger;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This filter sets encoding parameters for this application.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */

public class CacheControlFilter implements Filter {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger logger = Logger.getLogger(CacheControlFilter.class);


    /**
     * The header name constant.
     */
    private final static String CACHE_CONTROL = "Cache-Control";

    /**
     * The header name constant.
     */
    private final static String PRAGMA = "Pragma";

    /**
     * The header value.
     */
    private final static String NO_CACHE_PARAMETER = "no-cache";

    /**
     * The header values.
     */
    private final static String PARAMETERS = "no-cache, no-store, must-revalidate, max-age=0";



    @Override
    public void init(FilterConfig filterConfig) {
        logger.debug("Caching filter has been initialized");
    }



    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader(CACHE_CONTROL, PARAMETERS);
        httpServletResponse.setHeader(PRAGMA, NO_CACHE_PARAMETER);

        filterChain.doFilter(request, response);
    }



    @Override
    public void destroy() {
        logger.debug("Caching filter has been destroyed");
    }


}


