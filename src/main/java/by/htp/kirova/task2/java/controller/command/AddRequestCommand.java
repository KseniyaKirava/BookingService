package by.htp.kirova.task2.java.controller.command;


import by.htp.kirova.task2.java.entity.Request;
import by.htp.kirova.task2.java.entity.User;
import by.htp.kirova.task2.java.service.GenericService;
import by.htp.kirova.task2.java.service.ServiceException;
import by.htp.kirova.task2.java.service.ServiceFactory;
import by.htp.kirova.task2.java.service.validation.Validator;
import by.htp.kirova.task2.java.util.DateConverter;
import by.htp.kirova.task2.java.util.Util;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;

/**
 * Abstract class implementation for a
 * particular command type - Profile.
 *
 * @author Kseniya Kirava
 * @since Oct 14, 2018
 */
public class AddRequestCommand extends Command {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(AddRequestCommand.class);

    /**
     * The room capacity (person).
     */
    private static final String ROOM_CAPACITY = "room_capacity";

    /**
     * Check in Date.
     */
    private static final String CHECKIN_DATE = "checkin_date";

    /**
     * Check out Date.
     */
    private static final String CHECKOUT_DATE = "checkout_date";

    /**
     * Class of te room.
     */
    private static final String ROOM_CLASS = "room_class";

    @Override
    public Command execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = Util.getUserFromSession(request);
        if (user == null) {
            return CommandType.LOGIN.getCurrentCommand();
        } else {

            String username = user.getUsername();
            String currentPassword = user.getPassword();
            Cookie cookie = new Cookie(username, currentPassword);
            cookie.setMaxAge(60);
            response.addCookie(cookie);
            LOGGER.info("Cookie successfully added");

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            GenericService<Request> requestService = serviceFactory.getRequestService();

            if (request.getMethod().equalsIgnoreCase("post")) {
                if (request.getParameter("saveinfo") != null) {
                    int room_capacity = Integer.parseInt(request.getParameter(ROOM_CAPACITY));
                    String checkin_date = request.getParameter(CHECKIN_DATE);
                    String checkout_date = request.getParameter(CHECKOUT_DATE);
                    String room_class = request.getParameter(ROOM_CLASS);

                    if (!Validator.checkCapacity(room_capacity) || !Validator.checkDate(checkin_date) ||
                            !Validator.checkDate(checkout_date)) {
                        return null;
                    }

                    long checkin = 0;
                    long checkout = 0;
                    try {
                        checkin = DateConverter.convertDateToMiliseconds(checkin_date);
                        checkout = DateConverter.convertDateToMiliseconds(checkout_date);
                    } catch (ParseException e) {
                        LOGGER.error("Date parse with error");
                    }

                    Request requestEntity = new Request(0, room_capacity, checkin, checkout, room_class,
                            true, user.getUsername());
                    boolean isCreate;
                    try {
                        isCreate = requestService.create(requestEntity);
                    } catch (ServiceException e) {
                        LOGGER.error("Creating requests is failed", e);
                        throw new CommandException("Creating requests is failed", e);
                    }
                    if (isCreate) {
                        long id = requestEntity.getId();
                        request.getSession().setAttribute("requestId", id);

                        return CommandType.RESERVATION.getCurrentCommand();
                    }
                }

            }
            return null;

        }
    }
}



