package com.projekt.ems.Dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationDto {
    private Long id;
    private String message;
    private String status;
    private Integer type;
    private Long userId;
    private LocalDateTime dateCreated;


}
