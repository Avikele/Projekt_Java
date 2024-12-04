package com.projekt.ems.Dto;

import com.projekt.ems.Models.User;
import lombok.Data;


@Data
public class UserDto {

    private long id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String surName;


    public UserDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.surName = user.getSurName();
        this.username = user.getUsername();
        this.password = null;
    }

    public UserDto() {
        this.id = 0;
        this.email = null;
        this.firstName = null;
        this.surName = null;
        this.username = null;
        this.password = null;
    }
}
