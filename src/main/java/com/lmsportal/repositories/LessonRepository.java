package com.lmsportal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lmsportal.models.Lesson;
import com.lmsportal.models.Quiz;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
	
	List<Lesson> findByCourseModule_Id(Long moduleId);
	

    List<Lesson> findByCourseModuleCourseId(Long courseId);

}
