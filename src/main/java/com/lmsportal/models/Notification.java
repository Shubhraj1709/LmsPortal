package com.lmsportal.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;


@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	private String title;


	@Column(length = 2000)
	private String message;


	private String sendTo; // e.g. STUDENT, INSTRUCTOR, ADMIN or ALL


	private Instant createdAt;
}

