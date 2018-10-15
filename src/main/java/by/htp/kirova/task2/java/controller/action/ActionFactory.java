package by.htp.kirova.task2.java.controller.action;

import by.htp.kirova.task2.java.controller.CommandType;
import by.htp.kirova.task2.java.controller.MessageManager;
import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
        String action = request.getParameter("command");

        if (action == null || action.isEmpty()) {
            return current;
        }

        try {
            CommandType currentType = CommandType.valueOf(action.toUpperCase());
            current = currentType.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action + MessageManager.getParameter("message.wrongaction"));
        }

        return current;
    }
}
