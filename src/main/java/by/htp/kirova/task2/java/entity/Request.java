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
    private boolean enable;

    /**
     * The unique identification name of user.
     */
    private String users_username;


    public Request() {
    }

    public Request(long id, int room_capacity, long checkin_date, long checkout_date, String room_class, boolean enable, String users_username) {
        this.id = id;
        this.room_capacity = room_capacity;
        this.checkin_date = checkin_date;
        this.checkout_date = checkout_date;
        this.room_class = room_class;
        this.enable = enable;
        this.users_username = users_username;
    }

    /**
     * Returns request unique identification number.
     *
     * @return java.lang.Long request unique identification number.
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the room capacity (person).
     *
     * @return room capacity (person).
     */
    public int getRoom_capacity() {
        return room_capacity;
    }

    /**
     * Returns date of check in.
     *
     * @return java.lang.Long date of check in.
     */
    public long getCheckin_date() {
        return checkin_date;
    }

    /**
     * Returns date of check out.
     *
     * @return java.lang.Long date of check out.
     */
    public long getCheckout_date() {
        return checkout_date;
    }

    /**
     * Returns room class name.
     *
     * @return java.lang.String room class name.
     */
    public String getRoom_class() {
        return room_class;
    }

    /**
     * Returns access to Request
     *
     * @return access to Request {@code true} if access granted, {@code false} otherwise.
     */
    public boolean isEnable() {
        return enable;
    }

    /**
     * Returns user unique identification name.
     *
     * @return java.lang.String user unique identification name.
     */
    public String getUsers_username() {
        return users_username;
    }

    /**
     * Sets request's unique identification number.
     *
     * @param id request's unique identification number.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Sets the room capacity (person).
     *
     * @param room_capacity the room capacity (person).
     */
    public void setRoom_capacity(int room_capacity) {
        this.room_capacity = room_capacity;
    }

    /**
     * Sets date of check in.
     *
     * @param checkin_date date of check in.
     */
    public void setCheckin_date(long checkin_date) {
        this.checkin_date = checkin_date;
    }

    /**
     * Sets date of check out.
     *
     * @param checkout_date date of check out.
     */
    public void setCheckout_date(long checkout_date) {
        this.checkout_date = checkout_date;
    }

    /**
     * Sets room class name.
     *
     * @param room_class room class name.
     */
    public void setRoom_class(String room_class) {
        this.room_class = room_class;
    }

    /**
     * Set access to Request
     *
     * @param enable boolean access state.
     */
    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    /**
     * Sets user's unique identification name.
     *
     * @param users_username user's unique identification name.
     */
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
                enable == request.enable &&
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
                ", users_username='" + users_username + '\'' +
                '}';
    }
}
