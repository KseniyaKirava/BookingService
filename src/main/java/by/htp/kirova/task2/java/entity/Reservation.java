package by.htp.kirova.task2.java.entity;


import java.util.Objects;

/**
 * Represents a reservation of an application, providing access to reservation Id, reservation date,
 * check in Date, check out Date, total cost, request id, room id.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class Reservation {

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

    public Reservation() {
    }

    public Reservation(long id, long reservation_date, long checkin_date, long checkout_date,
                       double total_cost, long requests_id, String requests_users_username, long rooms_id,
                       long rooms_room_classes_id) {
        this.id = id;
        this.reservation_date = reservation_date;
        this.checkin_date = checkin_date;
        this.checkout_date = checkout_date;
        this.total_cost = total_cost;
        this.requests_id = requests_id;
        this.requests_users_username = requests_users_username;
        this.rooms_id = rooms_id;
        this.rooms_room_classes_id = rooms_room_classes_id;
    }

    /**
     * Returns reservation unique identification number.
     *
     * @return java.lang.Long reservation unique identification number.
     */
    public long getId() {
        return id;
    }

    /**
     * Returns date of reservation.
     *
     * @return java.lang.Long date of reservation.
     */
    public long getReservation_date() {
        return reservation_date;
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
     *  Returns total cost of stay.
     *
     * @return java.lang.Double total cost of stay.
     */
    public double getTotal_cost() {
        return total_cost;
    }

    /**
     * Returns request unique identification number.
     *
     * @return java.lang.Long request unique identification number.
     */
    public long getRequests_id() {
        return requests_id;
    }

    /**
     * Returns user unique identification name.
     *
     * @return java.lang.String user unique identification name.
     */
    public String getRequests_users_username() {
        return requests_users_username;
    }

    /**
     * Returns room unique identification number.
     *
     * @return java.lang.Long room unique identification number.
     */
    public long getRooms_id() {
        return rooms_id;
    }

    /**
     * Returns room class unique identification number.
     *
     * @return java.lang.Long room class unique identification number.
     */
    public long getRooms_room_classes_id() {
        return rooms_room_classes_id;
    }

    /**
     * Sets reservation's unique identification number.
     *
     * @param id reservation's unique identification number.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Sets date of reservation.
     *
     * @param reservation_date date of reservation.
     */
    public void setReservation_date(long reservation_date) {
        this.reservation_date = reservation_date;
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
     * Sets total cost of stay.
     *
     * @param total_cost total cost of stay.
     */
    public void setTotal_cost(double total_cost) {
        this.total_cost = total_cost;
    }

    /**
     * Sets request's unique identification number.
     *
     * @param requests_id request's unique identification number.
     */
    public void setRequests_id(long requests_id) {
        this.requests_id = requests_id;
    }

    /**
     * Sets user's unique identification name.
     *
     * @param requests_users_username user's unique identification name.
     */
    public void setRequests_users_username(String requests_users_username) {
        this.requests_users_username = requests_users_username;
    }

    /**
     * Sets room's unique identification number.
     *
     * @param rooms_id room's unique identification number.
     */
    public void setRooms_id(long rooms_id) {
        this.rooms_id = rooms_id;
    }

    /**
     * Sets room class unique identification number.
     *
     * @param rooms_room_classes_id room class unique identification number.
     */
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
                ", requests_id=" + requests_id +
                ", requests_users_username='" + requests_users_username + '\'' +
                ", rooms_id=" + rooms_id +
                ", rooms_room_classes_id=" + rooms_room_classes_id +
                '}';
    }
}
