package com.lmsportal.dto;

import lombok.Data;


@Data
public class NotificationDTO {
	private String title;
	private String message;
	private String role; // STUDENT | INSTRUCTOR | ADMIN | ALL
    private String sendTo; // ALL | STUDENT | INSTRUCTOR | ADMIN

}