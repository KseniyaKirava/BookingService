package by.htp.kirova.task2.java.controller.action;

import by.htp.kirova.task2.java.controller.CommandType;
import by.htp.kirova.task2.java.entity.User;
import by.htp.kirova.task2.java.logic.UserLogic;
import by.htp.kirova.task2.java.service.ServiceException;
import by.htp.kirova.task2.java.service.ServiceFactory;
import by.htp.kirova.task2.java.service.validation.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignupCommand extends Command {

    @Override
    public Command execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        if (req.getMethod().equalsIgnoreCase("post")) {
            String username = req.getParameter("username");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String first_name = req.getParameter("first_name");
            String last_name = req.getParameter("last_name");
            String middle_name = req.getParameter("middle_name");

            if (!Validator.checkUsername(username) || !Validator.checkEmail(email) ||
                    !Validator.checkPassword(password) || !Validator.checkPassword(password) ||
                    !Validator.checkName(first_name) || !Validator.checkName(last_name) ||
                    !Validator.checkMiddleName(middle_name)) {
                return CommandType.SIGNUP.command;
            } else {
                String hashPassword = UserLogic.getHashPassword(password);
                User user = new User(username, email, hashPassword, first_name, last_name, middle_name, true);
                ServiceFactory serviceFactory = ServiceFactory.getInstance();
                boolean isCreate = false;
                try {
                    isCreate = serviceFactory.getUserService().create(user);
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
                if (isCreate) {
                    return CommandType.LOGIN.command;
                }
            }

        }
        return null;
    }
}
