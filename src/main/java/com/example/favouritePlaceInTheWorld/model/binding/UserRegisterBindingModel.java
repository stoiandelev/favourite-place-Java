package com.example.favouritePlaceInTheWorld.model.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegisterBindingModel {

    @Size(min = 2, message = "Full name must be min 2 symbols!")
    private String fullName;

    @Size(min = 2, message = "Username must be min 2 symbols!")
    private String username;

    @Email(message = "Enter valid e-mail!")
    @NotBlank(message = "Can not be empty!")
    private String email;

    @Size(min = 4, message = "Password must be min 4 symbols!")
    private String password;


    public UserRegisterBindingModel() {
    }

    public String getFullName() {
        return fullName;
    }

    public UserRegisterBindingModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
