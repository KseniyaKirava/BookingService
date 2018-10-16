package by.htp.kirova.task2.java.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorCommand extends Command {

    @Override
    public Command execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        return null;
    }
}
