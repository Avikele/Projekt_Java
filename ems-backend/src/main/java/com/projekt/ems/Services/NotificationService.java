package com.projekt.ems.Services;

import com.projekt.ems.Dto.CreateNotificationDto;
import com.projekt.ems.Dto.NotificationDto;

import java.util.List;

public interface NotificationService {
    NotificationDto getNotificationById(long id);
    List<NotificationDto> getNotificationsByUserId(Long userId);
    NotificationDto createNotification(CreateNotificationDto createnotification);
    NotificationDto updateNotification(Long id,NotificationDto notification);
    void deleteNotification(Long id);
}
