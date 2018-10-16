package by.htp.kirova.task2.java.controller.action;

import by.htp.kirova.task2.java.controller.CommandType;
import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    public Command defineCommand(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        Command current = new EmptyCommand();
        if (commandName != null && !commandName.isEmpty()) {
            try {
                current = CommandType.valueOf(commandName.toUpperCase()).command;
            }
            catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Command not found");
            }
        }
        return current;
    }

}
