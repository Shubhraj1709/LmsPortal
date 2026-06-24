package com.lmsportal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lmsportal.models.CourseModule;

@Repository
public interface ModuleRepository extends JpaRepository<CourseModule, Long> {
	
	List<CourseModule> findByCourse_Id(Long courseId);

}
