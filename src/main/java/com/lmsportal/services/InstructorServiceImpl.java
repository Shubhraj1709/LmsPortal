package com.lmsportal.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.*;
import org.springframework.web.multipart.MultipartFile;

import com.lmsportal.models.CourseModule;
import com.lmsportal.dto.DiscussionResponseDTO;
import com.lmsportal.dto.QuizSubmitDTO;
import com.lmsportal.dto.ReplyDTO;
import com.lmsportal.dto.StudentProgressDTO;
import com.lmsportal.enums.RoleEnum;
import com.lmsportal.models.Assignment;
import com.lmsportal.models.AssignmentSubmission;
import com.lmsportal.models.Course;
import com.lmsportal.models.Discussion;
import com.lmsportal.models.Enrollment;
import com.lmsportal.models.Lesson;
import com.lmsportal.models.Quiz;
import com.lmsportal.models.QuizResult;
import com.lmsportal.models.Reply;
import com.lmsportal.models.AssignmentSubmission;
import com.lmsportal.models.User;
import com.lmsportal.models.Video;
import com.lmsportal.repositories.AssignmentRepository;
import com.lmsportal.repositories.AssignmentSubmissionRepository;
import com.lmsportal.repositories.CourseRepository;
import com.lmsportal.repositories.DiscussionRepository;
import com.lmsportal.repositories.EnrollmentRepository;
import com.lmsportal.repositories.LessonRepository;
import com.lmsportal.repositories.ModuleRepository;
import com.lmsportal.repositories.QuizRepository;
import com.lmsportal.repositories.QuizResultRepository;
import com.lmsportal.repositories.ReplyRepository;
import com.lmsportal.repositories.SubmissionRepository;
import com.lmsportal.repositories.UserRepository;
import com.lmsportal.repositories.VideoRepository;

import jakarta.transaction.Transactional;

import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

import com.lmsportal.models.Discussion;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final CourseRepository courseRepo;
    private final ModuleRepository moduleRepo;
    private final LessonRepository lessonRepo;
    private final VideoRepository videoRepo;
    private final QuizRepository quizRepo;
    private final AssignmentRepository assignmentRepo;
    private final SubmissionRepository submissionRepo;
    private final EnrollmentRepository enrollmentRepo;
    private final DiscussionRepository discussionRepo;
    private final ReplyRepository replyRepo;
    private final UserRepository userRepo;
    private final QuizResultRepository quizResultRepo;
    private final AssignmentSubmissionRepository assignmentSubmissionRepo;
    private final AuthService authService;
    
    /** ADD MODULE */
    @Override
    public CourseModule addModule(Long courseId, CourseModule courseModule) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course Not Found"));

        courseModule.setCourse(course);
        return moduleRepo.save(courseModule);
    }
    
    @Override
    public List<CourseModule> getModules(Long courseId) {
        return moduleRepo.findByCourse_Id(courseId);
    }

    /** ADD LESSON */
    @Override
    public Lesson addLesson(Long moduleId, Lesson lesson) {
    	CourseModule courseModule = moduleRepo.findById(moduleId)
    	        .orElseThrow(() -> new RuntimeException("Module Not Found"));


        lesson.setCourseModule(courseModule);
        return lessonRepo.save(lesson);
    }
    
    @Override
    public List<Lesson> getLessons(Long moduleId) {
        return lessonRepo.findByCourseModule_Id(moduleId);
    }


    /** ADD VIDEO */
    @Override
//    public Video addVideo(Long lessonId, Video video) {
//        Lesson lesson = lessonRepo.findById(lessonId)
//                .orElseThrow(() -> new RuntimeException("Lesson Not Found"));
//
//        video.setLesson(lesson);
//        return videoRepo.save(video);
//    }
    
    @Transactional
    public Video addVideo(Long lessonId, String title, Integer duration, MultipartFile file) {

        Lesson lesson = lessonRepo.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        Video video = videoRepo.findByLesson_Id(lessonId)
                .orElse(new Video());

        // delete old file if exists
        if (video.getId() != null) {
            deleteFileIfExists(video.getVideoUrl());
        }

        // save new file
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path uploadDir = Paths.get("uploads/videos");

        try {
            Files.createDirectories(uploadDir);
            Files.copy(
                    file.getInputStream(),
                    uploadDir.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING
            );
        } catch (IOException e) {
            throw new RuntimeException("Video upload failed", e);
        }

        video.setTitle(title);
        video.setDuration(duration);
        video.setVideoUrl("/videos/" + fileName);
        video.setLesson(lesson);

        return videoRepo.save(video);
    }


    private void deleteFileIfExists(String videoUrl) {
        if (videoUrl == null) return;

        // If videoUrl is an external URL, do nothing
        if (videoUrl.startsWith("http")) {
            return;
        }

        try {
            Path path = Paths.get(
                    "uploads/videos",
                    Paths.get(videoUrl).getFileName().toString()
            );
            Files.deleteIfExists(path);
        } catch (IOException e) {
            // log only, do not fail transaction
        }
    }



    @Override
    public Video getVideo(Long lessonId) {
        return videoRepo.findByLesson_Id(lessonId)
                .orElseThrow(() -> new RuntimeException("Video not found for lesson"));
    }

    
    @Override
    public List<Video> getAllVideos() {
        return videoRepo.findAll();
    }

    /** ADD QUIZ */
    @Override
    public Quiz addQuiz(Long lessonId, Quiz quiz) {
        Lesson lesson = lessonRepo.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson Not Found"));

        quiz.setLesson(lesson);
        return quizRepo.save(quiz);
    }

    /** ADD ASSIGNMENT */
    @Override
    public Assignment addAssignment(Long lessonId, Assignment assignment) {
        Lesson lesson = lessonRepo.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson Not Found"));

        assignment.setLesson(lesson);
        return assignmentRepo.save(assignment);
    }
    
    @Override
    public List<Assignment> getAssignmentsByLesson(Long lessonId) {
        Lesson lesson = lessonRepo.findById(lessonId)
            .orElseThrow(() -> new RuntimeException("Lesson not found"));
        return assignmentRepo.findByLesson(lesson);
    }

    @Override
    public List<Assignment> getAssignmentsByCourse(Long courseId) {
    	List<Lesson> lessons = lessonRepo.findByCourseModuleCourseId(courseId);
        return assignmentRepo.findByLessonIn(lessons);
    }



    /** PUBLISH COURSE */
    @Override
    public void publishCourse(Long courseId) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course Not Found"));

        course.setPublished(true);
        courseRepo.save(course);
    }

    /** GET ENROLLED STUDENTS */
    @Override
    public List<User> getEnrolledStudents(Long courseId) {
        List<Enrollment> list = enrollmentRepo.findByCourse_Id(courseId);
        return list.stream()
                .map(enrollment -> enrollment.getStudent())
                .collect(Collectors.toList());
    }

    public Enrollment enrollStudent(Long courseId, Enrollment enrollment) {

        // Fetch course
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // Get studentId
        Long studentId = null;
        if (enrollment.getStudent() != null) {
            studentId = enrollment.getStudent().getId();
        }

        if (studentId == null) {
            throw new RuntimeException("Student ID is required for enrollment");
        }

        User student = userRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // ✅ DUPLICATE ENROLLMENT CHECK (NEW)
        boolean alreadyEnrolled =
                enrollmentRepo.existsByStudent_IdAndCourse_Id(studentId, courseId);

        if (alreadyEnrolled) {
            throw new RuntimeException("Course is already enrolled");
        }

        // Set relations
        enrollment.setCourse(course);
        enrollment.setStudent(student);

        if (enrollment.getEnrolledAt() == null) {
            enrollment.setEnrolledAt(Instant.now());
        }

        if (enrollment.getProgressPercentage() == null) {
            enrollment.setProgressPercentage(0.0);
        }

        return enrollmentRepo.save(enrollment);
    }


    /** GRADE ASSIGNMENT */
    @Override
    public AssignmentSubmission gradeSubmission(Long submissionId, Integer grade) {
        AssignmentSubmission assignmentSubmission = submissionRepo.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission Not Found"));

        assignmentSubmission.setGrade(grade);
        return submissionRepo.save(assignmentSubmission);
    }

    /** TRACK PROGRESS */
    @Override
    public List<StudentProgressDTO> getCourseProgress(Long courseId) {
        List<Enrollment> enrolled = enrollmentRepo.findByCourse_Id(courseId);

        return enrolled.stream().map(e -> {
            StudentProgressDTO dto = new StudentProgressDTO();
            dto.setStudentId(e.getStudent().getId());
            dto.setStudentName(e.getStudent().getName());

            dto.setProgress(e.getProgressPercentage());

            return dto;
        }).collect(Collectors.toList());
    }
    		
    /** DISCUSSION REPLY */

    
    @Override
    public Reply replyToDiscussion(Long threadId, Reply reply) {

        Long instructorId = authService.getLoggedInUserId(); // get from JWT session

        Discussion discussion = discussionRepo.findById(threadId)
                .orElseThrow(() -> new RuntimeException("Thread Not Found"));

        User instructor = userRepo.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor Not Found"));

        reply.setDiscussion(discussion);
        reply.setInstructor(instructor);

        return replyRepo.save(reply);
    }

    @Override
    public Discussion createDiscussion(Discussion discussion) {

        // validate course
        Course course = courseRepo.findById(discussion.getCourse().getId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // validate student
        User student = userRepo.findById(discussion.getStudent().getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        discussion.setCourse(course);
        discussion.setStudent(student);

        return discussionRepo.save(discussion);
    }
    


    @Override
    public List<DiscussionResponseDTO> getDiscussions(Long courseId) {

        List<Discussion> discussions =
                discussionRepo.findDiscussionsWithReplies(courseId);

        return discussions.stream().map(d -> {

            List<ReplyDTO> replies = d.getReplies().stream()
                    .map(r -> new ReplyDTO(
                            r.getId(),
                            r.getMessage(),
                            r.getInstructor().getId(),
                            r.getInstructor().getName()
                    ))
                    .toList();

            return new DiscussionResponseDTO(
                    d.getId(),
                    d.getQuestion(),
                    d.getStudent().getId(),
                    d.getStudent().getName(),
                    replies
            );

        }).toList();
    }


    @Override
    public List<Quiz> getQuizByLesson(Long lessonId) {
        return quizRepo.findByLessonId(lessonId);
    }

    
    @Override
    public QuizResult submitQuiz(QuizSubmitDTO dto) {

        List<Quiz> quizList = quizRepo.findByLessonId(dto.getLessonId());

        if (quizList.isEmpty()) {
            throw new RuntimeException("No quiz found for this lesson");
        }

        int total = quizList.size();
        int score = 0;

        for (Quiz q : quizList) {
//            String chosen = dto.getAnswers().get(q.getId());
        	String chosen = dto.getAnswers().get(String.valueOf(q.getId()));

            if (chosen != null && chosen.equalsIgnoreCase(q.getCorrectAnswer())) {
                score++;
            }
        }

        double percentage = ((double) score / total) * 100;

        // ✅ PASS LOGIC (IMPORTANT)
        boolean passed = percentage >= 60;   // you can change threshold

        QuizResult result = new QuizResult();
        result.setLessonId(dto.getLessonId());
        result.setStudentId(dto.getStudentId());
        result.setTotalQuestions(total);
        result.setCorrectAnswers(score);
        result.setScorePercentage(percentage);
        result.setPassed(passed);           // 🔥 THIS FIXES CERTIFICATE ISSUE

        return quizResultRepo.save(result);
    }



    @Override
    @Transactional
    public String submitAssignment(
            MultipartFile file,
            Long studentId,
            Long assignmentId
    ) {

        User student = userRepo.findById(studentId)
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

        // Check if submission already exists
        AssignmentSubmission submission =
                assignmentSubmissionRepo
                        .findByAssignment_IdAndStudent_Id(assignmentId, studentId)
                        .orElse(null);

        if (submission != null) {
            // 🔁 RESUBMIT (UPDATE)
            submission.setFileUrl(fileName);
            submission.setSubmittedAt(LocalDateTime.now());
            assignmentSubmissionRepo.save(submission);

            return "Assignment resubmitted successfully";
        }

        // 🆕 FIRST TIME SUBMIT (INSERT)
        AssignmentSubmission newSubmission = new AssignmentSubmission();
        newSubmission.setAssignment(assignment);
        newSubmission.setStudent(student);
        newSubmission.setFileUrl(fileName);
        newSubmission.setSubmittedAt(LocalDateTime.now());

        assignmentSubmissionRepo.save(newSubmission);

        return "Assignment submitted successfully";
    }

    @Override
    public List<AssignmentSubmission> getMySubmissions(Long studentId) {

        if (!userRepo.existsById(studentId)) {
            throw new RuntimeException("Student not found");
        }

        return assignmentSubmissionRepo.findByStudent_Id(studentId);
    }

    @Override
    public List<AssignmentSubmission> getMySubmissionsByLesson(
            Long studentId,
            Long lessonId
    ) {
        return assignmentSubmissionRepo.findByStudentAndLesson(studentId, lessonId);
    }





    @Override
    public List<User> getAllStudents() {
        return userRepo.findByRole(RoleEnum.STUDENT);
    }

    
    @Override
    public List<Discussion> getAllDiscussions() {
        return discussionRepo.findAll();
    }


}
