package by.htp.kirova.task2.java.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(initParams = {@WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param")},
        urlPatterns = {"/*"})

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
        if (encoding == null)
            encoding = "utf-8";
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        LOGGER.info("Encoding filter has been destroyed");
        encoding = null;
    }
}
