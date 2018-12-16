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
public class ManagerCommand extends Command {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger logger = Logger.getLogger(ManagerCommand.class);

    /**
     * The reservation unique identification number constant.
     */
    private final static String RESERVATION_ID = "reservationId";

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
     * The SQL 'where' query constant.
     */
    private static final String RESERVATIONS_BY_USER_WHERE_QUERY = "'%s' ORDER BY res.reservation_date DESC";

    /**
     * The SQL 'limit' query for view constant.
     */
    private static final String RESERVATIONS_LIMIT_QUERY = " LIMIT %d, %d";

    /**
     * The SQL reservation query constant.
     */
    private static final String RESERVATIONS_WHERE_QUERY = "SELECT res.id, users.first_name, users.last_name, res.checkin_date, res.checkout_date, res.total_cost, rooms.name, \n" +
            "rooms.number, rooms.capacity, rc.name FROM reservations as res JOIN room_classes as rc JOIN rooms JOIN users \n" +
            "WHERE users.username like res.users_username AND rooms.id=res.rooms_id AND rc.id=rooms.room_classes_id AND\n" +
            "checkin_date = 1552338000000\n";

//todo разбить на методы

    @Override
    public Command execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
//        User user = UserService.getUserFromSession(request);
//        if (user == null) {
//            return CommandType.LOGIN.getCurrentCommand();
//        } else if (request.getSession().getAttribute("role").equals("admin")) {
//            return CommandType.ADMIN.getCurrentCommand();
//        }
////        else if (request.getSession().getAttribute("role").equals("manager")) {
////            return CommandType.MANAGER.getCurrentCommand();
////        }
//
//        String username = user.getUsername();
//
//        ServiceFactory serviceFactory = ServiceFactory.getInstance();
//        BookingService<ReserveByUser> reserveByUserService = serviceFactory.getReserveByUserService();
//
//        String where = String.format(RESERVATIONS_BY_USER_WHERE_QUERY, username);
//
//        List<ReserveByUser> reservations;
//
//        try {
//
//            reservations = reserveByUserService.read(where);
//
//            request.setAttribute(SIZE, reservations.size());
//            String strStart = request.getParameter(START);
//
//            int startReq = 0;
//            if (strStart != null) {
//                startReq = Integer.parseInt(strStart);
//            }
//
//            int rowPerPage = 10;
//            request.setAttribute(ROW_PER_PAGE, rowPerPage);
//
//            String limit = String.format(RESERVATIONS_BY_USER_LIMIT_QUERY, startReq, rowPerPage);
//            String limitedReservationListQuery = where + limit;
//
//            reservations = reserveByUserService.read(limitedReservationListQuery);
//
//            request.setAttribute(RESERVATIONS, reservations);
//
//        } catch (ServiceException e) {
//            throw new CommandException("Reservations read error", e);
//        }
//
//        if (request.getMethod().equalsIgnoreCase(POST)) {
//
//            String reservationId = request.getParameter(RESERVATION_ID);
//            String currentAssessment = request.getParameter(ASSESSMENT);
//            String checkoutDate = request.getParameter(CHECKOUT_DATE);
//
//            boolean dateBeforeCurrentDate = DateService.isDateBeforeCurrentDate(Long.parseLong(checkoutDate));
//
//            byte assessment;
//
//            if (currentAssessment != null && currentAssessment.length() == 1) {
//                assessment = Byte.parseByte(currentAssessment);
//            } else {
//                String messageIncorrectData =
//                        MessageManager.getMessageInSessionLanguage(request.getSession(), MessageConstant.MESSAGE_INCORRECT_DATA);
//                request.setAttribute(MessageConstant.ERROR_DATA, messageIncorrectData);
//                logger.debug("Data validation failed");
//                return null;
//            }
//
//            BookingService<Reservation> reservationService = serviceFactory.getReservationService();
//
//            Reservation reservation = null;
//
//            String currentReservationQuery = String.format(CURRENT_RESERVATIONS_QUERY, reservationId);
//            try {
//                List<Reservation> reservationsById = reservationService.read(currentReservationQuery);
//                if (!reservationsById.isEmpty()) {
//                    reservation = reservationsById.get(0);
//                }
//            } catch (ServiceException e) {
//                throw new CommandException("Reading reservation data failed", e);
//            }
//
//            if (request.getParameter(RATE_BUTTON) != null) {
//                if (!dateBeforeCurrentDate) {
//                    String tooEarlyForAssessment =
//                            MessageManager.getMessageInSessionLanguage(request.getSession(), MessageConstant.MESSAGE_TOO_EARLY);
//                    request.setAttribute(MessageConstant.TOO_EARLY, tooEarlyForAssessment);
//                    logger.debug("Date is incorrect");
//                    return null;
//                }
//
//                if (assessment != 0 && reservation != null) {
//                    try {
//                        reservation.setAssessment(assessment);
//                        reservationService.update(reservation);
//                    } catch (ServiceException e) {
//                        throw new CommandException("Updating reservation's data failed", e);
//                    }
//                } else {
//                    return null;
//                }
//
//                return CommandType.RESERVE.getCurrentCommand();
//            }
//
//        }

        return null;

    }
}
