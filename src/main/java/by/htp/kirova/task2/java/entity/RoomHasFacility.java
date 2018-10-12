package by.htp.kirova.task2.java.entity;


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
    private long rooms_id;

    /**
     * The unique identification number of facility.
     */
    private long facilities_id;

    /**
     * The count of facilities.
     */
    private int count;

    /**
     * Access to the Room-has-Facility: available or deleted.
     */
    private boolean enable;


    public RoomHasFacility() {
    }

    public RoomHasFacility(long rooms_id, long facilities_id, int count, boolean enable) {
        this.rooms_id = rooms_id;
        this.facilities_id = facilities_id;
        this.count = count;
        this.enable = enable;
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
     * Returns facility unique identification number.
     *
     * @return java.lang.Long facility unique identification number.
     */
    public long getFacilities_id() {
        return facilities_id;
    }

    /**
     * Returns facility count in the room.
     *
     * @return facility facility count in the room.
     */
    public int getCount() {
        return count;
    }

    /**
     * Returns access to Room-has-Faciity
     *
     * @return access to Room-has-Faciity {@code true} if access granted, {@code false} otherwise.
     */
    public boolean isEnable() {
        return enable;
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
     * Sets facility's unique identification number.
     *
     * @param facilities_id facility unique identification number.
     */
    public void setFacilities_id(long facilities_id) {
        this.facilities_id = facilities_id;
    }

    /**
     * Sets facility's count in the room.
     *
     * @param count facility count.
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Set access to Room-has-Facility
     *
     * @param enable boolean access state.
     */
    public void setEnable(boolean enable) {
        this.enable = enable;
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

        return rooms_id == that.rooms_id &&
                facilities_id == that.facilities_id &&
                count == that.count &&
                enable == that.enable;
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = (int)(result * 31 + result * rooms_id);
        result = (int)(result * 31 + result * facilities_id);
        result = result * 31 + result * count;
        result = result * 31 + (enable ? 0 : 1) * result;
        return result;
    }

    @Override
    public String toString() {
        return "RoomHasFacility{" +
                "rooms_id=" + rooms_id +
                ", facilities_id=" + facilities_id +
                ", count=" + count +
                ", enable=" + enable +
                '}';
    }
}
