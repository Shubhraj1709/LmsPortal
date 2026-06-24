package com.lmsportal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lmsportal.models.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
	
    List<Quiz> findByLessonId(Long lessonId);

}
