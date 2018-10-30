package by.htp.kirova.task2.java.controller.command;


import by.htp.kirova.task2.java.controller.MessageManager;
import by.htp.kirova.task2.java.entity.Request;
import by.htp.kirova.task2.java.entity.Reservation;
import by.htp.kirova.task2.java.entity.User;
import by.htp.kirova.task2.java.service.GenericService;
import by.htp.kirova.task2.java.service.ServiceException;
import by.htp.kirova.task2.java.service.ServiceFactory;
import by.htp.kirova.task2.java.service.validation.Validator;
import by.htp.kirova.task2.java.util.DateConverter;
import by.htp.kirova.task2.java.util.Util;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

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

            request.getSession().setAttribute("messageReservationNotFound", request.getSession().getAttribute("messageReservationNotFound"));

            String username = user.getUsername();

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            GenericService<Request> requestService = serviceFactory.getRequestService();
            GenericService<Reservation> reservationService = serviceFactory.getReservationService();


            if (request.getMethod().equalsIgnoreCase("post")) {
                if (request.getParameter("saveinfo") != null) {
                    int room_capacity = Integer.parseInt(request.getParameter(ROOM_CAPACITY));
                    String checkinDate = request.getParameter(CHECKIN_DATE);
                    String checkoutDate = request.getParameter(CHECKOUT_DATE);
                    String roomClass = request.getParameter(ROOM_CLASS);

                    if (!Validator.checkCapacity(room_capacity) || !Validator.checkDate(checkinDate) ||
                            !Validator.checkDate(checkoutDate)) {
                        request.getSession().setAttribute("incorrectData", MessageManager.getProperty("message.incorrectData"));
                        return null;
                    }

                    long checkin = 0;
                    long checkout = 0;
                    try {
                        checkin = DateConverter.convertDateToMiliseconds(checkinDate);
                        checkout = DateConverter.convertDateToMiliseconds(checkoutDate);
                    } catch (ParseException e) {
                        LOGGER.error("Date parse with error");
                    }

                    Date currentDate = new Date();
                    int currentDays = currentDate.getDay();
                    int checkInDays = (int) (checkin / (1000 * 24 * 60 * 60));
                    if ((checkInDays - currentDays >= 0) && (checkout - checkin > 0)) {
                        Request requestEntity = new Request(0, room_capacity, checkin, checkout, roomClass,
                                true, user.getUsername());
                        boolean isCreate;
                        try {
                            isCreate = requestService.create(requestEntity);
                        } catch (ServiceException e) {
                            LOGGER.error("Creating requests is failed", e);
                            throw new CommandException("Creating requests is failed", e);
                        }
                        if (isCreate) {
                            long requests_Id = requestEntity.getId();
                            List rooms;
                            try {
                                rooms = serviceFactory.getHelperService().showAvialiableRooms(requests_Id);
                            } catch (ServiceException e) {
                                LOGGER.error("Parsing requestId from quert error", e);
                                throw new CommandException("Parsing requestId from quert error", e);
                            }
                            if (!rooms.isEmpty()) {
                                long reservation_date = DateConverter.getCurrentDateInMiliseconds();
                                long checkin_date;
                                long checkout_date;
                                double cost;
                                int capacity;
                                int rooms_id;
                                int room_class;
                                String room_name;
                                String room_number;
                                try {
                                    checkin_date = DateConverter.convertDateToMiliseconds((String) rooms.get(1));
                                    checkout_date = DateConverter.convertDateToMiliseconds((String) rooms.get(2));
                                    cost = Double.valueOf((String) rooms.get(7));
                                    capacity = Integer.parseInt((String) rooms.get(0));
                                    rooms_id = Integer.parseInt((String) rooms.get(4));
                                    room_class = Integer.parseInt((String) rooms.get(8));
                                    room_name = (String) rooms.get(5);
                                    room_number = (String) rooms.get(6);
                                } catch (ParseException e) {
                                    LOGGER.error("Parsing data error", e);
                                    throw new CommandException("Parsing data error", e);
                                }
                                double total_cost = Util.getTotalCost(checkin_date, checkout_date, cost);
                                Reservation reservation = new Reservation(0, reservation_date, checkin_date, checkout_date,
                                        total_cost, true, requests_Id, username, rooms_id, room_class);

                                boolean isCreated = false;
                                try {
                                    isCreated = reservationService.create(reservation);
                                } catch (ServiceException e) {
                                    LOGGER.info("Creating user failed", e);
                                    throw new CommandException("Creating user failed", e);
                                }

                                if (isCreated) {
                                    LOGGER.info("Reservation successfully created");

                                    request.getSession().setAttribute("room_name", room_name);
                                    request.getSession().setAttribute("room_number", room_number);
                                    request.getSession().setAttribute("capacity", capacity);
                                    request.getSession().setAttribute("check_in", checkin_date);
                                    request.getSession().setAttribute("check_out", checkout_date);
                                    request.getSession().setAttribute("total_cost", total_cost);
                                    request.getSession().setAttribute("reservation", reservation);

                                    return CommandType.BILL.getCurrentCommand();
                                }
                            }

                            request.getSession().setAttribute("messageReservationNotFound", MessageManager.getProperty("message.reservationNotFound"));
                        }
                    }
                }

            }
            return null;

        }
    }
}



