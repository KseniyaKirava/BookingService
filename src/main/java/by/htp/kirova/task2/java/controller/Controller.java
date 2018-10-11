package by.htp.kirova.task2.java.controller;

import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.entity.Facility;
import by.htp.kirova.task2.java.entity.Room;
import by.htp.kirova.task2.java.entity.RoomClass;
import by.htp.kirova.task2.java.entity.RoomHasFacility;
import by.htp.kirova.task2.java.service.GenericService;
import by.htp.kirova.task2.java.service.ServiceException;
import by.htp.kirova.task2.java.service.ServiceFactory;


/**
 * Main servlet of web application.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class Controller{

    public  static void main(String[] args) throws DAOException {


        ServiceFactory serviceFactory = ServiceFactory.getInstance();

        GenericService<RoomClass> roomClassService = serviceFactory.getRoomClassService();
        RoomClass roomClass = new RoomClass(0, "люкс");


        GenericService<Room> roomService = serviceFactory.getRoomService();
        Room room = new Room(0, "абра кадабра", "45d", 5, 45.84,1);

        GenericService<Facility> facilityService = serviceFactory.getFacilityService();
        Facility facility = new Facility(0, "бла");


        GenericService<RoomHasFacility> roomHasFacilityService = serviceFactory.getRoomHasFacilityService();
        RoomHasFacility roomHasFacility = new RoomHasFacility(1, 1, 2);

        try {
            roomClassService.create(roomClass);
            roomService.create(room);
            facilityService.create(facility);
            roomHasFacilityService.create(roomHasFacility);
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
