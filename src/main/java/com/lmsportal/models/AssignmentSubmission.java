package com.lmsportal.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

//@Entity
//@Table(name = "assignment_submission")
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class AssignmentSubmission {
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    
//    @ManyToOne
//    @JoinColumn(name = "assignment_id")
//    private Assignment assignment;
//
//    @ManyToOne
//    @JoinColumn(name = "student_id")
//    private User student;
//    
//    @Column(name = "file_path")
//    private String fileUrl;
//    private LocalDateTime submittedAt;
//    
//    private Integer grade;
//}

@Entity
@Table(name = "assignment_submission")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @Column(name = "file_path")
    private String fileUrl;

    private LocalDateTime submittedAt;

    private Integer grade;
}
