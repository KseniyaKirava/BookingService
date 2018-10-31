package by.htp.kirova.task2.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Abstract class implementation for a
 * particular command type - Error.
 *
 * @author Kseniya Kirava
 * @since Oct 14, 2018
 */
public class ErrorCommand extends Command {

    @Override
    public Command execute(HttpServletRequest req, HttpServletResponse resp) {
        return null;
    }
}
