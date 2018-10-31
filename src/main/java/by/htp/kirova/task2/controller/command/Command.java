package by.htp.kirova.task2.controller.command;


import by.htp.kirova.task2.controller.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command of web application.
 *
 * @author Kseniya Kirava
 * @since Oct 14, 2018
 */
public abstract class Command {

    /**
     * Abstract class returning a command.
     *
     * @param request  Initial {@link javax.servlet.http.HttpServletRequest} object.
     * @param response Initial {@link javax.servlet.http.HttpServletResponse} object.
     * @return command.
     * @throws CommandException layer.
     */
    public abstract Command execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;

    @Override
    public String toString() {
        return this.getClass().getSimpleName().replaceFirst("Command", "");
    }

    /**
     * Returns name of page jsp by key.
     *
     * @return name of jsp.
     */
    public String getJsp() {
        return ConfigurationManager.getProperty("path.page." + this.toString().toLowerCase());
    }
}