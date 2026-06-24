package com.lmsportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.lmsportal.enums.CourseLevel;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
	private Long id;
	private String title;
	private String description;
	private Double price;
	private CourseLevel level;
	private Long categoryId;
	private Long instructorId;
	private Boolean published;
}