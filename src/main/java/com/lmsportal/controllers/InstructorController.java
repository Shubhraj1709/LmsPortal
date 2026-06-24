package com.lmsportal.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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

import com.lmsportal.dto.DiscussionResponseDTO;
import com.lmsportal.dto.EnrollmentRequest;
import com.lmsportal.dto.QuizSubmitDTO;
import com.lmsportal.dto.StudentProgressDTO;
import com.lmsportal.models.Assignment;
import com.lmsportal.models.Lesson;
import com.lmsportal.models.Quiz;
import com.lmsportal.models.QuizResult;
import com.lmsportal.models.Reply;
import com.lmsportal.models.AssignmentSubmission;
import com.lmsportal.models.User;
import com.lmsportal.models.Video;

import lombok.RequiredArgsConstructor;

import com.lmsportal.models.CourseModule;
import com.lmsportal.models.Discussion;
import com.lmsportal.models.Enrollment;
import com.lmsportal.services.InstructorService;

@RestController
@RequestMapping("/api/instructor")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService service;

    /** ADD MODULE */
    @PostMapping("/course/{courseId}/module")
    public ResponseEntity<CourseModule> addModule(@PathVariable Long courseId,
                                            @RequestBody CourseModule courseModule) {
        return ResponseEntity.ok(service.addModule(courseId, courseModule));
    }
    
    @GetMapping("/course/{courseId}/modules")
    public ResponseEntity<List<CourseModule>> getModules(@PathVariable Long courseId) {
        return ResponseEntity.ok(service.getModules(courseId));
    }


    /** ADD LESSON */
    @PostMapping("/module/{moduleId}/lesson")
    public ResponseEntity<Lesson> addLesson(@PathVariable Long moduleId,
                                            @RequestBody Lesson lesson) {
        return ResponseEntity.ok(service.addLesson(moduleId, lesson));
    }
    
    @GetMapping("/module/{moduleId}/lessons")
    public ResponseEntity<List<Lesson>> getLessons(@PathVariable Long moduleId) {
        return ResponseEntity.ok(service.getLessons(moduleId));
    }


    /** ADD VIDEO */
    
    @PostMapping(
    	    value = "/lesson/{lessonId}/video",
    	    consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    	)
    	@PreAuthorize("hasRole('INSTRUCTOR')")
    	public ResponseEntity<Video> addVideo(
    	        @PathVariable Long lessonId,
    	        @RequestParam String title,
    	        @RequestParam Integer duration,
    	        @RequestParam("file") MultipartFile file
    	) {
    	    return ResponseEntity.ok(service.addVideo(lessonId, title, duration, file));
    	}

    
    @GetMapping("/lesson/{lessonId}/video")
    public ResponseEntity<Video> getVideo(@PathVariable Long lessonId) {
        return ResponseEntity.ok(service.getVideo(lessonId));
    }


    /** ADD QUIZ */
    @PostMapping("/lesson/{lessonId}/quiz")
    public ResponseEntity<Quiz> addQuiz(@PathVariable Long lessonId,
                                        @RequestBody Quiz quiz) {
        return ResponseEntity.ok(service.addQuiz(lessonId, quiz));
    }

    /** Get Quiz*/
    @GetMapping("/lesson/{lessonId}/quiz")
    public ResponseEntity<List<Quiz>> getQuizByLesson(@PathVariable Long lessonId) {
        return ResponseEntity.ok(service.getQuizByLesson(lessonId));
    }


//    @PostMapping("/quiz/submit")
//    public ResponseEntity<QuizResult> submitQuiz(@RequestBody QuizSubmitDTO dto) {
//        return ResponseEntity.ok(service.submitQuiz(dto));
//    }
    @PostMapping("/quiz/submit")
    public ResponseEntity<QuizResult> submitQuiz(
            @RequestBody QuizSubmitDTO dto,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();   // ✅ correct principal
        dto.setStudentId(user.getId());

        return ResponseEntity.ok(service.submitQuiz(dto));
    }



    /** ADD ASSIGNMENT */
    @PostMapping("/lesson/{lessonId}/assignment")
    public ResponseEntity<Assignment> addAssignment(@PathVariable Long lessonId,
                                                    @RequestBody Assignment assignment) {
        return ResponseEntity.ok(service.addAssignment(lessonId, assignment));
    }

    /** Submit Assignment */
    
    @PostMapping(value = "/assignment/submit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> submitAssignment(
            @RequestParam("file") MultipartFile file,
            @RequestParam("studentId") Long studentId,
            @RequestParam("assignmentId") Long assignmentId
    ) {
        return ResponseEntity.ok(service.submitAssignment(file, studentId, assignmentId));
    }
    
    /** GET all assignments for a lesson */
    @GetMapping("/lesson/{lessonId}/assignments")
    public ResponseEntity<List<Assignment>> getAssignmentsByLesson(@PathVariable Long lessonId) {
        List<Assignment> assignments = service.getAssignmentsByLesson(lessonId);
        return ResponseEntity.ok(assignments);
    }

    /** GET all assignments for a course */
    @GetMapping("/course/{courseId}/assignments")
    public ResponseEntity<List<Assignment>> getAssignmentsByCourse(@PathVariable Long courseId) {
        List<Assignment> assignments = service.getAssignmentsByCourse(courseId);
        return ResponseEntity.ok(assignments);
    }

    
 // ✅ Get all submitted assignments by student
    @GetMapping("/{studentId}/assignments")
    public ResponseEntity<List<AssignmentSubmission>> getMyAssignments(
            @PathVariable Long studentId
    ) {
        return ResponseEntity.ok(
        		service.getMySubmissions(studentId)
        );
    }

    // ✅ (Optional) Get submissions for a lesson
    @GetMapping("/{studentId}/lesson/{lessonId}/assignments")
    public ResponseEntity<List<AssignmentSubmission>> getMyAssignmentsByLesson(
            @PathVariable Long studentId,
            @PathVariable Long lessonId
    ) {
        return ResponseEntity.ok(
        		service.getMySubmissionsByLesson(studentId, lessonId)
        );
    }

    /** PUBLISH COURSE */
    @PutMapping("/course/{courseId}/publish")
    public ResponseEntity<String> publishCourse(@PathVariable Long courseId) {
        service.publishCourse(courseId);
        return ResponseEntity.ok("Course published successfully");
    }

    /** GET ENROLLED STUDENTS */
    @GetMapping("/course/{courseId}/students")
    public ResponseEntity<List<User>> getStudents(@PathVariable Long courseId) {
        return ResponseEntity.ok(service.getEnrolledStudents(courseId));
    }
    
//    @PostMapping("/course/{courseId}/enroll")
//    public ResponseEntity<Enrollment> enrollStudent(@PathVariable Long courseId,
//                                                    @RequestBody Enrollment enrollment) {
//        return ResponseEntity.ok(service.enrollStudent(courseId, enrollment));
//    }
    @PostMapping("/course/{courseId}/enroll")
    public ResponseEntity<Enrollment> enrollStudent(
            @PathVariable Long courseId,
            @RequestBody EnrollmentRequest request
    ) {
        Enrollment enrollment = new Enrollment();

        enrollment.setAmountPaid(request.getAmountPaid());
        enrollment.setProgressPercentage(
            request.getProgressPercentage() != null ? request.getProgressPercentage() : 0.0
        );

        User student = new User();
        student.setId(request.getStudentId());
        enrollment.setStudent(student);

        return ResponseEntity.ok(service.enrollStudent(courseId, enrollment));
    }


    /** GRADE SUBMISSION */
    @PutMapping("/submission/{submissionId}/grade")
    public ResponseEntity<AssignmentSubmission> gradeSubmission(@PathVariable Long submissionId,
                                                      @RequestParam Integer grade) {
        return ResponseEntity.ok(service.gradeSubmission(submissionId, grade));
    }

    /** TRACK PROGRESS */
    @GetMapping("/course/{courseId}/progress")
    public ResponseEntity<List<StudentProgressDTO>> progress(@PathVariable Long courseId) {
        return ResponseEntity.ok(service.getCourseProgress(courseId));
    }

    /** DISCUSSION REPLY */
    
    @PostMapping("/discussion/create")
    public ResponseEntity<Discussion> createDiscussion(@RequestBody Discussion discussion) {
        return ResponseEntity.ok(service.createDiscussion(discussion));
    }
    
    @GetMapping("/course/{courseId}/discussions")
    public ResponseEntity<List<DiscussionResponseDTO>> getDiscussions(
            @PathVariable Long courseId
    ) {
        return ResponseEntity.ok(service.getDiscussions(courseId));
    }


    @PostMapping("/discussion/{threadId}/reply")
    public ResponseEntity<Reply> reply(@PathVariable Long threadId,
                                       @RequestBody Reply reply) {
        return ResponseEntity.ok(service.replyToDiscussion(threadId, reply));
    }
    
    @GetMapping("/students")
    public ResponseEntity<List<User>> getAllStudents() {
        return ResponseEntity.ok(service.getAllStudents());
    }

    @GetMapping("/discussions")
    public ResponseEntity<List<Discussion>> getAllDiscussions() {
        return ResponseEntity.ok(service.getAllDiscussions());
    }

}

