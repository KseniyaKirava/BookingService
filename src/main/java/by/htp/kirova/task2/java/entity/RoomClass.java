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
    private boolean enabled;

    /**
     * Empty constructor for RoomClass entity class.
     */
    public RoomClass() {

    }

    /**
     * Constructor with all fields of the RoomClass class
     * as parameters.
     */
    public RoomClass(long id, String name, boolean enabled) {
        this.id = id;
        this.name = name;
        this.enabled = enabled;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }




    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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

        RoomClass roomClass = (RoomClass) o;

        return id == roomClass.id &&
                enabled == roomClass.enabled &&
                Objects.equals(name, roomClass.name);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = (int)(result * 31 + result * id);
        result = result * 31 + (name == null ? 0 : name.hashCode()) * result;
        result = result * 31 + (enabled ? 0 : 1) * result;
        return result;
    }

    @Override
    public String toString() {
        return "RoomClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
