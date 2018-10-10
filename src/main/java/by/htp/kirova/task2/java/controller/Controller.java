package by.htp.kirova.task2.java.controller;

import by.htp.kirova.task2.java.connectionpool.ConnectionPool;
import by.htp.kirova.task2.java.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.dao.DAOFactory;
import by.htp.kirova.task2.java.dao.GenericDAO;
import by.htp.kirova.task2.java.entity.Facility;
import by.htp.kirova.task2.java.service.GenericService;
import by.htp.kirova.task2.java.service.ServiceException;
import by.htp.kirova.task2.java.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Main servlet of web application.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class Controller{

    public  static void main(String[] args) throws DAOException {


        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        GenericService<Facility> facilityService = serviceFactory.getFacilityService();

        Facility facility = new Facility();
        facility.setId(1);
        facility.setName("dfjgldfjlfjg");

        try {
            facilityService.delete(facility);
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
