package by.htp.kirova.task2.java.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a room of an application, providing access to room Id, name, number, capacity,
 * cost, room class id.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class Room implements Serializable {

    /**
     * The unique serial version identifier.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The unique identification number of room.
     */
    private long id;

    /**
     * The room name.
     */
    private String name;

    /**
     * The physical number of room.
     */
    private String number;

    /**
     * The room capacity (person).
     */
    private int capacity;

    /**
     * Cost of stay per day.
     */
    private double cost;

    /**
     * Access to the Room: available or deleted.
     */
    private boolean enabled;

    /**
     * The unique identification number of room class.
     */
    private long room_classes_id;


    /**
     * Empty constructor for Room entity class.
     */
    public Room() {
    }


    /**
     * Constructor with all fields of the Room class
     * as parameters.
     */

    public Room(long id, String name, String number, int capacity, double cost, boolean enabled, long room_classes_id) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.capacity = capacity;
        this.cost = cost;
        this.enabled = enabled;
        this.room_classes_id = room_classes_id;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getCost() {
        return cost;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public long getRoom_classes_id() {
        return room_classes_id;
    }



    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setRoom_classes_id(long room_classes_id) {
        this.room_classes_id = room_classes_id;
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

        Room room = (Room) o;

        return id == room.id &&
                capacity == room.capacity &&
                Double.compare(room.cost, cost) == 0 &&
                enabled == room.enabled &&
                room_classes_id == room.room_classes_id &&
                Objects.equals(name, room.name) &&
                Objects.equals(number, room.number);
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = (int)(result * 31 + result * id);
        result = result * 31 + (name == null ? 0 : name.hashCode()) * result;
        result = result * 31 + (number == null ? 0 : number.hashCode()) * result;
        result = result * 31 + result * capacity;
        result = (int) (result * 31 + result * cost);
        result = result * 31 + (enabled ? 0 : 1) * result;
        result = (int)(result * 31 + result * room_classes_id);

        return result;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", capacity=" + capacity +
                ", cost=" + cost +
                ", enabled=" + enabled +
                ", room_classes_id=" + room_classes_id +
                '}';
    }
}
