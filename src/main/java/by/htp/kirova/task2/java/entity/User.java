package by.htp.kirova.task2.java.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a user of an application, providing access to user's Id, e-mail, password,
 * full name and access level (role).
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
 public class User implements Serializable {

    /**
     * The unique serial version identifier.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The unique identification name.
     */
    private String username;

    /**
     * The user's e-mail.
     */
    private String email;

    /**
     * The user's password.
     */
    private String password;

    /**
     * The user's first name.
     */
    private String first_name;

    /**
     * The user's last name.
     */
    private String last_name;

    /**
     * The user's middle name.
     */
    private String middle_name;

    /**
     * Access to the User: available or deleted.
     */
    private boolean enabled;

    /**
     * Empty constructor for User entity class.
     */
    public User() {
    }

    /**
     * Constructor with all fields of the User class
     * as parameters.
     */
    public User(String username, String email, String password, String first_name, String last_name,
                String middle_name, boolean enabled) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.enabled = enabled;
    }


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public boolean isEnabled() {
        return enabled;
    }




    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
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

        User user = (User) o;

        return enabled == user.enabled &&
                Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(first_name, user.first_name) &&
                Objects.equals(last_name, user.last_name) &&
                Objects.equals(middle_name, user.middle_name);
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = result * 31 + (username == null ? 0 : username.hashCode()) * result;
        result = result * 31 + (email == null ? 0 : email.hashCode()) * result;
        result = result * 31 + (password == null ? 0 : password.hashCode()) * result;
        result = result * 31 + (first_name == null ? 0 : first_name.hashCode()) * result;
        result = result * 31 + (last_name == null ? 0 : last_name.hashCode()) * result;
        result = result * 31 + (middle_name == null ? 0 : middle_name.hashCode()) * result;
        result = result * 31 + (enabled ? 0 : 1) * result;

        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
