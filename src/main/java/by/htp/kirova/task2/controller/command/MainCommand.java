package by.htp.kirova.task2.controller.command;


import by.htp.kirova.task2.entity.User;
import by.htp.kirova.task2.service.util.Util;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Abstract class implementation for a
 * particular command type - Profile.
 *
 * @author Kseniya Kirava
 * @since Oct 14, 2018
 */
public class MainCommand extends Command {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger logger = Logger.getLogger(MainCommand.class);

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

            request.getSession().setAttribute("messageReservationNotFound", request.getSession().getAttribute("messageReservationNotFound"));
//
//            String username = user.getUsername();
//            Validator validator = Validator.getInstance();
//            ServiceFactory serviceFactory = ServiceFactory.getInstance();
//            BookingService<Request> requestService = serviceFactory.getRequestService();
//            BookingService<Reservation> reservationService = serviceFactory.getReservationService();
//
//
//            if (request.getMethod().equalsIgnoreCase("post")) {
//                if (request.getParameter("saveinfo") != null) {
//                    int room_capacity = Integer.parseInt(request.getParameter(ROOM_CAPACITY));
//                    String checkinDate = request.getParameter(CHECKIN_DATE);
//                    String checkoutDate = request.getParameter(CHECKOUT_DATE);
//                    String roomClass = request.getParameter(ROOM_CLASS);
//
//
//                    if (!validator.checkCapacity(room_capacity) || !validator.checkDate(checkinDate) ||
//                            !validator.checkDate(checkoutDate)) {
//                        request.getSession().setAttribute("incorrectData", MessageManager.getProperty("message.incorrectData"));
//                        return null;
//                    }
//
//                    long checkin = 0;
//                    long checkout = 0;
//                    try {
//                        checkin = DateConverter.convertDateToMiliseconds(checkinDate);
//                        checkout = DateConverter.convertDateToMiliseconds(checkoutDate);
//                    } catch (ParseException e) {
//                        logger.error("Date parse with error");
//                    }
//
//                    Date currentDate = new Date();
//                    int currentDays = currentDate.getDay();
//                    int checkInDays = (int) (checkin / (1000 * 24 * 60 * 60));
//                    if ((checkInDays - currentDays >= 0) && (checkout - checkin > 0)) {
//                        Request requestEntity = new Request(0, room_capacity, checkin, checkout, roomClass,
//                                true, user.getUsername());
//                        boolean isCreate;
//                        try {
//                            isCreate = requestService.create(requestEntity);
//                        } catch (ServiceException e) {
//                            throw new CommandException("Creating requests is failed", e);
//                        }
//                        if (isCreate) {
//                            long requests_Id = requestEntity.getId();
//                            List rooms;
//                            try {
//                                rooms = serviceFactory.getHelperService().avialiableRooms(requests_Id);
//                            } catch (ServiceException e) {
//                                throw new CommandException("Operation to get accessible rooms failed", e);
//                            }
//                            if (!rooms.isEmpty()) {
//                                long reservation_date = DateConverter.getCurrentDateInMiliseconds();
//                                long checkin_date = 0;
//                                long checkout_date = 0;
//                                double cost = 0.0;
//                                int capacity = 0;
//                                int rooms_id = 0;
//                                int room_class = 0;
//                                String room_name = null;
//                                String room_number = null;
//                                try {
//                                    checkin_date = DateConverter.convertDateToMiliseconds((String) rooms.get(1));
//                                    checkout_date = DateConverter.convertDateToMiliseconds((String) rooms.get(2));
//                                    cost = Double.valueOf((String) rooms.get(7));
//                                    capacity = Integer.parseInt((String) rooms.get(0));
//                                    rooms_id = Integer.parseInt((String) rooms.get(4));
//                                    room_class = Integer.parseInt((String) rooms.get(8));
//                                    room_name = (String) rooms.get(5);
//                                    room_number = (String) rooms.get(6);
//                                } catch (ParseException e) {
//                                    logger.error("Вata parsing ended with an error");
//                                }
//                                double total_cost = Util.getTotalCost(checkin_date, checkout_date, cost);
//                                Reservation reservation = new Reservation(0, reservation_date, checkin_date, checkout_date,
//                                        total_cost, true, requests_Id, username, rooms_id, room_class);
//
//                                boolean isCreated = false;
//                                try {
//                                    isCreated = reservationService.create(reservation);
//                                } catch (ServiceException e) {
//                                    throw new CommandException("Creating user failed", e);
//                                }
//
//                                if (isCreated) {
//                                    logger.info("Reservation successfully created");
//
//                                    request.getSession().setAttribute("room_name", room_name);
//                                    request.getSession().setAttribute("room_number", room_number);
//                                    request.getSession().setAttribute("capacity", capacity);
//                                    request.getSession().setAttribute("check_in", checkin_date);
//                                    request.getSession().setAttribute("check_out", checkout_date);
//                                    request.getSession().setAttribute("total_cost", total_cost);
//                                    request.getSession().setAttribute("reservation", reservation);
//
//                                    return CommandType.BILL.getCurrentCommand();
//                                }
//                            }
//
//                            request.getSession().setAttribute("messageReservationNotFound", MessageManager.getProperty("message.reservationNotFound"));
//                        }
//                    }
//                }

//            }
            return null;

        }
    }
//}



