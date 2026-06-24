package com.lmsportal.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lmsportal.enums.CourseLevel;


@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	private String title;


	@Column(length = 2000)
	private String description;


	private Double price;


	@Enumerated(EnumType.STRING)
	private CourseLevel level;


	@JsonIgnoreProperties("courses")
	@ManyToOne
	@JoinColumn(name = "category_id")
	private CourseCategory category;


// simple instructor reference
	private Long instructorId;

	@Column(nullable = false)
	@Builder.Default
	private Boolean published = false;
	
	@OneToMany(
		    mappedBy = "course",
		    cascade = CascadeType.ALL,
		    orphanRemoval = true
		)
		private List<Certificate> certificates = new ArrayList<>();

}

