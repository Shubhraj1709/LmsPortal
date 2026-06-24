package com.lmsportal.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lmsportal.dto.CourseDTO;
import com.lmsportal.models.Assignment;
import com.lmsportal.models.AssignmentSubmission;
import com.lmsportal.models.Course;
import com.lmsportal.models.Discussion;
import com.lmsportal.models.User;
import com.lmsportal.repositories.AssignmentRepository;
import com.lmsportal.repositories.AssignmentSubmissionRepository;
import com.lmsportal.repositories.CourseRepository;
import com.lmsportal.repositories.DiscussionRepository;
import com.lmsportal.repositories.EnrollmentRepository;
import com.lmsportal.repositories.SubmissionRepository;
import com.lmsportal.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final CourseRepository courseRepo;
    private final UserRepository userRepo;
    private final DiscussionRepository discussionRepo;
    private final AssignmentRepository assignmentRepo;
    private final SubmissionRepository submissionRepo;
    private final AssignmentSubmissionRepository assignmentSubmissionRepo;

    private final EnrollmentRepository enrollmentRepo;   // ADD THIS

    
    @Override
    public Discussion createDiscussion(Long courseId, Long studentId, Discussion discussion) {

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course Not Found"));

        User student = userRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student Not Found"));

        discussion.setCourse(course);
        discussion.setStudent(student);

        return discussionRepo.save(discussion);
    }
    


    @Override
    @Transactional
    public String submitAssignment(
            MultipartFile file,
            String studentEmail,
            Long assignmentId
    ) {

        User student = userRepo.findByEmail(studentEmail)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Assignment assignment = assignmentRepo.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        // Save file
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get("uploads/assignments/" + fileName);

        try {
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("File upload failed", e);
        }

        // 🔍 CHECK EXISTING SUBMISSION
        AssignmentSubmission submission =
                assignmentSubmissionRepo
                        .findByAssignment_IdAndStudent_Id(
                                assignmentId,
                                student.getId()
                        )
                        .orElse(null);

        if (submission != null) {
            // 🔁 RESUBMIT
            submission.setFileUrl(fileName);
            submission.setSubmittedAt(LocalDateTime.now());

            assignmentSubmissionRepo.save(submission);
            return "Assignment resubmitted successfully";
        }

        // 🆕 FIRST SUBMISSION
        AssignmentSubmission newSubmission = new AssignmentSubmission();
        newSubmission.setAssignment(assignment);
        newSubmission.setStudent(student);
        newSubmission.setFileUrl(fileName);
        newSubmission.setSubmittedAt(LocalDateTime.now());

        assignmentSubmissionRepo.save(newSubmission);

        return "Assignment submitted successfully";
    }
    
    /** GRADE ASSIGNMENT */
    @Override
    public AssignmentSubmission gradeSubmission(Long submissionId, Integer grade) {
        AssignmentSubmission assignmentSubmission = submissionRepo.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission Not Found"));

        assignmentSubmission.setGrade(grade);
        return submissionRepo.save(assignmentSubmission);
    }
    
//    public List<CourseDTO> getStudentCourses(Long studentId) {
//
//        List<Course> courses = courseRepo.findPublishedCourses();
//
//        return courses.stream().map(course -> {
//
//            CourseDTO dto = new CourseDTO();
//            dto.setId(course.getId());
//            dto.setTitle(course.getTitle());
//            dto.setDescription(course.getDescription());
//            dto.setPrice(course.getPrice());
//
//            // 🔥 IMPORTANT LINE
//            boolean enrolled =
//                    enrollmentRepo.existsByStudentIdAndCourseId(studentId, course.getId());
//
//            dto.setEnrolled(enrolled);
//
//            return dto;
//
//        }).toList();
//}
}
