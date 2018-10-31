package by.htp.kirova.task2.entity;


import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a role of an application, providing access to role Id, name.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class Authority implements Serializable {

    /**
     * The unique serial version identifier.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The authority name.
     */
    private String authority;

    /**
     * The unique identification name.
     */
    private String username;

    /**
     * Access to the Authority: available or deleted.
     */
    private boolean enabled;

    /**
     * Empty constructor for Authority entity class.
     */
    public Authority() {
    }

    /**
     * Constructor with all fields of the Authority class
     * as parameters.
     */
    public Authority(String authority, String username, boolean enabled) {
        this.authority = authority;
        this.username = username;
        this.enabled = enabled;
    }


    public String getAuthority() {
        return authority;
    }

    public String getUsername() {
        return username;
    }

    public boolean isEnabled() {
        return enabled;
    }




    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public void setUsername(String username) {
        this.username = username;
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

        Authority authority = (Authority) o;

        return enabled == authority.enabled &&
                Objects.equals(authority, authority.authority) &&
                Objects.equals(username, authority.username);
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = result * 31 + (authority == null ? 0 : authority.hashCode()) * result;
        result = result * 31 + (username == null ? 0 : username.hashCode()) * result;
        result = result * 31 + (enabled ? 0 : 1) * result;

        return result;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "authority='" + authority + '\'' +
                ", username='" + username + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}


