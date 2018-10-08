package by.htp.kirova.task2.java.filter;

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
    private static final Logger LOGGER = Logger.getLogger(CacheControlFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("Caching filter has been initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate, max-age=0");
        httpServletResponse.setHeader("Pragma", "no-cache");

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        LOGGER.info("Caching filter has been destroyed");
    }



}


