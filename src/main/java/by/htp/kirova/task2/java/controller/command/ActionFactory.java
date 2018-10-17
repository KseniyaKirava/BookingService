package by.htp.kirova.task2.java.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Creates instances which implements {@link
 * by.htp.kirova.task2.java.controller.command.Command}.
 *
 * @author Kseniya Kirava
 * @since Oct 14, 2018
 */

public class ActionFactory {

    /**
     * Returns command from request.
     *
     * @param request  Initial {@link javax.servlet.http.HttpServletRequest} object.
     * @return command from request.
     *
     */
    public Command defineCommand(HttpServletRequest request) throws CommandException{
        String commandName = request.getParameter("command");
        Command current = new EmptyCommand();
        if (commandName != null && !commandName.isEmpty()) {
            try {
                current = CommandType.valueOf(commandName.toUpperCase()).command;
            }
            catch (IllegalArgumentException e) {
                throw new CommandException("Command not found");
            }
        }
        return current;
    }

}
