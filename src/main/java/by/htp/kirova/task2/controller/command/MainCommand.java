package by.htp.kirova.task2.controller.command;


import by.htp.kirova.task2.controller.MessageManager;
import by.htp.kirova.task2.entity.User;
import by.htp.kirova.task2.service.util.DateService;
import by.htp.kirova.task2.service.util.UserService;
import by.htp.kirova.task2.service.validation.Validator;
import org.apache.log4j.Logger;
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
public class MainCommand extends Command {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger logger = Logger.getLogger(MainCommand.class);

    /**
     * The room capacity (person).
     */
    private static final String ROOM_CAPACITY = "roomCapacity";

    /**
     * Check in Date.
     */
    private static final String CHECKIN_DATE = "checkinDate";

    /**
     * Check out Date.
     */
    private static final String CHECKOUT_DATE = "checkoutDate";

    /**
     * Class of te room.
     */
    private static final String ROOM_CLASS_ID = "roomClassId";

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
     * The request method post constant.
     */
    private final static String POST = "post";

    /**
     * The username constant.
     */
    private final static String USERNAME = "username";

    /**
     * The name of button constant.
     */
    private final static String SEARCH_BUTTON = "search";

    /**
     * The request attribute message name for attribute 'error with login or password'.
     */
    private final static String MESSAGE_INCORRECT_DATA = "message.incorrectData";

    /**
     * The request attribute for message 'error search command'.
     */
    private final static String ERROR_SEARCH_COMMAND = "errorSearchCommand";

    //todo разбить на методы

    @Override
    public Command execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = UserService.getUserFromSession(request);
        String username = null;
        if (user != null) {
            username = (String) request.getSession().getAttribute(USERNAME);
        }

        Validator validator = Validator.getInstance();

        String roomCapacityString = request.getParameter(ROOM_CAPACITY);
        String checkin = request.getParameter(CHECKIN_DATE);
        String checkout = request.getParameter(CHECKOUT_DATE);
        String roomClassIdString = request.getParameter(ROOM_CLASS_ID);


        if (request.getMethod().equalsIgnoreCase(POST)) {

            if (request.getParameter(SEARCH_BUTTON) != null) {

                long checkinDate = 0;
                long checkoutDate = 0;
                long roomClassId = 0;
                int roomCapacity = 0;

                try {
                    checkinDate = DateService.convertDateToMiliseconds(checkin);
                    checkoutDate = DateService.convertDateToMiliseconds(checkout);
                    roomCapacity = Integer.parseInt(roomCapacityString);
                    roomClassId = Long.parseLong(roomClassIdString);
                } catch (ParseException e) {
                    logger.error("Date parsing ended with error");
                }

                if (!validator.checkCapacity(roomCapacity) ||
                        !validator.checkCheckinCheckoutDate(checkinDate, checkoutDate) ||
                        !(roomClassId > 0)) {
                    String messageIncorrectData = MessageManager.getMessageInSessionLanguage(request.getSession(), MESSAGE_INCORRECT_DATA);
                    request.setAttribute(ERROR_SEARCH_COMMAND, messageIncorrectData);
                    logger.debug("Data validation failed");
                    return null;
                }

                    request.getSession().setAttribute(ROOM_CLASS_ID_TEMP, roomClassId);
                    request.getSession().setAttribute(ROOM_CAPACITY_TEMP, roomCapacity);
                    request.getSession().setAttribute(CHECKIN_DATE_TEMP, checkinDate);
                    request.getSession().setAttribute(CHECKOUT_DATE_TEMP, checkoutDate);

                    return CommandType.SEARCH.getCurrentCommand();
            }

        }
        return null;
    }

}






