package by.htp.kirova.task2.java.entity;

import java.util.Objects;

/**
 * Represents a room class of an application, providing access to room class Id, name.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class RoomClass {

    /**
     * The unique serial version identifier.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The unique identification number of room class.
     */
    private long id;

    /**
     * The name of class room.
     */
    private String name;

    public RoomClass() {

    }

    public RoomClass(long id, String name) {
        this.id = id;
        this.name = name;
    }


    /**
     * Returns room's class unique identification number.
     *
     * @return java.lang.Long room's class unique identification number.
     */
    public long getId() {
        return id;
    }

    /**
     * Returns room's class name.
     *
     * @return java.lang.String room's class name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets room's class unique identification number.
     *
     * @param id request's room's class unique identification number.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Sets room's class name.
     *
     * @param id request's room's class name.
     */
    public void setName(String name) {
        this.name = name;
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

        RoomClass roomClass = (RoomClass) o;

        return id == roomClass.id &&
                Objects.equals(name, roomClass.name);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = (int)(result * 31 + result * id);
        result = result * 31 + (name == null ? 0 : name.hashCode()) * result;
        return result;
    }

    @Override
    public String toString() {
        return "RoomClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
