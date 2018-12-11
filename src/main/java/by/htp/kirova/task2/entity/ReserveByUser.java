package by.htp.kirova.task2.entity;

import java.util.Objects;

/**
 * Represents a reserved room by user, providing access to reservation Id, reservation date,
 * check in Date, check out Date, total cost, request id, room id.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */

public class ReserveByUser {

    /**
     * The unique serial version identifier.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The unique identification number of reservation.
     */
    private long reservationId;

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
     * The room name.
     */
    private String roomName;

    /**
     * The physical number of room.
     */
    private String roomNumber;

    /**
     * The room capacity (person).
     */
    private int roomCapacity;

    /**
     * The name of class room.
     */
    private String roomClassName;

    /**
     * The assessment room.
     */
    private byte assessment;


    /**
     * Empty constructor for Reservation entity class.
     */
    public ReserveByUser() {
    }


    /**
     * Constructor with all fields of the Reservation class
     * as parameters.
     */
    public ReserveByUser(long reservationId, long reservationDate, long checkinDate, long checkoutDate,
                         double totalCost, String roomName, String roomNumber, int roomCapacity, String roomClassName,
                         byte assessment) {
        this.reservationId = reservationId;
        this.reservationDate = reservationDate;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.totalCost = totalCost;
        this.roomName = roomName;
        this.roomNumber = roomNumber;
        this.roomCapacity = roomCapacity;
        this.roomClassName = roomClassName;
        this.assessment = assessment;
    }


    public long getReservationId() {
        return reservationId;
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

    public String getRoomName() {
        return roomName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public String getRoomClassName() {
        return roomClassName;
    }

    public byte getAssessment() {
        return assessment;
    }



    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
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

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public void setRoomClassName(String roomClassName) {
        this.roomClassName = roomClassName;
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

        ReserveByUser that = (ReserveByUser) o;

        return reservationId == that.reservationId &&
                reservationDate == that.reservationDate &&
                checkinDate == that.checkinDate &&
                checkoutDate == that.checkoutDate &&
                Double.compare(that.totalCost, totalCost) == 0 &&
                roomCapacity == that.roomCapacity &&
                Objects.equals(roomName, that.roomName) &&
                Objects.equals(roomNumber, that.roomNumber) &&
                Objects.equals(roomClassName, that.roomClassName) &&
                assessment == that.assessment;
    }


    @Override
    public int hashCode() {
        int result = 1;

        result = (int) (result * 31 + result * reservationId);
        result = (int) (result * 31 + result * reservationDate);
        result = (int) (result * 31 + result * checkinDate);
        result = (int) (result * 31 + result * checkoutDate);
        result = (int) (result * 31 + result * totalCost);
        result = result * 31 + (roomName == null ? 0 : roomName.hashCode()) * result;
        result = result * 31 + (roomNumber == null ? 0 : roomNumber.hashCode()) * result;
        result = result * 31 + result * roomCapacity;
        result = result * 31 + (roomClassName == null ? 0 : roomClassName.hashCode()) * result;
        result = result * 31 + result * assessment;

        return result;
    }

    @Override
    public String toString() {
        return "ReserveByUser{" +
                "reservationId=" + reservationId +
                ", reservationDate=" + reservationDate +
                ", checkinDate=" + checkinDate +
                ", checkoutDate=" + checkoutDate +
                ", totalCost=" + totalCost +
                ", roomName='" + roomName + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", roomCapacity=" + roomCapacity +
                ", roomClassName='" + roomClassName + '\'' +
                ", assessment=" + assessment +
                '}';
    }
}
