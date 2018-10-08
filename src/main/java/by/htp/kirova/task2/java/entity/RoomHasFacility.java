package by.htp.kirova.task2.java.entity;


/**
 * Represents a room has facilities of an application, providing access to room id, facilities id, count.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class RoomHasFacility{

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

    public RoomHasFacility() {
    }

    public RoomHasFacility(long rooms_id, long facilities_id, int count) {
        this.rooms_id = rooms_id;
        this.facilities_id = facilities_id;
        this.count = count;
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

    public int getCount() {
        return count;
    }

    public void setRooms_id(long rooms_id) {
        this.rooms_id = rooms_id;
    }

    public void setFacilities_id(long facilities_id) {
        this.facilities_id = facilities_id;
    }

    public void setCount(int count) {
        this.count = count;
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
                count == that.count;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = (int)(result * 31 + result * rooms_id);
        result = (int)(result * 31 + result * facilities_id);
        result = result * 31 + result * count;
        return result;
    }

    @Override
    public String toString() {
        return "RoomHasFacility{" +
                "rooms_id=" + rooms_id +
                ", facilities_id=" + facilities_id +
                ", count=" + count +
                '}';
    }
}
