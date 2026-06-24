package com.lmsportal.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lmsportal.dto.QuizSubmitDTO;
import com.lmsportal.dto.StudentCourseDTO;
import com.lmsportal.dto.StudentProgressDTO;
import com.lmsportal.models.Assignment;
import com.lmsportal.models.Lesson;
import com.lmsportal.models.Quiz;
import com.lmsportal.models.QuizResult;
import com.lmsportal.models.Reply;
import com.lmsportal.models.AssignmentSubmission;
import com.lmsportal.models.Course;
import com.lmsportal.models.CourseCategory;
import com.lmsportal.models.User;
import com.lmsportal.models.Video;

import lombok.RequiredArgsConstructor;

import com.lmsportal.models.CourseModule;
import com.lmsportal.models.Discussion;
import com.lmsportal.models.Enrollment;
import com.lmsportal.services.AdminCourseService;
import com.lmsportal.services.InstructorService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LandingController {

	    private final InstructorService service;

		private final AdminCourseService adminCourseService;


	    /** GET ALL VIDEOS (Not filtered by lesson) */
	    @GetMapping("/videos")
	    public ResponseEntity<List<Video>> getAllVideos() {
	        return ResponseEntity.ok(service.getAllVideos());
	    }

	    @GetMapping("/categories")
	    public ResponseEntity<List<CourseCategory>> getAllCategories() {
	        return ResponseEntity.ok(adminCourseService.getAllCategories());
	    }

	    @GetMapping("/courses")
	    public ResponseEntity<List<Course>> getAllCourses() {
	        return ResponseEntity.ok(adminCourseService.getAllCourses());
	    }
	    
	    @GetMapping("/student/courses/{studentId}")
	    public ResponseEntity<List<StudentCourseDTO>> getCoursesForStudent(
	            @PathVariable Long studentId
	    ) {
	        return ResponseEntity.ok(
	                adminCourseService.getCoursesForStudent(studentId)
	        );
	    }


	    
	    
}
