package by.htp.kirova.task2.entity;

import java.util.Objects;

/**
 * Represents a reservations, providing access to reservation Id, first name, last name, check in Date, check out Date,
 * room name, room number, room capacity, room class name, total cost fields.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class ReserveForManager {

    /**
     * The unique identification number of reservation.
     */
    private long reservationId;

    /**
     * The user's first name.
     */
    private String firstName;

    /**
     * The user's last name.
     */
    private String lastName;

    /**
     * The date of check in.
     */
    private long checkinDate;

    /**
     * The date of check out.
     */
    private long checkoutDate;

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
     * Total cost of stay.
     */
    private double totalCost;




    /**
     * Empty constructor for Reserve for manager entity class.
     */
    public ReserveForManager() {
    }




    /**
     * Constructor with all fields of the Reserve for manager class
     * as parameters.
     */
    public ReserveForManager(long reservationId, String firstName, String lastName, long checkinDate,
                             long checkoutDate, String roomName, String roomNumber, int roomCapacity,
                             String roomClassName, double totalCost) {
        this.reservationId = reservationId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.roomName = roomName;
        this.roomNumber = roomNumber;
        this.roomCapacity = roomCapacity;
        this.roomClassName = roomClassName;
        this.totalCost = totalCost;
    }



    public long getReservationId() {
        return reservationId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getCheckinDate() {
        return checkinDate;
    }

    public long getCheckoutDate() {
        return checkoutDate;
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

    public double getTotalCost() {
        return totalCost;
    }




    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCheckinDate(long checkinDate) {
        this.checkinDate = checkinDate;
    }

    public void setCheckoutDate(long checkoutDate) {
        this.checkoutDate = checkoutDate;
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

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
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

        ReserveForManager that = (ReserveForManager) o;

        return reservationId == that.reservationId &&
                checkinDate == that.checkinDate &&
                checkoutDate == that.checkoutDate &&
                roomCapacity == that.roomCapacity &&
                Double.compare(that.totalCost, totalCost) == 0 &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(roomName, that.roomName) &&
                Objects.equals(roomNumber, that.roomNumber) &&
                Objects.equals(roomClassName, that.roomClassName);
    }





    @Override
    public int hashCode() {
        int result = 1;

        result = (int) (result * 31 + result * reservationId);
        result = result * 31 + (firstName == null ? 0 : firstName.hashCode()) * result;
        result = result * 31 + (lastName == null ? 0 : lastName.hashCode()) * result;
        result = (int) (result * 31 + result * checkinDate);
        result = (int) (result * 31 + result * checkoutDate);
        result = (int) (result * 31 + result * totalCost);
        result = result * 31 + (roomName == null ? 0 : roomName.hashCode()) * result;
        result = result * 31 + (roomNumber == null ? 0 : roomNumber.hashCode()) * result;
        result = result * 31 + result * roomCapacity;
        result = result * 31 + (roomClassName == null ? 0 : roomClassName.hashCode()) * result;

        return result;
    }




    @Override
    public String toString() {
        return "ReserveForManager{" +
                "reservationId=" + reservationId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", checkinDate=" + checkinDate +
                ", checkoutDate=" + checkoutDate +
                ", roomName='" + roomName + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", roomCapacity=" + roomCapacity +
                ", roomClassName='" + roomClassName + '\'' +
                ", totalCost=" + totalCost +
                '}';
    }
}


