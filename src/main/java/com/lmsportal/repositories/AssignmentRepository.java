package com.lmsportal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lmsportal.models.Assignment;
import com.lmsportal.models.Lesson;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
	
	List<Assignment> findByLesson(Lesson lesson);

	List<Assignment> findByLessonIn(List<Lesson> lessons);

}
