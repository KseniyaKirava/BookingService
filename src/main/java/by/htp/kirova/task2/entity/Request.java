package by.htp.kirova.task2.entity;


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
    private int roomCapacity;

    /**
     * The date of check in.
     */
    private long checkinDate;

    /**
     * The date of check out.
     */
    private long checkoutDate;

    /**
     * The name of room class.
     */
    private String roomClass;

    /**
     * Access to the Request: available or deleted.
     */
    private boolean enabled;

    /**
     * The unique identification name of user.
     */
    private String usersUsername;

    /**
     * Empty constructor for Request entity class.
     */
    public Request() {
    }

    /**
     * Constructor with all fields of the Request class
     * as parameters.
     */
    public Request(long id, int roomCapacity, long checkinDate, long checkoutDate, String roomClass, boolean enabled, String usersUsername) {
        this.id = id;
        this.roomCapacity = roomCapacity;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.roomClass = roomClass;
        this.enabled = enabled;
        this.usersUsername = usersUsername;
    }


    public long getId() {
        return id;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public long getCheckinDate() {
        return checkinDate;
    }

    public long getCheckoutDate() {
        return checkoutDate;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getUsersUsername() {
        return usersUsername;
    }




    public void setId(long id) {
        this.id = id;
    }

    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public void setCheckinDate(long checkinDate) {
        this.checkinDate = checkinDate;
    }

    public void setCheckoutDate(long checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setUsersUsername(String usersUsername) {
        this.usersUsername = usersUsername;
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
                roomCapacity == request.roomCapacity &&
                checkinDate == request.checkinDate &&
                checkoutDate == request.checkoutDate &&
                enabled == request.enabled &&
                Objects.equals(roomClass, request.roomClass) &&
                Objects.equals(usersUsername, request.usersUsername);
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = (int)(result * 31 + result * id);
        result = result * 31 + result * roomCapacity;
        result = (int) (result * 31 + result * checkinDate);
        result = (int) (result * 31 + result * checkoutDate);
        result = result * 31 + (roomClass == null ? 0 : roomClass.hashCode()) * result;
        result = result * 31 + (enabled ? 0 : 1) * result;
        result = result * 31 + (usersUsername == null ? 0 : usersUsername.hashCode()) * result;

        return result;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", roomCapacity=" + roomCapacity +
                ", checkinDate=" + checkinDate +
                ", checkoutDate=" + checkoutDate +
                ", roomClass='" + roomClass + '\'' +
                ", enabled=" + enabled +
                ", usersUsername='" + usersUsername + '\'' +
                '}';
    }
}

