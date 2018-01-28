package com.lxdnz.bit794.tm3.library_project.web.signup;

import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.User;
import org.hibernate.validator.constraints.NotBlank;

public class SignupForm {

    private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
    private String username;

    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
    private String password;

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

    public User createUser() {
        // Set up new user
        User user = new User();
        user.setUsername(getUsername());
        user.setPassword(getPassword());
        return user;
    }
}
