package com.lmsportal.repositories;

import com.lmsportal.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface NotificationRepository extends JpaRepository<Notification, Long> {
	
    List<Notification> findBySendTo(String sendTo);

}