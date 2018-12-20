package by.htp.kirova.task2.controller;


import by.htp.kirova.task2.controller.command.ActionFactory;
import by.htp.kirova.task2.controller.command.Command;
import by.htp.kirova.task2.controller.command.CommandType;
import by.htp.kirova.task2.dao.ConnectionPool;
import by.htp.kirova.task2.dao.connectionpool.ConnectionPoolImpl;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main servlet of web application.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
@WebServlet("/do")
public class Controller extends HttpServlet {
    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger logger = Logger.getLogger(Controller.class);

    /**
     * The unique serial version identifier.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Object of action factory class.
     */
    private ActionFactory actionFactory;

    @Override
    public void init() {
        actionFactory = new ActionFactory();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    /**
     * Main method which is executed implicitly in
     * {@link #doPost(HttpServletRequest, HttpServletResponse)} and
     * {@link #doGet(HttpServletRequest, HttpServletResponse)} methods.
     * Processes {@link javax.servlet.http.HttpServletRequest} and
     * creates {@link javax.servlet.http.HttpServletResponse}.
     *
     * @param request  Initial {@link javax.servlet.http.HttpServletRequest} object.
     * @param response Initial {@link javax.servlet.http.HttpServletResponse} object.
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String viewPage;
        try {
            Command command = actionFactory.defineCommand(request);
            Command next = command.execute(request, response);
            if (next == null) {
                viewPage = command.getJsp();
                logger.debug("Forward to " + viewPage);
                getServletContext().getRequestDispatcher(viewPage).forward(request, response);
            } else {
                logger.debug("Send redirect to " + next.toString() + " page");
                response.sendRedirect("do?command=" + next.toString());
            }
        } catch (Exception e) {
            logger.error("Process request failed", e);
            getServletContext().getRequestDispatcher(CommandType.ERROR.getCurrentCommand().getJsp()).forward(request, response);

        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}








