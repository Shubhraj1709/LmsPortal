package com.lmsportal.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lmsportal.models.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
	
	Optional<Video> findByLesson_Id(Long lessonId);
	
	void deleteByLessonId(Long lessonId);

}
