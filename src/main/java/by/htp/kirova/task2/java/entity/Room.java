package by.htp.kirova.task2.java.entity;

import java.util.Objects;

/**
 * Represents a room of an application, providing access to room Id, name, number, capacity,
 * cost, room class id.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class Room {

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
     * The unique identification number of room class.
     */
    private long room_classes_id;


    public Room() {
    }

    public Room(long id, String name, String number, int capacity, double cost, long room_classes_id) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.capacity = capacity;
        this.cost = cost;
        this.room_classes_id = room_classes_id;
    }

    /**
     * Returns room unique identification number.
     *
     * @return java.lang.Long room unique identification number.
     */
    public long getId() {
        return id;
    }

    /**
     * Returns room name.
     *
     * @return java.lang.String room unique identification name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns physical number of room.
     *
     * @return java.lang.String physical number of room.
     */
    public String getNumber() {
        return number;
    }

    /**
     * Returns the room capacity (person).
     *
     * @return room capacity (person).
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     *  Returns cost of stay per day.
     *
     * @return java.lang.Double cost of stay per day.
     */
    public double getCost() {
        return cost;
    }

    /**
     * Returns room's class unique identification number.
     *
     * @return java.lang.Long room's class unique identification number.
     */
    public long getRoom_classes_id() {
        return room_classes_id;
    }

    /**
     * Sets room's unique identification number.
     *
     * @param id room's unique identification number.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Sets room's name.
     *
     * @param name room's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets physical number of room.
     *
     * @param number physical number of room.
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Sets the room capacity (person).
     *
     * @param capacity the room capacity (person).
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Sets cost of stay per day.
     *
     * @param cost cost of stay per day.
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Sets room's class unique identification number.
     *
     * @param room_classes_id room's class unique identification number.
     */
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
                ", room_classes_id=" + room_classes_id +
                '}';
    }
}
