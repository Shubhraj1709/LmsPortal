package com.lmsportal.dto;

import lombok.Data;


@Data
public class UserStatusDTO {
	private Long userId;
	private boolean active;
}