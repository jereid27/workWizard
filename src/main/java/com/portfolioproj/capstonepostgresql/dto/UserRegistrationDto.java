package com.portfolioproj.capstonepostgresql.dto;

import org.springframework.security.core.userdetails.UserDetails;

public class UserRegistrationDto {

    private String username;
    private String password;
    private String fullname;

    public UserRegistrationDto(String username, String password, String fullname) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
    }

    public UserRegistrationDto() {
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


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

}
