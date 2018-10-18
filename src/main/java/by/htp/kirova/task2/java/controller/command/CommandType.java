package by.htp.kirova.task2.java.controller.command;

/**
 * Enum of commands.
 *
 * @author Kseniya Kirava
 * @since Oct 10, 2018
 */
public enum CommandType {

    /**
     * Command for the user to log in.
     */
    LOGIN {
        {
            this.command = new LoginCommand();

        }
    },
    /**
     * Command for the user to Sign up.
     */
    SIGNUP {
        {
            this.command = new SignupCommand();

        }
    },
    /**
     * Command to show error page.
     */
    ERROR {
        {
            this.command = new ErrorCommand();

        }
    },
    /**
     * Command to show user's account info.
     */
    PROFILE {
        {
            this.command = new ProfileCommand();
        }
    },
    /**
     * Command to show user's reservations.
     */
    RESERVATION {
        {
            this.command = new ReservationCommand();
        }
    },
    /**
     * Command to search info by query.
     */
    REQUEST {
        {
            this.command = new RequestCommand();
        }
    },
    /**
     * Command to add query.
     */
    ADDREQUEST {
        {
            this.command = new AddRequestCommand();
        }
    },
    /**
     * Command to show admin page.
     */
    ADMIN {
        {
            this.command = new AdminCommand();
        }
    };


    public Command command;

    /**
     * Gets current command object.
     *
     * @return current command object.
     */
    public Command getCurrentCommand() {
        return command;
    }

}
