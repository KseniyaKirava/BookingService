package by.htp.kirova.task2.controller.command;

import by.htp.kirova.task2.entity.User;
import by.htp.kirova.task2.service.util.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Abstract class implementation for a
 * particular command type - Payment.
 *
 * @author Kseniya Kirava
 * @since Oct 20, 2018
 */
public class PaymentCommand extends Command {

    /**
     * The request method post constant.
     */
    private final static String POST = "post";

    /**
     * The attribute name.
     */
    private final static String TOTAL_COST = "totalCost";

    //todo разбить на методы

    @Override
    public Command execute(HttpServletRequest request, HttpServletResponse response) {
        User user = UserService.getUserFromSession(request);
        if (user == null) {
            return CommandType.LOGIN.getCurrentCommand();
        } else if (request.getSession().getAttribute("role").equals("admin")) {
            return CommandType.ADMIN.getCurrentCommand();
        }
//        else if (request.getSession().getAttribute("role").equals("manager")) {
//            return CommandType.MANAGER.getCurrentCommand();
//        }

        else {
            request.setAttribute(TOTAL_COST, request.getSession().getAttribute(TOTAL_COST));

            if (request.getMethod().equalsIgnoreCase(POST)) {
                return CommandType.RESERVE.getCurrentCommand();
            }
        }
        return null;
    }
}
