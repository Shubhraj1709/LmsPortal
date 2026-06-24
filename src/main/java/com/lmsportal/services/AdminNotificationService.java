package com.lmsportal.services;

import com.lmsportal.dto.NotificationDTO;
import com.lmsportal.models.Notification;
import com.lmsportal.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class AdminNotificationService {

    private final NotificationRepository repo;

    public AdminNotificationService(NotificationRepository repo) {
        this.repo = repo;
    }

    public Notification send(NotificationDTO dto) {

        Notification n = Notification.builder()
                .title(dto.getTitle())
                .message(dto.getMessage())
                .sendTo(dto.getSendTo())     // FIXED
                .createdAt(Instant.now())
                .build();

        return repo.save(n);
    }

    public Notification sendToAll(String title, String message) {
        Notification n = Notification.builder()
                .title(title)
                .message(message)
                .sendTo("ALL")                // FIXED
                .createdAt(Instant.now())
                .build();

        return repo.save(n);
    }

    public Notification sendToRole(String role, String title, String message) {
        Notification n = Notification.builder()
                .title(title)
                .message(message)
                .sendTo(role)                 // FIXED
                .createdAt(Instant.now())
                .build();

        return repo.save(n);
    }
    
    public List<Notification> getAllNotifications() {
        return repo.findAll();
    }

    public List<Notification> getNotificationsBySendTo(String sendTo) {
        return repo.findBySendTo(sendTo);
    }

}
