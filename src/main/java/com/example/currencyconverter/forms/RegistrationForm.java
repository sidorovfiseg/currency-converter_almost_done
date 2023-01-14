package com.example.currencyconverter.forms;

import com.example.currencyconverter.entities.User;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.security.crypto.password.PasswordEncoder;


public class RegistrationForm {

    @NotEmpty(message = "username cannot be empty.")
    private String username;

    @NotEmpty(message = "password cannot be empty.")
    private String password;

    @NotEmpty(message = "confirm password cannot be empty.")
    private String confirmPassword;

    public RegistrationForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public RegistrationForm() {

    }

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                username,
                passwordEncoder.encode(password)
        );
    }
}
