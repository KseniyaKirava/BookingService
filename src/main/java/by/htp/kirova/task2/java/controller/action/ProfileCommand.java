package by.htp.kirova.task2.java.controller.action;


import by.htp.kirova.task2.java.controller.CommandType;
import by.htp.kirova.task2.java.entity.User;
import by.htp.kirova.task2.java.logic.UserLogic;
import by.htp.kirova.task2.java.service.GenericService;
import by.htp.kirova.task2.java.service.ServiceException;
import by.htp.kirova.task2.java.service.ServiceFactory;
import by.htp.kirova.task2.java.service.validation.Validator;
import by.htp.kirova.task2.java.util.Util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ProfileCommand extends Command {

    @Override
    public Command execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = Util.getUserFromSession(request);


        if (user == null)
            return CommandType.LOGIN.command;
        else {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            GenericService<User> userService = serviceFactory.getUserService();

            String username = user.getUsername();
            String currentPassword = user.getPassword();
            Cookie cookie = new Cookie(username, currentPassword);
            cookie.setMaxAge(60);
            response.addCookie(cookie);
            request.setAttribute("username", user.getUsername());
            request.setAttribute("email", user.getEmail());
            request.setAttribute("password", user.getPassword());
            request.setAttribute("first_name", user.getFirst_name());
            request.setAttribute("last_name", user.getLast_name());
            request.setAttribute("middle_name", user.getMiddle_name());
            if (request.getMethod().equalsIgnoreCase("post")) {
                if (request.getParameter("saveinfo") != null) {
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");
                    String first_name = request.getParameter("first_name");
                    String last_name = request.getParameter("last_name");
                    String middle_name = request.getParameter("middle_name");
                    if (!password.equals(currentPassword) && !Validator.checkPassword(password)) {
                        return null;
                    }
                    if (!Validator.checkEmail(email) || !Validator.checkName(first_name) ||
                            !Validator.checkName(last_name) || !Validator.checkMiddleName(middle_name)) {
                        return null;
                    }
                    String hashPassword = UserLogic.getHashPassword(password);
                    user.setEmail(email);
                    user.setPassword(hashPassword);
                    user.setFirst_name(first_name);
                    user.setLast_name(last_name);
                    user.setMiddle_name(middle_name);

                    boolean isUpdate = false;
                    try {
                        isUpdate = userService.update(user);
                    } catch (ServiceException e) {
                        e.printStackTrace();
                    }
                    if (isUpdate) {
                        System.out.println("EHF");//msg о том что данные сохранены успешно/неуспешно
                    }
                    return CommandType.PROFILE.command;
                }
                if (request.getParameter("logout") != null) {
                    request.getSession().invalidate();
                    return CommandType.LOGIN.command;
                }
                if (request.getParameter("deletemyaccount") != null) {
                    user.setEnable(false);
                    try {
                        userService.update(user);
                    } catch (ServiceException e) {
                        e.printStackTrace();
                    }
                    request.getSession().invalidate();
                    return CommandType.LOGIN.command;
                }

            }

        }
        return null;

    }


}



