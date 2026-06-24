package com.lmsportal.repositories;

import com.lmsportal.models.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Long> {
	
	Optional<CourseCategory> findByName(String name);

}
