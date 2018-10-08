package by.htp.kirova.task2.java.entity;


import java.util.Objects;

/**
 * Represents a role of an application, providing access to role Id, name.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class Authorities {

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


    public Authorities() {
    }

    public Authorities(String authority, String username) {
        this.authority = authority;
        this.username = username;
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

        Authorities that = (Authorities) o;

        return Objects.equals(authority, that.authority) &&
                Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = result * 31 + (authority == null ? 0 : authority.hashCode()) * result;
        result = result * 31 + (username == null ? 0 : username.hashCode()) * result;
        return result;
    }

    @Override
    public String toString() {
        return "Authorities{" +
                "authority='" + authority + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}


