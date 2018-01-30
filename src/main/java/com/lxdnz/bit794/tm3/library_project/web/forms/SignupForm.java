package com.lxdnz.bit794.tm3.library_project.web.forms;

import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.User;
import org.hibernate.validator.constraints.NotBlank;

public class SignupForm {

    private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
    private String username;

    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
    private String password;

    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
    private String firstName;

    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
    private String lastName;

    private String streetAddress;
    private String town;
    private String contactPhoneNumber;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public User createUser() {
        // Set up new user
        User user = new User();
        user.setUsername(getUsername());
        user.setPassword(getPassword());
        user.setFirstName(getFirstName());
        user.setLastName(getLastName());
        user.setStreetAddress(getStreetAddress());
        user.setTown(getTown());
        user.setContactPhoneNumber(getContactPhoneNumber());
        return user;
    }
}
