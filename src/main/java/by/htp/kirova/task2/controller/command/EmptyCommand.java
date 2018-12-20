package by.htp.kirova.task2.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementation for a
 * particular command type - Empty.
 *
 * @author Kseniya Kirava
 * @since Oct 14, 2018
 */
public class EmptyCommand extends Command{

    @Override
    public Command execute(HttpServletRequest request, HttpServletResponse response) {
        return CommandType.MAIN.getCurrentCommand();
    }
}
