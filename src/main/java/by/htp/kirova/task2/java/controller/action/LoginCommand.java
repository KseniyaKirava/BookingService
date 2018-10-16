package by.htp.kirova.task2.java.controller.action;

import by.htp.kirova.task2.java.controller.CommandType;
import by.htp.kirova.task2.java.entity.User;
import by.htp.kirova.task2.java.logic.UserLogic;
import by.htp.kirova.task2.java.service.validation.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginCommand extends Command {

    private final static String USERNAME = "username";
    private final static String PASSWORD = "password";

    @Override
    public Command execute(HttpServletRequest request, HttpServletResponse response) throws CommandException{
        if (request.getMethod().equalsIgnoreCase("post")) {
            if (request.getParameter("loginbutton") != null) {
                String username = request.getParameter(USERNAME);
                String password = request.getParameter(PASSWORD);
                if (!Validator.checkUsername(username) || !Validator.checkPassword(password)) {
                    return null;
                }

                User user = null;
                try {
                    user = UserLogic.checkLogin(username, password);
                } catch (CommandException e) {
                    e.printStackTrace();
                }

                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    session.setMaxInactiveInterval(60);
                    return CommandType.PROFILE.command;
                }
            }

        }
        return null;
    }
}
