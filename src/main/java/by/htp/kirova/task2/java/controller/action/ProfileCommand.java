package by.htp.kirova.task2.java.controller.action;


import by.htp.kirova.task2.java.controller.CommandType;
import by.htp.kirova.task2.java.controller.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ProfileCommand extends Command {

    @Override
    public Command execute(HttpServletRequest req, HttpServletResponse resp) {
        //todo
        Command command = CommandType.PROFILE.command;
        return command;
    }


}
