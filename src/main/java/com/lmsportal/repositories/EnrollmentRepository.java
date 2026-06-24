package com.lmsportal.repositories;

import com.lmsportal.models.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
	

	List<Enrollment> findByCourse_Id(Long courseId);

	@Query("""
			SELECT COUNT(e) > 0 
			FROM Enrollment e 
			WHERE e.student.id = :studentId AND e.course.id = :courseId
			""")
    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);
	
    boolean existsByStudent_IdAndCourse_Id(Long studentId, Long courseId);


}