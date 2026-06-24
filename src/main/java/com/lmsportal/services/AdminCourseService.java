package com.lmsportal.services;

import com.lmsportal.dto.CategoryDTO;
import com.lmsportal.dto.CourseDTO;
import com.lmsportal.models.Course;
import com.lmsportal.dto.StudentCourseDTO;

//import com.lmsportal.dto.CourseDTO;
import com.lmsportal.dto.StudentCourseDTO;
import com.lmsportal.models.Course;
import com.lmsportal.models.CourseCategory;
import com.lmsportal.repositories.CourseCategoryRepository;
import com.lmsportal.repositories.CourseRepository;
import com.lmsportal.repositories.EnrollmentRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AdminCourseService {


	private final CourseRepository courseRepository;
	private final CourseCategoryRepository categoryRepository;

@Autowired
//	private EnrollmentRepository enrollmentRepo;

	private final EnrollmentRepository enrollmentRepo;


	public AdminCourseService(CourseRepository courseRepository, CourseCategoryRepository categoryRepository,EnrollmentRepository enrollmentRepo
) {
			this.courseRepository = courseRepository;
			this.categoryRepository = categoryRepository;
			this.enrollmentRepo = enrollmentRepo;
	}


	@Transactional
	public Course createCourse(CourseDTO dto) {
			CourseCategory category = null;
			if (dto.getCategoryId() != null) {
					category = categoryRepository.findById(dto.getCategoryId()).orElse(null);
				}


			Course course = Course.builder()
					.title(dto.getTitle())
					.description(dto.getDescription())
					.price(dto.getPrice())
					.level(dto.getLevel())
					.category(category)
					.instructorId(dto.getInstructorId())
					//.published(dto.getPublished())
					.published(dto.getPublished() != null ? dto.getPublished() : false)

					.build();


			return courseRepository.save(course);
	}


	public CourseCategory createCategory(com.lmsportal.dto.CategoryDTO dto) {
		CourseCategory c = CourseCategory.builder().name(dto.getName()).description(dto.getDescription()).build();
		return categoryRepository.save(c);
	}
	
	
	// -------- CATEGORY ---------

    public List<CourseCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    public CourseCategory updateCategory(Long id, CategoryDTO dto) {
        CourseCategory c = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        c.setName(dto.getName());
        c.setDescription(dto.getDescription());

        return categoryRepository.save(c);
    }

//    @Transactional
//    public void deleteCategory(Long id) {
//        CourseCategory category = categoryRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Category not found"));
//
//        categoryRepository.delete(category);
//    }
    
    @Transactional
    public void deleteCategory(Long id) {

        CourseCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // 🔴 STEP 1: Delete certificates of each course
        for (Course course : category.getCourses()) {
            course.getCertificates().clear(); // orphanRemoval → delete certificates
        }

        // 🔴 STEP 2: Delete courses
        category.getCourses().clear(); // orphanRemoval → delete courses

        // 🔴 STEP 3: Delete category
        categoryRepository.delete(category);
    }



    // -------- COURSES ---------

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourse(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public Course updateCourse(Long id, CourseDTO dto) {
        Course c = getCourse(id);

        c.setTitle(dto.getTitle());
        c.setDescription(dto.getDescription());
        c.setPrice(dto.getPrice());
        c.setLevel(dto.getLevel());

        return courseRepository.save(c);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
	
    public List<StudentCourseDTO> getCoursesForStudent(Long studentId) {

        List<Course> courses = courseRepository.findAll();

        return courses.stream().map(course -> {

            boolean enrolled =
                    enrollmentRepo.existsByStudent_IdAndCourse_Id(
                            studentId,
                            course.getId()
                    );

            StudentCourseDTO dto = new StudentCourseDTO();
            dto.setId(course.getId());
            dto.setTitle(course.getTitle());
            dto.setPrice(course.getPrice());
            dto.setEnrolled(enrolled);

            return dto;
        }).toList();
    }



}
