package com.lmsportal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lmsportal.enums.RoleEnum;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(
	    name = "users",
	    uniqueConstraints = {
	        @UniqueConstraint(columnNames = "email")
	    }
	)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	private String name;


	@Column(unique = true, nullable = false)
	private String email;


	@Column(nullable = false)
	@JsonIgnore
	private String password;


	@Enumerated(EnumType.STRING)
	private RoleEnum role;

	@Column(nullable = false)
	@Builder.Default
	private Boolean active = true;

	@Builder.Default
	private Boolean approved = false; // for instructors needing approval
}