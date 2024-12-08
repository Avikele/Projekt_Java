package com.projekt.ems.Services.impl;

import com.projekt.ems.Dto.CreateNotificationDto;
import com.projekt.ems.Dto.NotificationDto;
import com.projekt.ems.Models.Notification;
import com.projekt.ems.Models.User;
import com.projekt.ems.Repositories.NotificationRepository;
import com.projekt.ems.Repositories.UserRepository;
import com.projekt.ems.Services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationImpl(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }


    @Override
    public NotificationDto getNotificationById(long id){
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        return mapToDto(notification);
    }

    @Override
    public List<NotificationDto> getNotificationsByUserId(Long userId){
        List<Notification> notifications = notificationRepository.findByUserId(userId);
        return notifications.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public NotificationDto createNotification(CreateNotificationDto createNotificationDto) {
        User user = userRepository.findById(createNotificationDto.getUserId())
                .orElseThrow(()-> new RuntimeException("UsernotFound"));

        Notification notification = new Notification();
        notification.setMessage(createNotificationDto.getMessage());
        notification.setStatus(createNotificationDto.getStatus());
        notification.setType(createNotificationDto.getType());
        notification.setUser(user);
        notification.setDateCreated(LocalDateTime.now());

        notification = notificationRepository.save(notification);
        return mapToDto(notification);
    }


    @Override
    public NotificationDto updateNotification(Long id, NotificationDto notificationDto) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Notification not found with id"));

        notification.setMessage(notificationDto.getMessage());
        notification.setStatus(notificationDto.getStatus());
        notification.setType(notificationDto.getType());

        notification = notificationRepository.save(notification);
        return mapToDto(notification);
    }

    @Override
    public void deleteNotification(Long id) {
        if(!notificationRepository.existsById(id)){
            throw new RuntimeException("Notification not found with id " + id);
        }
        notificationRepository.deleteById(id);
    }


    private NotificationDto mapToDto(Notification notification) {
        return NotificationDto.builder()
                .id(notification.getId())
                .message(notification.getMessage())
                .status(notification.getStatus())
                .type(notification.getType())
                .dateCreated(notification.getDateCreated())
                .userId(notification.getUser().getId())
                .build();
    }
}
