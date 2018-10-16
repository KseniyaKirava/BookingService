package by.htp.kirova.task2.java.controller;


import by.htp.kirova.task2.java.controller.action.ActionFactory;
import by.htp.kirova.task2.java.controller.action.Command;
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
    private static final Logger LOGGER = Logger.getLogger(Controller.class);
    /**
     * The unique serial version identifier.
     */
    private static final long serialVersionUID = 1L;

    private ActionFactory actionFactory;

    @Override
    public void init() throws ServletException {
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
     * @param response Initial {@link javax.servlet.http.HttpServletResponse} object
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String viewPage;
        try {
            Command command = actionFactory.defineCommand(request);
            Command next = command.execute(request, response);
            if (next == null) {
                viewPage = command.getJsp();
                getServletContext().getRequestDispatcher(viewPage).forward(request, response);
            } else {
                response.sendRedirect("do?command=" + next.toString());
            }
        } catch (Exception e) {
            getServletContext().getRequestDispatcher(CommandType.ERROR.command.getJsp()).forward(request, response);

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







