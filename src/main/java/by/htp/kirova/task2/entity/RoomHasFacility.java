package by.htp.kirova.task2.entity;


import java.io.Serializable;

/**
 * Represents a room has facilities of an application, providing access to room id, facilities id, count.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class RoomHasFacility implements Serializable {

    /**
     * The unique serial version identifier.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The unique identification number of room.
     */
    private long roomsId;

    /**
     * The unique identification number of facility.
     */
    private long facilitiesId;

    /**
     * The count of facilities.
     */
    private int count;

    /**
     * Access to the Room-has-Facility: available or deleted.
     */
    private boolean enabled;

    /**
     * Empty constructor for RoomHasFacility entity class.
     */
    public RoomHasFacility() {
    }

    /**
     * Constructor with all fields of the RoomHasFacility class
     * as parameters.
     */
    public RoomHasFacility(long roomsId, long facilitiesId, int count, boolean enabled) {
        this.roomsId = roomsId;
        this.facilitiesId = facilitiesId;
        this.count = count;
        this.enabled = enabled;
    }


    public long getRoomsId() {
        return roomsId;
    }

    public long getFacilitiesId() {
        return facilitiesId;
    }

    public int getCount() {
        return count;
    }

    public boolean isEnabled() {
        return enabled;
    }



    public void setRoomsId(long roomsId) {
        this.roomsId = roomsId;
    }

    public void setFacilitiesId(long facilitiesId) {
        this.facilitiesId = facilitiesId;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

        RoomHasFacility that = (RoomHasFacility) o;

        return roomsId == that.roomsId &&
                facilitiesId == that.facilitiesId &&
                count == that.count &&
                enabled == that.enabled;
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = (int)(result * 31 + result * roomsId);
        result = (int)(result * 31 + result * facilitiesId);
        result = result * 31 + result * count;
        result = result * 31 + (enabled ? 0 : 1) * result;
        return result;
    }

    @Override
    public String toString() {
        return "RoomHasFacility{" +
                "roomsId=" + roomsId +
                ", facilitiesId=" + facilitiesId +
                ", count=" + count +
                ", enabled=" + enabled +
                '}';
    }
}
