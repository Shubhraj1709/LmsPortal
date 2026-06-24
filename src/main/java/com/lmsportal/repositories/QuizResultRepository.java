package com.lmsportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lmsportal.models.QuizResult;

@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {
	
//	long countByStudentIdAndQuizCourseId(Long studentId, Long courseId);
//
//    long countByStudentIdAndQuizCourseIdAndPassedTrue(Long studentId, Long courseId);
	
	@Query("""
	        SELECT COUNT(qr)
	        FROM QuizResult qr
	        WHERE qr.studentId = :studentId
	          AND qr.lessonId IN (
	              SELECT l.id
	              FROM Lesson l
	              WHERE l.courseModule.course.id = :courseId
	          )
	    """)
	    long countQuizResultsByStudentAndCourse(
	            @Param("studentId") Long studentId,
	            @Param("courseId") Long courseId
	    );

	@Query("""
		    SELECT COUNT(DISTINCT qr.lessonId)
		    FROM QuizResult qr
		    WHERE qr.studentId = :studentId
		      AND qr.scorePercentage >= 50
		      AND qr.lessonId IN (
		          SELECT l.id
		          FROM Lesson l
		          WHERE l.courseModule.course.id = :courseId
		      )
		""")
		long countPassedQuizResultsByStudentAndCourse(
		        @Param("studentId") Long studentId,
		        @Param("courseId") Long courseId
		);

	    
//	    @Query("""
//	    	    SELECT COUNT(q)
//	    	    FROM Quiz q
//	    	    WHERE q.lesson.courseModule.course.id = :courseId
//	    	""")
//	    	long countTotalQuizzesByCourse(@Param("courseId") Long courseId);
	
	@Query("""
		    SELECT COUNT(q)
		    FROM Quiz q
		    WHERE q.lesson.courseModule.course.id = :courseId
		""")
		long countTotalQuizzesByCourse(Long courseId);


}
