package by.htp.kirova.task2.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementation for a
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

    @Override
    public Command execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(TOTAL_COST, request.getSession().getAttribute(TOTAL_COST));

        if (request.getMethod().equalsIgnoreCase(POST)) {
            return CommandType.RESERVE.getCurrentCommand();
        }

        return null;
    }
}
