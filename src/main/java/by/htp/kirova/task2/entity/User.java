package by.htp.kirova.task2.entity;

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
    private String firstName;

    /**
     * The user's last name.
     */
    private String lastName;

    /**
     * The user's middle name.
     */
    private String middleName;

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
    public User(String username, String email, String password, String firstName, String lastName,
                String middleName, boolean enabled) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(middleName, user.middleName);
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = result * 31 + (username == null ? 0 : username.hashCode()) * result;
        result = result * 31 + (email == null ? 0 : email.hashCode()) * result;
        result = result * 31 + (password == null ? 0 : password.hashCode()) * result;
        result = result * 31 + (firstName == null ? 0 : firstName.hashCode()) * result;
        result = result * 31 + (lastName == null ? 0 : lastName.hashCode()) * result;
        result = result * 31 + (middleName == null ? 0 : middleName.hashCode()) * result;
        result = result * 31 + (enabled ? 0 : 1) * result;

        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
