package by.htp.kirova.task2.java.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Abstract class implementation for a
 * particular command type - Reserve.
 *
 * @author Kseniya Kirava
 * @since Oct 20, 2018
 */
public class ReserveCommand extends Command{

    @Override
    public Command execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        return null;
    }
}
