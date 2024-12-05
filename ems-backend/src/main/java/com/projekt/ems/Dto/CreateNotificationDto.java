package com.projekt.ems.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateNotificationDto {
    private String message;
    private String status;
    private Integer type;
    private Long userId;
}
