package by.htp.kirova.task2.java.entity;


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
    private boolean enable;


    public Authority() {
    }

    public Authority(String authority, String username, boolean enable) {
        this.authority = authority;
        this.username = username;
        this.enable = enable;
    }

    /**
     * Returns authority name.
     *
     * @return java.lang.String authority name.
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * Returns user's unique identification name.
     *
     * @return java.lang.String user's unique identification name.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns access to Authority
     *
     * @return access to Authority {@code true} if access granted, {@code false} otherwise.
     */
    public boolean isEnable() {
        return enable;
    }

    /**
     * Sets authority name.
     *
     * @param authority authority name.
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    /**
     * Sets user's unique identification name.
     *
     * @param username user's unique identification name.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Set access to Authority
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

        Authority authority = (Authority) o;

        return enable == authority.enable &&
                Objects.equals(authority, authority.authority) &&
                Objects.equals(username, authority.username);
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = result * 31 + (authority == null ? 0 : authority.hashCode()) * result;
        result = result * 31 + (username == null ? 0 : username.hashCode()) * result;
        result = result * 31 + (enable ? 0 : 1) * result;

        return result;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "authority='" + authority + '\'' +
                ", username='" + username + '\'' +
                ", enable=" + enable +
                '}';
    }
}


