package by.htp.kirova.task2.controller.command;


import by.htp.kirova.task2.controller.MessageManager;
import by.htp.kirova.task2.entity.Reservation;
import by.htp.kirova.task2.entity.Room;
import by.htp.kirova.task2.entity.User;
import by.htp.kirova.task2.service.BookingService;
import by.htp.kirova.task2.service.ServiceException;
import by.htp.kirova.task2.service.ServiceFactory;
import by.htp.kirova.task2.service.util.DateService;
import by.htp.kirova.task2.service.util.ReservationService;
import by.htp.kirova.task2.service.util.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Abstract class implementation for a
 * particular command type - Profile.
 *
 * @author Kseniya Kirava
 * @since Oct 14, 2018
 */
public class SearchCommand extends Command {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger logger = Logger.getLogger(SearchCommand.class);

    /**
     * The room capacity (person).
     */
    private static final String ROOM_CAPACITY_TEMP = "roomCapacityTemp";

    /**
     * Check in Date.
     */
    private static final String CHECKIN_DATE_TEMP = "checkinDateTemp";

    /**
     * Check out Date.
     */
    private static final String CHECKOUT_DATE_TEMP = "checkoutDateTemp";

    /**
     * Class of te room.
     */
    private static final String ROOM_CLASS_ID_TEMP = "roomClassIdTemp";


    /**
     * The SQL 'where' query for view constant.
     */
    private static final String ROOMS_WHERE_QUERY = "WHERE rooms.capacity >= %d AND " +
            "rooms.room_classes_id = %d AND rooms.enabled = true " +
            "AND rooms.id NOT IN (SELECT res.rooms_id FROM reservations as res WHERE " +
            "(%d >= res.checkin_date AND res.checkout_date >= %d) AND res.enabled = true) ORDER BY rooms.cost";


    /**
     * The SQL 'limit' query for view constant.
     */
    private static final String ROOMS_LIMIT_QUERY = " LIMIT %d, %d";

    /**
     * The SQL 'where' query for rating constant.
     */
    private static final String RATING_WHERE_QUERY = " WHERE rooms_id = %d";

    @Override
    public Command execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = UserService.getUserFromSession(request);
        String username = null;
        if (user != null) {
            username = (String) request.getSession().getAttribute("username");
        }


        long roomClassId = (Long) request.getSession().getAttribute(ROOM_CLASS_ID_TEMP);
        int roomCapacity = (int) request.getSession().getAttribute(ROOM_CAPACITY_TEMP);
        long checkinDate = (long) request.getSession().getAttribute(CHECKIN_DATE_TEMP);
        long checkoutDate = (long) request.getSession().getAttribute(CHECKOUT_DATE_TEMP);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookingService<Room> roomService = serviceFactory.getRoomService();
        BookingService<Reservation> reservationService = serviceFactory.getReservationService();

        try {

            String sql = String.format(ROOMS_WHERE_QUERY, roomCapacity, roomClassId, checkoutDate, checkinDate);
            List<Room> rooms = roomService.read(sql);

            request.setAttribute("size", rooms.size());
            String strStart = request.getParameter("start");

            int startReq = 0;
            if (strStart != null) {
                startReq = Integer.parseInt(strStart);
            }

            int rowPerPage = 5;
            request.setAttribute("rowPerPage", rowPerPage);

            String limit = String.format(ROOMS_LIMIT_QUERY, startReq, rowPerPage);
            String limitedRoomsListQuery = sql + limit;

            rooms = roomService.read(limitedRoomsListQuery);

            for (Room room : rooms) {
                String sqlForRating = String.format(RATING_WHERE_QUERY, room.getId());
                List<Reservation> reservations = reservationService.read(sqlForRating);
                int count = 0;
                int sum = 0;

                for (Reservation res: reservations) {
                    if (res.getAssessment() != 0) {
                        count++;
                        sum += res.getAssessment();
                    }
                }

                if (count != 0 && sum != 0) {
                    double avg = sum / (count * 1.0) + sum % count;
                    room.setAverageAssessment(avg);

                    count = 0;
                    sum = 0;
                }

            }


            if (rooms.isEmpty()) {
                request.setAttribute("messageReservationNotFound", MessageManager.getProperty("message.reservationNotFound"));
                return null;
            }

            request.setAttribute("rooms", rooms);

        } catch (ServiceException e) {
            throw new CommandException("Reading user's data failed", e);
        }

        if (request.getMethod().equalsIgnoreCase("post")) {

            if (request.getParameter("booking") != null) {

                if (username == null) {
                    return CommandType.LOGIN.getCurrentCommand();
                }

                long reservationDate = DateService.getCurrentDateInMiliseconds();
                double cost = Double.valueOf(request.getParameter("cost"));
                double totalCost = ReservationService.getTotalCost(checkinDate, checkoutDate, cost);
                long roomId = Long.parseLong(request.getParameter("id"));
                byte assessment = 0;
                boolean enabled = true;

                Reservation reservation = new Reservation(0, reservationDate, checkinDate, checkoutDate, totalCost,
                        enabled, username, roomId, assessment);

                boolean isCreated = false;
                try {
                    isCreated = reservationService.create(reservation);
                } catch (ServiceException e) {
                    throw new CommandException("Creating reservation failed", e);
                }

                if (!isCreated) {
                    request.setAttribute("messageReservationNotFound", MessageManager.getProperty("message.reservationNotFound"));
                    return null;
                }

                request.getSession().setAttribute("totalCost", totalCost);
                return CommandType.PAYMENT.getCurrentCommand();
            }

        }
        return null;
    }

}






