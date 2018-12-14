package by.htp.kirova.task2.filter;

import org.apache.log4j.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;


/**
 * This filter sets encoding parameters for this application.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
@WebFilter(initParams = {@WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param")},
        urlPatterns = {"/*"})
public class EncodingFilter implements Filter {
    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger logger = Logger.getLogger(EncodingFilter.class);

    /**
     * Encoding parameter.
     */
    private String encoding;


    /**
     * The parameter name encoding constant.
     */
    private final static String ENCODING = "encoding";

    /**
     * The encoding constant.
     */
    private final static String UTF_8 = "utf-8";




    @Override
    public void init(FilterConfig filterConfig) {
        logger.debug("Encoding filter has been initialized");
        encoding = filterConfig.getInitParameter(ENCODING);
        if (encoding == null)
            encoding = UTF_8;
    }



    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        filterChain.doFilter(request, response);
    }



    @Override
    public void destroy() {
        logger.info("Encoding filter has been destroyed");
        encoding = null;
    }
}
