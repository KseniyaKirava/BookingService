package by.htp.kirova.task2.java.controller;


import by.htp.kirova.task2.java.controller.action.ActionCommand;
import by.htp.kirova.task2.java.controller.action.ActionFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

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
    private static final Logger LOGGER = Logger.getLogger(Controller.class);
    /**
     * The unique serial version identifier.
     */
    private static final long serialVersionUID = 1L;


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
     * @param response Initial {@link javax.servlet.http.HttpServletResponse} object
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String page = null;
        ActionFactory actionFactory = new ActionFactory();

        ActionCommand command = actionFactory.defineCommand(request);
        page = command.execute(request);

        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = ConfigurationManager.getParameter("path.page.index");
            request.getSession().setAttribute("nullPage", MessageManager.getParameter("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}

//        RequestContent requestContent = new RequestContent(request);
//        String commandName = requestContent.getRequestParameter(COMMAND);
//        LOGGER.info("Command name: " + commandName);
//        try {
//            Command command = CommandFactory.defineCommand(commandName);
//            CommandResult commandResult = command.execute(requestContent);
//            requestContent.insertAttributes(request);
//            requestContent.getCookies().forEach(response::addCookie);
//            if (FORWARD.equals(commandResult.getResponseType())) {
//                LOGGER.info("Forward to" + commandResult.getPage());
//                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(commandResult.getPage());
//                requestDispatcher.forward(request, response);
//            } else {
//                LOGGER.info("Redirect to" + commandResult.getPage());
//                response.sendRedirect(request.getContextPath() + commandResult.getPage());
//            }
//        } catch (CommandException e) {
//            throw new ServletException(e);
//        }







