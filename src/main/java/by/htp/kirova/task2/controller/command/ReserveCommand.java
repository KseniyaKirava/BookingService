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
 * Implementation for a
 * particular command type - Reserve.
 *
 * @author Kseniya Kirava
 * @since Oct 20, 2018
 */
public class ReserveCommand extends Command {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger logger = Logger.getLogger(ReserveCommand.class);

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
     * The size of array attribute constant for paginator.
     */
    private final static String SIZE = "size";

    /**
     * The start position attribute constant for paginator.
     */
    private final static String START = "start";

    /**
     * The count of rows per page attribute constant for paginator.
     */
    private final static String ROW_PER_PAGE = "rowPerPage";

    /**
     * The request method post constant.
     */
    private final static String POST = "post";

    /**
     * The array of reservations attribute constant.
     */
    private final static String RESERVATIONS = "reservations";

    /**
     * The name of button constant.
     */
    private final static String RATE_BUTTON = "rate";

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
        if (user != null) {
            String username = user.getUsername();

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            BookingService<ReserveByUser> reserveByUserService = serviceFactory.getReserveByUserService();

            String where = String.format(RESERVATIONS_BY_USER_WHERE_QUERY, username);

            List<ReserveByUser> reservations;

            try {

                reservations = reserveByUserService.read(where);

                request.setAttribute(SIZE, reservations.size());
                String strStart = request.getParameter(START);

                int startReq = 0;
                if (strStart != null) {
                    startReq = Integer.parseInt(strStart);
                }

                int rowPerPage = 10;
                request.setAttribute(ROW_PER_PAGE, rowPerPage);

                String limit = String.format(RESERVATIONS_BY_USER_LIMIT_QUERY, startReq, rowPerPage);
                String limitedReservationListQuery = where + limit;

                reservations = reserveByUserService.read(limitedReservationListQuery);

                if (reservations.isEmpty()) {
                    String reservationNotFound =
                            MessageManager.getMessageInSessionLanguage(request.getSession(), MessageConstant.MESSAGE_RESERVATIONS_NOT_FOUND);
                    request.setAttribute(MessageConstant.RESERVATIONS_NOT_FOUND, reservationNotFound);
                } else {
                    request.setAttribute(RESERVATIONS, reservations);
                }

            } catch (ServiceException e) {
                throw new CommandException("Reservations read error", e);
            }

            if (request.getMethod().equalsIgnoreCase(POST)) {

                String reservationId = request.getParameter(RESERVATION_ID);
                String currentAssessment = request.getParameter(ASSESSMENT);
                String checkoutDate = request.getParameter(CHECKOUT_DATE);

                boolean dateBeforeCurrentDate;

                dateBeforeCurrentDate = DateService.isDateBeforeCurrentDate(Long.parseLong(checkoutDate));


                byte assessment;

                if (currentAssessment != null && currentAssessment.length() == 1) {
                    assessment = Byte.parseByte(currentAssessment);
                } else {
                    String messageIncorrectData =
                            MessageManager.getMessageInSessionLanguage(request.getSession(), MessageConstant.MESSAGE_INCORRECT_DATA);
                    request.setAttribute(MessageConstant.ERROR_DATA, messageIncorrectData);
                    logger.debug("Data validation failed");
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

                if (request.getParameter(RATE_BUTTON) != null) {
                    if (!dateBeforeCurrentDate) {
                        String tooEarlyForAssessment =
                                MessageManager.getMessageInSessionLanguage(request.getSession(), MessageConstant.MESSAGE_TOO_EARLY);
                        request.setAttribute(MessageConstant.TOO_EARLY, tooEarlyForAssessment);
                        logger.debug("Date is incorrect");
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
        }

        return null;

    }
}
