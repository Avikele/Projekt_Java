package com.projekt.ems.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
    private long Id;
    private String username;
    private String password;
    private int privilages;
}
