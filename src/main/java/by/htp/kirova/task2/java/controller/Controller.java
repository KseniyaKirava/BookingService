package by.htp.kirova.task2.java.controller;

import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.entity.*;
import by.htp.kirova.task2.java.service.GenericService;
import by.htp.kirova.task2.java.service.ServiceException;
import by.htp.kirova.task2.java.service.ServiceFactory;


/**
 * Main servlet of web application.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class Controller {

    public static void main(String[] args) throws DAOException {



        ServiceFactory serviceFactory = ServiceFactory.getInstance();

        GenericService<User> userService = serviceFactory.getUserService();
        User user = new User("username", "A@gmail.com", "passwodffrd", "Имя",
                "Фамилия", "yuiyuihhh", true);
//
//        User user2 = new User("USER", "ASDER@gmail.com", "password", "Имя",
//                "Фамилия", "", true);

//        GenericService<Authority> authorityService = serviceFactory.getAuthorityService();
//        Authority authority = new Authority("admin", "username", true);
//
//        GenericService<RoomClass> roomClassService = serviceFactory.getRoomClassService();
//        RoomClass roomClass = new RoomClass(1, "люкс", true);
//
//        GenericService<Room> roomService = serviceFactory.getRoomService();
//        Room room = new Room(0, "комната с терассой", "45d", 5, 45.84, true, 1);
//
//        GenericService<Request> requestService = serviceFactory.getRequestService();
//        Request request = new Request(1, 5, 1539365610550L, 1539365610550L,
//                "люкс", true, "username");
//
//        GenericService<Reservation> reservationService = serviceFactory.getReservationService();
//        Reservation reservation = new Reservation(1, 1539365610550L, 1539365610550L,
//                1539464400000L, 137.52, true, 1, "username",
//                1, 1);
//
//        GenericService<Facility> facilityService = serviceFactory.getFacilityService();
//        Facility facility = new Facility(3, "кровать", true);
////
//
//        GenericService<RoomHasFacility> roomHasFacilityService = serviceFactory.getRoomHasFacilityService();
//        RoomHasFacility roomHasFacility = new RoomHasFacility(1, 1, 2, true);

        try {
            userService.create(user);
//            userService.read("WHERE username = 'username'");
//            userService.update(user);
//            userService.delete(user);
//            authorityService.create(authority);
//            roomClassService.create(roomClass);
//            roomService.create(room);
//            requestService.create(request);
//            reservationService.create(reservation);

//            facilityService.create(facility);
//            facilityService.read("WHERE id = 1");

//            facilityService.delete(facility);
//            roomHasFacilityService.create(roomHasFacility);
        } catch (ServiceException e) {
            e.printStackTrace();
        }


    }

//    @Override
//    public void init() throws ServletException {
//        super.init();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
//    }
}
