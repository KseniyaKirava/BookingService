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
    private boolean enabled;

    /**
     * Empty constructor for Facility entity class.
     */
    public Facility() {
    }

    /**
     * Constructor with all fields of the Facility class
     * as parameters.
     */
    public Facility(long id, String name, boolean enabled) {
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

        Facility facility = (Facility) o;

        return id == facility.id &&
                enabled == facility.enabled &&
                Objects.equals(name, facility.name);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = (int) (result * 31 + result * id);
        result = result * 31 + (name == null ? 0 : name.hashCode()) * result;
        result = result * 31 + (enabled ? 0 : 1) * result;
        return result;
    }

    @Override
    public String toString() {
        return "Facility{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
