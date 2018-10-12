package by.htp.kirova.task2.java.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a room class of an application, providing access to room class Id, name.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class RoomClass implements Serializable {

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

    /**
     * Access to the Room Class: available or deleted.
     */
    private boolean enable;

    public RoomClass() {

    }

    public RoomClass(long id, String name, boolean enable) {
        this.id = id;
        this.name = name;
        this.enable = enable;
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
     * Returns access to Room Class
     *
     * @return access to Room Class {@code true} if access granted, {@code false} otherwise.
     */
    public boolean isEnable() {
        return enable;
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
     * @param name request's room's class name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set access to Room Class
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

        RoomClass roomClass = (RoomClass) o;

        return id == roomClass.id &&
                enable == roomClass.enable &&
                Objects.equals(name, roomClass.name);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = (int)(result * 31 + result * id);
        result = result * 31 + (name == null ? 0 : name.hashCode()) * result;
        result = result * 31 + (enable ? 0 : 1) * result;
        return result;
    }

    @Override
    public String toString() {
        return "RoomClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", enable=" + enable +
                '}';
    }
}
