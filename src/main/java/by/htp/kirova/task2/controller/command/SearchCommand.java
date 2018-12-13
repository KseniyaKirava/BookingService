package by.htp.kirova.task2.controller.command;


import by.htp.kirova.task2.entity.Reservation;
import by.htp.kirova.task2.entity.User;
import by.htp.kirova.task2.service.BookingService;
import by.htp.kirova.task2.service.ServiceFactory;
import by.htp.kirova.task2.service.util.UserService;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Abstract class implementation for a
 * particular command type - Bill.
 *
 * @author Kseniya Kirava
 * @since Oct 14, 2018
 */
public class SearchCommand extends Command {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(SearchCommand.class);

    @Override
    public Command execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = UserService.getUserFromSession(request);
        if (user == null) {
            return CommandType.LOGIN.getCurrentCommand();
        } else {

            Reservation reservation = (Reservation) request.getSession().getAttribute("reservation");
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            BookingService<Reservation> reservationService = serviceFactory.getReservationService();


            if (request.getParameter("pay") != null) {
                double totalCost = reservation.getTotalCost();
                request.getSession().setAttribute("totalCost", totalCost);
                return CommandType.PAYMENT.getCurrentCommand();
            }

            return null;
        }

    }
}
