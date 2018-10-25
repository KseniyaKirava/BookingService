package by.htp.kirova.task2.java.controller.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Abstract class implementation for a
 * particular command type.
 *
 * @author Kseniya Kirava
 * @since Oct 14, 2018
 */
public class EmptyCommand extends Command{

    @Override
    public Command execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        return CommandType.LOGIN.command;
    }
}
