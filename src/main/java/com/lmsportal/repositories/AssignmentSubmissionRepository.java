package com.lmsportal.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lmsportal.models.AssignmentSubmission;

@Repository
public interface AssignmentSubmissionRepository extends JpaRepository<AssignmentSubmission, Long> {
	
	// total assignments in a course
//    @Query("""
//        SELECT COUNT(DISTINCT a.id)
//        FROM Assignment a
//        WHERE a.lesson.courseModule.course.id = :courseId
//    """)
//    long countAssignmentsByCourse(
//            @Param("courseId") Long courseId
//    );
//
//    // assignments submitted by a student for a course
//    @Query("""
//        SELECT COUNT(DISTINCT asg.assignment.id)
//        FROM AssignmentSubmission asg
//        WHERE asg.student.id = :studentId
//          AND asg.assignment.lesson.courseModule.course.id = :courseId
//    """)
//    long countSubmittedAssignmentsByStudentAndCourse(
//            @Param("studentId") Long studentId,
//            @Param("courseId") Long courseId
//    );
    
	@Query("""
			SELECT DISTINCT a.id
			FROM Assignment a
			JOIN a.lesson l
			JOIN l.courseModule m
			JOIN m.course c
			WHERE c.id = :courseId
			""")
			Set<Long> findAssignmentIdsByCourse(Long courseId);


	@Query("""
			SELECT DISTINCT s.assignment.id
			FROM AssignmentSubmission s
			JOIN s.assignment a
			JOIN a.lesson l
			JOIN l.courseModule m
			JOIN m.course c
			WHERE s.student.id = :studentId
			AND c.id = :courseId
			""")
			Set<Long> findSubmittedAssignmentIds(Long studentId, Long courseId);


  
    Optional<AssignmentSubmission>
    findByAssignment_IdAndStudent_Id(Long assignmentId, Long studentId);

    @Modifying
    @Query("""
        UPDATE AssignmentSubmission s
        SET s.fileUrl = :fileUrl,
            s.submittedAt = :submittedAt
        WHERE s.assignment.id = :assignmentId
          AND s.student.id = :studentId
    """)
    int updateSubmission(
        Long assignmentId,
        Long studentId,
        String fileUrl,
        LocalDateTime submittedAt
    );
    
    List<AssignmentSubmission> findByStudent_Id(Long studentId);
    
 // Optional: submissions of a student for a lesson
    @Query("""
        SELECT s
        FROM AssignmentSubmission s
        WHERE s.student.id = :studentId
          AND s.assignment.lesson.id = :lessonId
    """)
    List<AssignmentSubmission> findByStudentAndLesson(
            Long studentId,
            Long lessonId
    );

   
    
}
