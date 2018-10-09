package by.htp.kirova.task2.java.controller;

import by.htp.kirova.task2.java.connectionpool.ConnectionPool;
import by.htp.kirova.task2.java.connectionpool.ConnectionPoolException;
import by.htp.kirova.task2.java.dao.DAOException;
import by.htp.kirova.task2.java.dao.DAOFactory;
import by.htp.kirova.task2.java.dao.GenericDAO;
import by.htp.kirova.task2.java.entity.Facility;

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

//        DAOFactory instance = DAOFactory.getInstance();
//        GenericDAO daoFacility = instance.getDaoFacility();
//
//        Facility facility = new Facility();
//        facility.setId(1);
//        facility.setName("adsdssf");
//        boolean b = daoFacility.create(facility);




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
