package com.lmsportal.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "course_categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@Column(unique = true, nullable = false)
	private String name;


	private String description;
	
	@OneToMany(
	        mappedBy = "category",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	@JsonManagedReference
	    private List<Course> courses = new ArrayList<>();
}
