package by.htp.kirova.task2.java.controller.action;


import by.htp.kirova.task2.java.controller.CommandType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EmptyCommand extends Command{

    @Override
    public Command execute(HttpServletRequest request, HttpServletResponse response) {
        Command command = CommandType.LOGIN.command;
        return command;
    }
}
