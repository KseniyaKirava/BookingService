package by.htp.kirova.task2.controller.command;

import by.htp.kirova.task2.controller.MessageManager;
import by.htp.kirova.task2.entity.ReserveForManager;
import by.htp.kirova.task2.service.BookingService;
import by.htp.kirova.task2.service.ServiceException;
import by.htp.kirova.task2.service.ServiceFactory;
import by.htp.kirova.task2.service.util.DateService;
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
     * The array of reservations attribute constant.
     */
    private final static String RESERVATIONS = "reservations";

    /**
     * The SQL 'limit' query for view constant.
     */
    private static final String RESERVATIONS_FOR_MANAGER_LIMIT_QUERY = " LIMIT %d, %d";

    /**
     *  The SQL 'where' query constant.
     */
    private static final String RESERVATIONS_FOR_MANAGER_WHERE_QUERY = "%d ORDER BY rooms.number";


    @Override
    public Command execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        Long currrentDate = DateService.getCurrentDateInMiliseconds();

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookingService<ReserveForManager> reserveForManagerService = serviceFactory.getReserveForManagerService();

        String where = String.format(RESERVATIONS_FOR_MANAGER_WHERE_QUERY, currrentDate);

        List<ReserveForManager> reservations;

        try {

            reservations = reserveForManagerService.read(where);

            request.setAttribute(SIZE, reservations.size());
            String strStart = request.getParameter(START);

            int startReq = 0;
            if (strStart != null) {
                startReq = Integer.parseInt(strStart);
            }

            int rowPerPage = 8;
            request.setAttribute(ROW_PER_PAGE, rowPerPage);

            String limit = String.format(RESERVATIONS_FOR_MANAGER_LIMIT_QUERY, startReq, rowPerPage);
            String limitedReservationListQuery = where + limit;

            reservations = reserveForManagerService.read(limitedReservationListQuery);

            if (reservations.isEmpty()) {
                String reservationNotFound =
                        MessageManager.getMessageInSessionLanguage(request.getSession(), MessageConstant.MESSAGE_RESERVATIONS_NOT_FOUND);
                request.setAttribute(MessageConstant.RESERVATIONS_NOT_FOUND, reservationNotFound);
                logger.debug("Reservations list is empty");
            } else {
                request.setAttribute(RESERVATIONS, reservations);
            }

        } catch (ServiceException e) {
            throw new CommandException("Reservations read error", e);
        }

        return null;

    }
}
