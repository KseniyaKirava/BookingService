package by.htp.kirova.task2.java.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a facility of an application, providing access to facilities Id, name.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class Facility implements Serializable {

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

    /**
     * Access to the Facility: available or deleted.
     */
    private boolean enable;

    public Facility() {
    }

    public Facility(long id, String name, boolean enable) {
        this.id = id;
        this.name = name;
        this.enable = enable;
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
     * Returns access to Facility
     *
     * @return access to Facility {@code true} if access granted, {@code false} otherwise.
     */
    public boolean isEnable() {
        return enable;
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

    /**
     * Set access to Facility
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

        Facility facility = (Facility) o;

        return id == facility.id &&
                enable == facility.enable &&
                Objects.equals(name, facility.name);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = (int) (result * 31 + result * id);
        result = result * 31 + (name == null ? 0 : name.hashCode()) * result;
        result = result * 31 + (enable ? 0 : 1) * result;
        return result;
    }

    @Override
    public String toString() {
        return "Facility{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", enable=" + enable +
                '}';
    }
}
