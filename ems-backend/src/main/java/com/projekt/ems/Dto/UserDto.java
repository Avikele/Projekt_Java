package com.projekt.ems.Dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserDto {

    private long id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String surName;
    private Integer status;
    private Integer privilages;
    private LocalDate creationDate;


}
