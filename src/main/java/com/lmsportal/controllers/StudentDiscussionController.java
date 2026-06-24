package com.lmsportal.controllers;


import com.lmsportal.dto.QuizSubmitDTO;
import com.lmsportal.models.Discussion;
import com.lmsportal.models.QuizResult;
import com.lmsportal.models.User;
import com.lmsportal.repositories.UserRepository;
import com.lmsportal.services.InstructorService;
import com.lmsportal.services.StudentService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentDiscussionController {

    private final StudentService studentService;

    private final InstructorService service;
    private final UserRepository userRepo;


    /** CREATE DISCUSSION THREAD */
    @PostMapping("/course/{courseId}/discussion")
    public ResponseEntity<Discussion> createDiscussion(
            @PathVariable Long courseId,
            @RequestBody Discussion discussion,
            @RequestParam Long studentId) {

        return ResponseEntity.ok(studentService.createDiscussion(courseId, studentId, discussion));
    }
    
    
    @PostMapping(
    	    value = "/assignment/submit",
    	    consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    	)
    	public ResponseEntity<String> submitAssignment(
    			@RequestParam("file") MultipartFile file,
    			@RequestParam("assignmentId") Long assignmentId,
    	        Authentication authentication
    	) {
    	    String email = authentication.getName(); // logged-in student
    	    return ResponseEntity.ok(
    	        studentService.submitAssignment(file, email, assignmentId)
    	    );
    	}
    
    @PostMapping("/quiz/submit")
    public ResponseEntity<QuizResult> submitQuiz(
            @RequestBody QuizSubmitDTO dto,
            Authentication authentication
    ) {
        String email = authentication.getName(); // ✅ ALWAYS SAFE

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        dto.setStudentId(user.getId());

        return ResponseEntity.ok(service.submitQuiz(dto));
    }


}
