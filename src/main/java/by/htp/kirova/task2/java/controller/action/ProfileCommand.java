package by.htp.kirova.task2.java.controller.action;



import by.htp.kirova.task2.java.controller.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;


public class ProfileCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getParameter("path.page.login");
        return page;
    }
}
