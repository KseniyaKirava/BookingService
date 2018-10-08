package by.htp.kirova.task2.java.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

/**
 * This filter sets encoding parameters for this application.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */

public class EncodingFilter implements Filter {
    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(EncodingFilter.class);

    /**
     * Encoding parameter.
     */
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("Encoding filter has been initialized");
        encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String encoding = request.getCharacterEncoding();
        if (encoding == null || !encoding.equalsIgnoreCase(encoding))
            request.setCharacterEncoding(encoding);

        encoding = response.getCharacterEncoding();
        if (encoding == null || !encoding.equalsIgnoreCase(encoding))
            response.setCharacterEncoding(encoding);

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        LOGGER.info("Encoding filter has been destroyed");
        encoding = null;
    }
}
