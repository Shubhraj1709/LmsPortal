package com.lmsportal.services;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.web.multipart.MultipartFile;

import com.lmsportal.dto.DiscussionResponseDTO;
import com.lmsportal.dto.QuizSubmitDTO;
import com.lmsportal.dto.StudentProgressDTO;
import com.lmsportal.models.Assignment;
import com.lmsportal.models.Lesson;
import com.lmsportal.models.CourseModule;
import com.lmsportal.models.Discussion;
import com.lmsportal.models.Enrollment;
import com.lmsportal.models.Quiz;
import com.lmsportal.models.QuizResult;
import com.lmsportal.models.Reply;
import com.lmsportal.models.AssignmentSubmission;
import com.lmsportal.models.User;
import com.lmsportal.models.Video;

public interface InstructorService {

    CourseModule addModule(Long courseId, CourseModule courseModule);

    Lesson addLesson(Long moduleId, Lesson lesson);

    Video addVideo(Long lessonId, String title, Integer duration, MultipartFile file);

    Quiz addQuiz(Long lessonId, Quiz quiz);

    Assignment addAssignment(Long lessonId, Assignment assignment);

    List getAssignmentsByLesson(Long lessonId);
    List getAssignmentsByCourse(Long courseId);
    
    void publishCourse(Long courseId);

    List<User> getEnrolledStudents(Long courseId);

    AssignmentSubmission gradeSubmission(Long submissionId, Integer grade);

    List<StudentProgressDTO> getCourseProgress(Long courseId);

    Reply replyToDiscussion(Long threadId, Reply reply);
    
    Enrollment enrollStudent(Long courseId, Enrollment enrollment);

    Discussion createDiscussion(Discussion discussion);

    List<Quiz> getQuizByLesson(Long lessonId);
    
    QuizResult submitQuiz(QuizSubmitDTO dto);

    String submitAssignment(MultipartFile file, Long studentId, Long assignmentId);

    List<AssignmentSubmission> getMySubmissions(Long studentId);

    List<AssignmentSubmission> getMySubmissionsByLesson(Long studentId, Long lessonId);
    
    List<CourseModule> getModules(Long courseId);

    List<Lesson> getLessons(Long moduleId);

    Video getVideo(Long lessonId);
    
    List<DiscussionResponseDTO> getDiscussions(Long courseId);

    List<Video> getAllVideos();

    List<User> getAllStudents();

    List<Discussion> getAllDiscussions();

	


}
