package by.htp.kirova.task2.java.entity;


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
    private long reservation_date;

    /**
     * The date of check in.
     */
    private long checkin_date;

    /**
     * The date of check out.
     */
    private long checkout_date;

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
    private long requests_id;

    /**
     * The unique identification name of user.
     */
    private String requests_users_username;

    /**
     * The unique identification number of room.
     */
    private long rooms_id;

    /**
     * The unique identification number of room class.
     */
    private long rooms_room_classes_id;


    /**
     * Empty constructor for Reservation entity class.
     */
    public Reservation() {
    }


    /**
     * Constructor with all fields of the Reservation class
     * as parameters.
     */
    public Reservation(long id, long reservation_date, long checkin_date, long checkout_date,
                       double total_cost, boolean enabled, long requests_id, String requests_users_username,
                       long rooms_id, long rooms_room_classes_id) {
        this.id = id;
        this.reservation_date = reservation_date;
        this.checkin_date = checkin_date;
        this.checkout_date = checkout_date;
        this.total_cost = total_cost;
        this.enabled = enabled;
        this.requests_id = requests_id;
        this.requests_users_username = requests_users_username;
        this.rooms_id = rooms_id;
        this.rooms_room_classes_id = rooms_room_classes_id;
    }


    public long getId() {
        return id;
    }

    public long getReservation_date() {
        return reservation_date;
    }

    public long getCheckin_date() {
        return checkin_date;
    }

    public long getCheckout_date() {
        return checkout_date;
    }

    public double getTotal_cost() {
        return total_cost;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public long getRequests_id() {
        return requests_id;
    }

    public String getRequests_users_username() {
        return requests_users_username;
    }

    public long getRooms_id() {
        return rooms_id;
    }

    public long getRooms_room_classes_id() {
        return rooms_room_classes_id;
    }




    public void setId(long id) {
        this.id = id;
    }

    public void setReservation_date(long reservation_date) {
        this.reservation_date = reservation_date;
    }

    public void setCheckin_date(long checkin_date) {
        this.checkin_date = checkin_date;
    }

    public void setCheckout_date(long checkout_date) {
        this.checkout_date = checkout_date;
    }

    public void setTotal_cost(double total_cost) {
        this.total_cost = total_cost;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setRequests_id(long requests_id) {
        this.requests_id = requests_id;
    }

    public void setRequests_users_username(String requests_users_username) {
        this.requests_users_username = requests_users_username;
    }

    public void setRooms_id(long rooms_id) {
        this.rooms_id = rooms_id;
    }

    public void setRooms_room_classes_id(long rooms_room_classes_id) {
        this.rooms_room_classes_id = rooms_room_classes_id;
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
                reservation_date == that.reservation_date &&
                checkin_date == that.checkin_date &&
                checkout_date == that.checkout_date &&
                Double.compare(that.total_cost, total_cost) == 0 &&
                enabled == that.enabled &&
                requests_id == that.requests_id &&
                rooms_id == that.rooms_id &&
                rooms_room_classes_id == that.rooms_room_classes_id &&
                Objects.equals(requests_users_username, that.requests_users_username);
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = (int) (result * 31 + result * id);
        result = (int) (result * 31 + result * reservation_date);
        result = (int) (result * 31 + result * checkin_date);
        result = (int) (result * 31 + result * checkout_date);
        result = (int) (result * 31 + result * total_cost);
        result = result * 31 + (enabled ? 0 : 1) * result;
        result = (int) (result * 31 + result * requests_id);
        result = result * 31 + (requests_users_username == null ? 0 : requests_users_username.hashCode()) * result;
        result = (int) (result * 31 + result * rooms_id);
        result = (int) (result * 31 + result * rooms_room_classes_id);

        return result;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", reservation_date=" + reservation_date +
                ", checkin_date=" + checkin_date +
                ", checkout_date=" + checkout_date +
                ", total_cost=" + total_cost +
                ", enabled=" + enabled +
                ", requests_id=" + requests_id +
                ", requests_users_username='" + requests_users_username + '\'' +
                ", rooms_id=" + rooms_id +
                ", rooms_room_classes_id=" + rooms_room_classes_id +
                '}';
    }
}
