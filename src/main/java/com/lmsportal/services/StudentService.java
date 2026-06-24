package com.lmsportal.services;

import org.springframework.web.multipart.MultipartFile;

import com.lmsportal.models.AssignmentSubmission;
import com.lmsportal.models.Discussion;

public interface StudentService {
    Discussion createDiscussion(Long courseId, Long studentId, Discussion discussion);
    
   // String submitAssignment(MultipartFile file, Long studentId, Long assignmentId);
    String submitAssignment(MultipartFile file, String studentEmail, Long assignmentId);
    
    AssignmentSubmission gradeSubmission(Long submissionId, Integer grade);


}
