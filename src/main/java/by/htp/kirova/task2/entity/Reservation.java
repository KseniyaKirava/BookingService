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
    private double totalCost;

    /**
     * Access to the Reservation: available or deleted.
     */
    private boolean enabled;

    /**
     * The unique identification name of user.
     */
    private String usersUsername;

    /**
     * The unique identification number of room.
     */
    private long roomsId;

    /**
     * The assessment of room for current reservation.
     */
    private byte assessment;

    /**
     * Empty constructor for Reservation entity class.
     */
    public Reservation() {
    }

    /**
     * Constructor with all fields of the Reservation class
     * as parameters.
     */
    public Reservation(long id, long reservationDate, long checkinDate, long checkoutDate, double totalCost,
                       boolean enabled, String usersUsername, long roomsId, byte assessment) {
        this.id = id;
        this.reservationDate = reservationDate;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.totalCost = totalCost;
        this.enabled = enabled;
        this.usersUsername = usersUsername;
        this.roomsId = roomsId;
        this.assessment = assessment;
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

    public double getTotalCost() {
        return totalCost;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getUsersUsername() {
        return usersUsername;
    }

    public long getRoomsId() {
        return roomsId;
    }

    public byte getAssessment() {
        return assessment;
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

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setUsersUsername(String usersUsername) {
        this.usersUsername = usersUsername;
    }

    public void setRoomsId(long roomsId) {
        this.roomsId = roomsId;
    }

    public void setAssessment(byte assessment) {
        this.assessment = assessment;
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
                Double.compare(that.totalCost, totalCost) == 0 &&
                enabled == that.enabled &&
                roomsId == that.roomsId &&
                assessment == that.assessment &&
                Objects.equals(usersUsername, that.usersUsername);
    }


    @Override
    public int hashCode() {
        int result = 1;

        result = (int) (result * 31 + result * id);
        result = (int) (result * 31 + result * reservationDate);
        result = (int) (result * 31 + result * checkinDate);
        result = (int) (result * 31 + result * checkoutDate);
        result = (int) (result * 31 + result * totalCost);
        result = result * 31 + (enabled ? 0 : 1) * result;
        result = result * 31 + (usersUsername == null ? 0 : usersUsername.hashCode()) * result;
        result = (int) (result * 31 + result * roomsId);
        result = result * 31 + result * assessment;

        return result;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", reservationDate=" + reservationDate +
                ", checkinDate=" + checkinDate +
                ", checkoutDate=" + checkoutDate +
                ", totalCost=" + totalCost +
                ", enabled=" + enabled +
                ", usersUsername='" + usersUsername + '\'' +
                ", roomsId=" + roomsId +
                ", assessment=" + assessment +
                '}';
    }
}
