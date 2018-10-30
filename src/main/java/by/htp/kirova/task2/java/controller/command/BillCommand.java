package by.htp.kirova.task2.java.controller.command;


import by.htp.kirova.task2.java.controller.MessageManager;
import by.htp.kirova.task2.java.entity.Reservation;
import by.htp.kirova.task2.java.entity.User;
import by.htp.kirova.task2.java.service.GenericService;
import by.htp.kirova.task2.java.service.ServiceException;
import by.htp.kirova.task2.java.service.ServiceFactory;
import by.htp.kirova.task2.java.util.Util;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Abstract class implementation for a
 * particular command type - Bill.
 *
 * @author Kseniya Kirava
 * @since Oct 14, 2018
 */
public class BillCommand extends Command {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(BillCommand.class);

    @Override
    public Command execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = Util.getUserFromSession(request);
        if (user == null) {
            return CommandType.LOGIN.getCurrentCommand();
        } else {

            Reservation reservation = (Reservation) request.getSession().getAttribute("reservation");
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            GenericService<Reservation> reservationService = serviceFactory.getReservationService();

            if (!request.getMethod().equalsIgnoreCase("post")) {
                String username = user.getUsername();
                String currentPassword = user.getPassword();
                Cookie cookie = new Cookie(username, currentPassword);
                cookie.setMaxAge(60);
                response.addCookie(cookie);
                LOGGER.info("Cookie successfully added");

            }
            if (request.getParameter("cancel") != null) {
                reservation.setEnabled(false);
                try {
                    reservationService.update(reservation);
                } catch (ServiceException e) {
                    LOGGER.info("Cancelling reservation failed", e);
                    throw new CommandException("Cancelling reservation failed", e);
                }
                request.getSession().setAttribute("messageReservation", MessageManager.getProperty("message.reservationCancelled"));
                return CommandType.RESERVE.getCurrentCommand();
            }
            if (request.getParameter("pay") != null) {
                double total_cost = reservation.getTotal_cost();
                request.getSession().setAttribute("total_cost", total_cost);
                return CommandType.PAYMENT.getCurrentCommand();
            }

            return null;
        }

    }
}
