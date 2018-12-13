package by.htp.kirova.task2.controller.command;

import by.htp.kirova.task2.controller.MessageManager;
import by.htp.kirova.task2.entity.Reservation;
import by.htp.kirova.task2.entity.ReserveByUser;
import by.htp.kirova.task2.entity.User;
import by.htp.kirova.task2.service.BookingService;
import by.htp.kirova.task2.service.ServiceException;
import by.htp.kirova.task2.service.ServiceFactory;
import by.htp.kirova.task2.service.util.DateService;
import by.htp.kirova.task2.service.util.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Abstract class implementation for a
 * particular command type - ReserveByUser.
 *
 * @author Kseniya Kirava
 * @since Oct 20, 2018
 */
public class ReserveCommand extends Command {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(ReserveCommand.class);

    /**
     * The reservation unique identification number constant.
     */
    private final static String RESERVATION_ID = "reservationId";

    /**
     * The room assessment constant.
     */
    private final static String ASSESSMENT = "assessment";


    /**
     * The checkout date constant.
     */
    private final static String CHECKOUT_DATE = "checkoutDate";

    /**
     * The SQL 'where' query constant.
     */
    private static final String RESERVATIONS_BY_USER_WHERE_QUERY = "'%s' ORDER BY res.reservation_date DESC";

    /**
     * The SQL 'limit' query for view constant.
     */
    private static final String RESERVATIONS_BY_USER_LIMIT_QUERY = " LIMIT %d, %d";

    /**
     * The SQL current reservation query constant.
     */
    private static final String CURRENT_RESERVATIONS_QUERY = "WHERE id='%s'";


    @Override
    public Command execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = UserService.getUserFromSession(request);
        if (user == null) {
            return CommandType.LOGIN.getCurrentCommand();
        }

        String username = user.getUsername();

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookingService<ReserveByUser> reserveByUserService = serviceFactory.getReserveByUserService();

        String where = String.format(RESERVATIONS_BY_USER_WHERE_QUERY, username);

        List<ReserveByUser> reservations;

        try {

            reservations = reserveByUserService.read(where);

            request.setAttribute("size", reservations.size());
            String strStart = request.getParameter("start");

            int startReq = 0;
            if (strStart != null) {
                startReq = Integer.parseInt(strStart);
            }

            int rowPerPage = 10;
            request.setAttribute("rowPerPage", rowPerPage);

            String limit = String.format(RESERVATIONS_BY_USER_LIMIT_QUERY, startReq, rowPerPage);
            String limitedReservationListQuery = where + limit;

            reservations = reserveByUserService.read(limitedReservationListQuery);

            request.setAttribute("reservations", reservations);

        } catch (ServiceException e) {
            throw new CommandException("Reservations read error", e);
        }

        if (request.getMethod().equalsIgnoreCase("post")) {

            String reservationId = request.getParameter(RESERVATION_ID);
            String currentAssessment = request.getParameter(ASSESSMENT);
            String checkoutDate = request.getParameter(CHECKOUT_DATE);

            boolean dateBeforeCurrentDate = DateService.isDateBeforeCurrentDate(Long.parseLong(checkoutDate));

            byte assessment;

            if (currentAssessment != null && currentAssessment.length() == 1) {
                assessment= Byte.parseByte(currentAssessment);
            } else {
                request.setAttribute("errorData", MessageManager.getProperty("message.incorrectData"));
                return null;
            }

            BookingService<Reservation> reservationService = serviceFactory.getReservationService();

            Reservation reservation = null;

            String currentReservationQuery = String.format(CURRENT_RESERVATIONS_QUERY, reservationId);
            try {
                List<Reservation> reservationsById = reservationService.read(currentReservationQuery);
                if (!reservationsById.isEmpty()) {
                    reservation = reservationsById.get(0);
                }
            } catch (ServiceException e) {
                throw new CommandException("Reading reservation data failed", e);
            }

            if (request.getParameter("rate") != null) {
                if (!dateBeforeCurrentDate) {
                    request.setAttribute("tooEarly", MessageManager.getProperty("message.tooEarlyForAssessment"));
                    return null;
                }

                if (assessment != 0 && reservation != null) {
                    try {
                        reservation.setAssessment(assessment);
                        reservationService.update(reservation);
                    } catch (ServiceException e) {
                        throw new CommandException("Updating reservation's data failed", e);
                    }
                } else {
                    return null;
                }

                return CommandType.RESERVE.getCurrentCommand();
            }

        }

        return null;

    }
}
