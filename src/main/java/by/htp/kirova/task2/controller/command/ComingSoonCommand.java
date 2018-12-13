package by.htp.kirova.task2.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Abstract class implementation for a
 * particular command type - Payment.
 *
 * @author Kseniya Kirava
 * @since Oct 20, 2018
 */
public class ComingSoonCommand extends Command {

    @Override
    public Command execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
