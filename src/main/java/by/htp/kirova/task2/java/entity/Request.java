package by.htp.kirova.task2.java.entity;


import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a request of an application, providing access to request Id, room capacity,
 * check in Date, check out Date, room class id, user id.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class Request implements Serializable {

    /**
     * The unique serial version identifier.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The unique identification number of request.
     */
    private long id;

    /**
     * The room capacity (person).
     */
    private int room_capacity;

    /**
     * The date of check in.
     */
    private long checkin_date;

    /**
     * The date of check out.
     */
    private long checkout_date;

    /**
     * The name of room class.
     */
    private String room_class;

    /**
     * Access to the Request: available or deleted.
     */
    private boolean enabled;

    /**
     * The unique identification name of user.
     */
    private String users_username;

    /**
     * Empty constructor for Request entity class.
     */
    public Request() {
    }

    /**
     * Constructor with all fields of the Request class
     * as parameters.
     */
    public Request(long id, int room_capacity, long checkin_date, long checkout_date, String room_class, boolean enabled, String users_username) {
        this.id = id;
        this.room_capacity = room_capacity;
        this.checkin_date = checkin_date;
        this.checkout_date = checkout_date;
        this.room_class = room_class;
        this.enabled = enabled;
        this.users_username = users_username;
    }


    public long getId() {
        return id;
    }

    public int getRoom_capacity() {
        return room_capacity;
    }

    public long getCheckin_date() {
        return checkin_date;
    }

    public long getCheckout_date() {
        return checkout_date;
    }

    public String getRoom_class() {
        return room_class;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getUsers_username() {
        return users_username;
    }




    public void setId(long id) {
        this.id = id;
    }

    public void setRoom_capacity(int room_capacity) {
        this.room_capacity = room_capacity;
    }

    public void setCheckin_date(long checkin_date) {
        this.checkin_date = checkin_date;
    }

    public void setCheckout_date(long checkout_date) {
        this.checkout_date = checkout_date;
    }

    public void setRoom_class(String room_class) {
        this.room_class = room_class;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setUsers_username(String users_username) {
        this.users_username = users_username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }

        Request request = (Request) o;

        return id == request.id &&
                room_capacity == request.room_capacity &&
                checkin_date == request.checkin_date &&
                checkout_date == request.checkout_date &&
                enabled == request.enabled &&
                Objects.equals(room_class, request.room_class) &&
                Objects.equals(users_username, request.users_username);
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = (int)(result * 31 + result * id);
        result = result * 31 + result * room_capacity;
        result = (int) (result * 31 + result * checkin_date);
        result = (int) (result * 31 + result * checkout_date);
        result = result * 31 + (room_class == null ? 0 : room_class.hashCode()) * result;
        result = result * 31 + (enabled ? 0 : 1) * result;
        result = result * 31 + (users_username == null ? 0 : users_username.hashCode()) * result;

        return result;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", room_capacity=" + room_capacity +
                ", checkin_date=" + checkin_date +
                ", checkout_date=" + checkout_date +
                ", room_class='" + room_class + '\'' +
                ", enabled=" + enabled +
                ", users_username='" + users_username + '\'' +
                '}';
    }
}

