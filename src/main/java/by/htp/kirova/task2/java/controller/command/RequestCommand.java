package by.htp.kirova.task2.java.controller.command;


import by.htp.kirova.task2.java.entity.Request;
import by.htp.kirova.task2.java.entity.User;
import by.htp.kirova.task2.java.service.GenericService;
import by.htp.kirova.task2.java.service.ServiceException;
import by.htp.kirova.task2.java.service.ServiceFactory;
import by.htp.kirova.task2.java.util.Util;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Abstract class implementation for a
 * particular command type - Request.
 *
 * @author Kseniya Kirava
 * @since Oct 14, 2018
 */
public class RequestCommand extends Command {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(RequestCommand.class);

    @Override
    public Command execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = Util.getUserFromSession(request);

        if (user == null) {
            return CommandType.LOGIN.getCurrentCommand();
        } else {
            String username = user.getUsername();

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            GenericService<Request> requestService = serviceFactory.getRequestService();
            List<Request> requests;

            try {
                requests = requestService.read("WHERE users_username like '" + username + "' AND enabled = true");
                request.getSession().setAttribute("size", requests.size());
                String strStart = request.getParameter("start");
                int startReq = 0;
                if (strStart != null) {
                    startReq = Integer.parseInt(strStart);
                }
                String where = String.format(" LIMIT %d, 10", startReq);
                requests = requestService.read("WHERE users_username like '" + username + "' AND enabled = true " + where);
                request.getSession().setAttribute("requests", requests);
            } catch (ServiceException e) {
                LOGGER.error("Request read error");
                throw new CommandException(e);
            }

        }

        return null;

    }
}

