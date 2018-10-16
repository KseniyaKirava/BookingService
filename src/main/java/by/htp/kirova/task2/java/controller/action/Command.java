package by.htp.kirova.task2.java.controller.action;


import by.htp.kirova.task2.java.controller.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class Command {
    public abstract Command execute(HttpServletRequest req, HttpServletResponse resp);

    @Override
    public String toString() {
        return this.getClass().getSimpleName().replaceFirst("Command", "");
    }

    public String getJsp() {
        return ConfigurationManager.getParameter("path.page." + this.toString().toLowerCase());
    }
}