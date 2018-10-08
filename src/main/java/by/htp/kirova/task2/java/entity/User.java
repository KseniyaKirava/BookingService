package by.htp.kirova.task2.java.entity;

import java.util.Objects;

/**
 * Represents a user of an application, providing access to user's Id, e-mail, password,
 * full name and access level (role).
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
 public class User{

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
     * Access to the User.
     */
    private boolean enable;


    public User() {
    }

    public User(String username, String email, String password, String first_name, String last_name,
                String middle_name, boolean enable) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.enable = enable;
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
     * Returns user's e-mail.
     *
     * @return java.lang.String user's e-mail.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns user's password.
     *
     * @return java.lang.String user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns user's first name.
     *
     * @return java.lang.String user's first name.
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * Returns user's last name.
     *
     * @return java.lang.String user's last name.
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * Returns user's middle name.
     *
     * @return java.lang.String user's middle name.
     */
    public String getMiddle_name() {
        return middle_name;
    }

    /**
     * Returns access to User
     *
     * @return access to User {@code true} if access granted, {@code false} otherwise.
     */
    public boolean isEnable() {
        return enable;
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
     * Sets user's e-mail.
     *
     * @param email user's e-mail.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets user's password.
     *
     * @param password user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets user's first name.
     *
     * @param first_name user's first name.
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Sets user's last name.
     *
     * @param last_name user's last name.
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Sets user's middle name.
     *
     * @param middle_name user's middle name.
     */
    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }



    /**
     * Set access to User
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
        User user = (User) o;

        return enable == user.enable &&
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
        result = result * 31 + (enable ? 0 : 1) * result;

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
                ", enable=" + enable +
                '}';
    }
}
