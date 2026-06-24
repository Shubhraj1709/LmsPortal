package com.lmsportal.utils;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.lmsportal.repositories.AssignmentSubmissionRepository;
import com.lmsportal.repositories.EnrollmentRepository;
import com.lmsportal.repositories.QuizResultRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CertificateEligibilityChecker {

    private final EnrollmentRepository enrollmentRepository;
    private final QuizResultRepository quizResultRepository;
    private final AssignmentSubmissionRepository assignmentSubmissionRepository;

    public void checkEligibility(Long studentId, Long courseId) {

        // 1️⃣ Enrollment
        if (!enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId)) {
            throw new RuntimeException("Student is not enrolled in this course");
        }

        // 2️⃣ Quiz pass check
     // 2️⃣ Quiz pass check (FIXED)

     // total quizzes present in the course
        long totalQuizzesInCourse =
                quizResultRepository.countTotalQuizzesByCourse(courseId);

        long passedQuizzesByStudent =
                quizResultRepository.countPassedQuizResultsByStudentAndCourse(studentId, courseId);

        System.out.println("=== QUIZ ELIGIBILITY DEBUG ===");
        System.out.println("CourseId = " + courseId);
        System.out.println("StudentId = " + studentId);
        System.out.println("Total quizzes in course = " + totalQuizzesInCourse);
        System.out.println("Passed quizzes by student = " + passedQuizzesByStudent);

     // if course has quizzes and student has not passed all
     if (totalQuizzesInCourse > 0 && totalQuizzesInCourse != passedQuizzesByStudent) {
         throw new RuntimeException("All quizzes must be passed");
     }


        // 3️⃣ Assignment submission check
//        long totalAssignments =
//                assignmentSubmissionRepository.countAssignmentsByCourse(courseId);
//
//        long submittedAssignments =
//                assignmentSubmissionRepository
//                        .countSubmittedAssignmentsByStudentAndCourse(studentId, courseId);
//
//        if (totalAssignments != submittedAssignments) {
//            throw new RuntimeException("All assignments must be submitted");
//        }
        
        System.out.println("==== CERTIFICATE ELIGIBILITY CHECK ====");
        System.out.println("StudentId = " + studentId);
        System.out.println("CourseId = " + courseId);

     // 3️⃣ Assignment submission check (FIXED)
        Set<Long> allAssignmentIds =
                assignmentSubmissionRepository.findAssignmentIdsByCourse(courseId);

        Set<Long> submittedAssignmentIds =
                assignmentSubmissionRepository.findSubmittedAssignmentIds(studentId, courseId);
        
        System.out.println("All assignment IDs      = " + allAssignmentIds);
        System.out.println("Submitted assignment IDs = " + submittedAssignmentIds);

        // remove submitted from total
        allAssignmentIds.removeAll(submittedAssignmentIds);

        if (!allAssignmentIds.isEmpty()) {
            throw new RuntimeException("All assignments must be submitted");
        }

    }
}
