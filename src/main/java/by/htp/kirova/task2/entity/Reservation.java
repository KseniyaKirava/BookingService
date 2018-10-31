package by.htp.kirova.task2.entity;


import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a reservation of an application, providing access to reservation Id, reservation date,
 * check in Date, check out Date, total cost, request id, room id.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class Reservation implements Serializable {

    /**
     * The unique serial version identifier.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The unique identification number of reservation.
     */
    private long id;

    /**
     * The date of reservation.
     */
    private long reservationDate;

    /**
     * The date of check in.
     */
    private long checkinDate;

    /**
     * The date of check out.
     */
    private long checkoutDate;

    /**
     * Total cost of stay.
     */
    private double total_cost;


    /**
     * Access to the Reservation: available or deleted.
     */
    private boolean enabled;

    /**
     * The unique identification number of requests.
     */
    private long requestsId;

    /**
     * The unique identification name of user.
     */
    private String requestsUsersUsername;

    /**
     * The unique identification number of room.
     */
    private long roomsId;

    /**
     * The unique identification number of room class.
     */
    private long roomsRoomClassesId;


    /**
     * Empty constructor for Reservation entity class.
     */
    public Reservation() {
    }


    /**
     * Constructor with all fields of the Reservation class
     * as parameters.
     */
    public Reservation(long id, long reservationDate, long checkinDate, long checkoutDate,
                       double total_cost, boolean enabled, long requestsId, String requestsUsersUsername,
                       long roomsId, long roomsRoomClassesId) {
        this.id = id;
        this.reservationDate = reservationDate;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.total_cost = total_cost;
        this.enabled = enabled;
        this.requestsId = requestsId;
        this.requestsUsersUsername = requestsUsersUsername;
        this.roomsId = roomsId;
        this.roomsRoomClassesId = roomsRoomClassesId;
    }


    public long getId() {
        return id;
    }

    public long getReservationDate() {
        return reservationDate;
    }

    public long getCheckinDate() {
        return checkinDate;
    }

    public long getCheckoutDate() {
        return checkoutDate;
    }

    public double getTotal_cost() {
        return total_cost;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public long getRequestsId() {
        return requestsId;
    }

    public String getRequestsUsersUsername() {
        return requestsUsersUsername;
    }

    public long getRoomsId() {
        return roomsId;
    }

    public long getRoomsRoomClassesId() {
        return roomsRoomClassesId;
    }




    public void setId(long id) {
        this.id = id;
    }

    public void setReservationDate(long reservationDate) {
        this.reservationDate = reservationDate;
    }

    public void setCheckinDate(long checkinDate) {
        this.checkinDate = checkinDate;
    }

    public void setCheckoutDate(long checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public void setTotal_cost(double total_cost) {
        this.total_cost = total_cost;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setRequestsId(long requestsId) {
        this.requestsId = requestsId;
    }

    public void setRequestsUsersUsername(String requestsUsersUsername) {
        this.requestsUsersUsername = requestsUsersUsername;
    }

    public void setRoomsId(long roomsId) {
        this.roomsId = roomsId;
    }

    public void setRoomsRoomClassesId(long roomsRoomClassesId) {
        this.roomsRoomClassesId = roomsRoomClassesId;
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

        Reservation that = (Reservation) o;

        return id == that.id &&
                reservationDate == that.reservationDate &&
                checkinDate == that.checkinDate &&
                checkoutDate == that.checkoutDate &&
                Double.compare(that.total_cost, total_cost) == 0 &&
                enabled == that.enabled &&
                requestsId == that.requestsId &&
                roomsId == that.roomsId &&
                roomsRoomClassesId == that.roomsRoomClassesId &&
                Objects.equals(requestsUsersUsername, that.requestsUsersUsername);
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = (int) (result * 31 + result * id);
        result = (int) (result * 31 + result * reservationDate);
        result = (int) (result * 31 + result * checkinDate);
        result = (int) (result * 31 + result * checkoutDate);
        result = (int) (result * 31 + result * total_cost);
        result = result * 31 + (enabled ? 0 : 1) * result;
        result = (int) (result * 31 + result * requestsId);
        result = result * 31 + (requestsUsersUsername == null ? 0 : requestsUsersUsername.hashCode()) * result;
        result = (int) (result * 31 + result * roomsId);
        result = (int) (result * 31 + result * roomsRoomClassesId);

        return result;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", reservationDate=" + reservationDate +
                ", checkinDate=" + checkinDate +
                ", checkoutDate=" + checkoutDate +
                ", total_cost=" + total_cost +
                ", enabled=" + enabled +
                ", requestsId=" + requestsId +
                ", requestsUsersUsername='" + requestsUsersUsername + '\'' +
                ", roomsId=" + roomsId +
                ", roomsRoomClassesId=" + roomsRoomClassesId +
                '}';
    }
}
