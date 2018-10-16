package by.htp.kirova.task2.java.controller;

import by.htp.kirova.task2.java.controller.action.*;

/**
 * Enum.
 *
 * @author Kseniya Kirava
 * @since Oct 10, 2018
 */
public enum CommandType {
    /**
     * Command to show index page.
     */
    INDEX {
        {
//            this.command = new IndexCommand();
        }
    },

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
    MYRESERVATIONS {
        {
//            this.command = new MyReservationCommand();
        }
    },
    /**
     * Command to search info by query.
     */
    SEARCH {
        {
//            this.command = new SearchCommand();
        }
    },
    /**
     * Command to show admin page.
     */
    ADMIN {
        {
//            this.command = new AdminCommand();
        }
    };

    public Command command;

    public Command getCurrentCommand() {
        return command;
    }

}
