package com.lmsportal.controllers;

import com.lmsportal.dto.CategoryDTO;
import com.lmsportal.dto.CourseDTO;
import com.lmsportal.models.Course;
import com.lmsportal.models.CourseCategory;
import com.lmsportal.services.AdminCourseService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin")
public class AdminCourseController {


	private final AdminCourseService adminCourseService;


	public AdminCourseController(AdminCourseService adminCourseService) {
		this.adminCourseService = adminCourseService;
	}


	@PostMapping("/categories")
	public ResponseEntity<CourseCategory> createCategory(@RequestBody CategoryDTO dto) {
		return ResponseEntity.ok(adminCourseService.createCategory(dto));
	}


	@PostMapping("/courses")
	public ResponseEntity<Course> createCourse(@RequestBody CourseDTO dto) {
		return ResponseEntity.ok(adminCourseService.createCourse(dto));
	}
	
	// ---------------- CATEGORY ----------------

//    @GetMapping("/categories")
//    public ResponseEntity<List<CourseCategory>> getAllCategories() {
//        return ResponseEntity.ok(adminCourseService.getAllCategories());
//    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CourseCategory> updateCategory(@PathVariable Long id,
                                                         @RequestBody CategoryDTO dto) {
        return ResponseEntity.ok(adminCourseService.updateCategory(id, dto));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        adminCourseService.deleteCategory(id);
        return ResponseEntity.ok("Category and related data deleted successfully.");
    }

    // ---------------- COURSES ----------------

//    @GetMapping("/courses")
//    public ResponseEntity<List<Course>> getAllCourses() {
//        return ResponseEntity.ok(adminCourseService.getAllCourses());
//    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        return ResponseEntity.ok(adminCourseService.getCourse(id));
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id,
                                               @RequestBody CourseDTO dto) {
        return ResponseEntity.ok(adminCourseService.updateCourse(id, dto));
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        adminCourseService.deleteCourse(id);
        return ResponseEntity.ok("Course deleted.");
    }
}
	



