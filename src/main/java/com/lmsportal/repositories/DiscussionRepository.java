package com.lmsportal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lmsportal.models.Discussion;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, Long> {
    List<Discussion> findByCourseId(Long courseId);
    
//    List<Discussion> findByCourse_Id(Long courseId);
    
    List<Discussion> findAll();

    
    @Query("""
    	    SELECT DISTINCT d
    	    FROM Discussion d
    	    LEFT JOIN FETCH d.replies r
    	    WHERE d.course.id = :courseId
    	""")
    	List<Discussion> findDiscussionsWithReplies(@Param("courseId") Long courseId);


}

