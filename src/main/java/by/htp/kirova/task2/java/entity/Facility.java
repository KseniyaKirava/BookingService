package by.htp.kirova.task2.java.entity;

import java.util.Objects;

/**
 * Represents a facility of an application, providing access to facilities Id, name.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class Facility {

    /**
     * The unique serial version identifier.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The unique identification number of facility.
     */
    private long id;

    /**
     * The facilities name.
     */
    private String name;

    public Facility() {
    }

    public Facility(long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns facility unique identification number.
     *
     * @return java.lang.Long facility unique identification number.
     */
    public long getId() {
        return id;
    }

    /**
     * Returns facilities name.
     *
     * @return java.lang.String facilities name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets facility's unique identification number.
     *
     * @param id facility unique identification number.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Sets facility'sname.
     *
     * @param name facility name.
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
        Facility facility = (Facility) o;
        return id == facility.id &&
                Objects.equals(name, facility.name);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = (int) (result * 31 + result * id);
        result = result * 31 + (name == null ? 0 : name.hashCode()) * result;
        return result;
    }

    @Override
    public String toString() {
        return "Facility{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
