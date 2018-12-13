package by.htp.kirova.task2.controller.command;

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
    BILL {
        {
            this.command = new SearchCommand();
        }
    },
    /**
     * Command to add query.
     */
    MAIN {
        {
            this.command = new MainCommand();
        }
    },
    /**
     * Command to show admin page.
     */
    ADMIN {
        {
            this.command = new AdminCommand();
        }
    },
    /**
     * Command to show user's reserve room page.
     */
    RESERVE {
        {
            this.command = new ReserveCommand();
        }
    },
    /**
     * Command to show user's reservation payment page.
     */
    PAYMENT {
        {
            this.command = new PaymentCommand();
        }
    },
    COMINGSOON {
        {
            this.command = new ComingSoonCommand();
        }
    };

    Command command;

    public Command getCurrentCommand(){
        return command;
    }

}
