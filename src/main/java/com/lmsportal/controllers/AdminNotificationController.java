package com.lmsportal.controllers;

import com.lmsportal.dto.NotificationDTO;
import com.lmsportal.models.Notification;
import com.lmsportal.services.AdminNotificationService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin")
public class AdminNotificationController {


	private final AdminNotificationService adminNotificationService;


	public AdminNotificationController(AdminNotificationService adminNotificationService) {
		this.adminNotificationService = adminNotificationService;
	}


	@PostMapping("/all")
	public ResponseEntity<Notification> sendAll(@RequestBody NotificationDTO dto) {
		return ResponseEntity.ok(adminNotificationService.sendToAll(dto.getTitle(), dto.getMessage()));
	}


	@PostMapping("/role")
	public ResponseEntity<Notification> sendRole(@RequestBody NotificationDTO dto) {
		return ResponseEntity.ok(adminNotificationService.sendToRole(dto.getRole(), dto.getTitle(), dto.getMessage()));
	}
	
	@PostMapping("/notifications")
    public ResponseEntity<Notification> sendNotification(@RequestBody NotificationDTO dto) {
        return ResponseEntity.ok(adminNotificationService.send(dto));
    }
	
	@GetMapping("/notifications")
	public ResponseEntity<List<Notification>> getAllNotifications() {
	    return ResponseEntity.ok(adminNotificationService.getAllNotifications());
	}

	@GetMapping("/notifications/{sendTo}")
	public ResponseEntity<List<Notification>> getNotificationsBySendTo(@PathVariable String sendTo) {
	    return ResponseEntity.ok(adminNotificationService.getNotificationsBySendTo(sendTo));
	}


}


