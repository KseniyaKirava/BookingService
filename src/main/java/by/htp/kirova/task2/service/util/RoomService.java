package by.htp.kirova.task2.service.util;

import by.htp.kirova.task2.controller.command.CommandException;
import by.htp.kirova.task2.entity.Reservation;
import by.htp.kirova.task2.entity.Room;
import by.htp.kirova.task2.entity.User;
import by.htp.kirova.task2.service.BookingService;
import by.htp.kirova.task2.service.ServiceException;
import by.htp.kirova.task2.service.ServiceFactory;
import by.htp.kirova.task2.service.validation.Validator;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/**
 * Contains methods which provide several logic to work with user.
 *
 * @author Kseniya Kirava
 * @since Sep 24, 2018
 */
public class RoomService {


    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger logger = Logger.getLogger(RoomService.class);

    /**
     * The SQL 'where' query for rating constant.
     */
    private static final String RATING_WHERE_QUERY = " WHERE rooms_id = %d";


    /**
     * Provided average marks of each room in array.
     *
     * @param rooms list of selected rooms.
     * @return rooms with averaged marks.
     */
    public static List<Room> getAverageMarksOfSelectedRooms(List<Room> rooms) {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookingService<Reservation> reservationService = serviceFactory.getReservationService();

        try {
            for (Room room : rooms) {
                String sqlForRating = String.format(RATING_WHERE_QUERY, room.getId());
                List<Reservation> reservations = reservationService.read(sqlForRating);
                int count = 0;
                int sum = 0;

                for (Reservation res : reservations) {
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

        } catch (ServiceException e) {
            logger.error("Can't process the request correctly: ", e);
        }

        return rooms;
    }
}
