package com.lmsportal.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(
	    name = "enrollments",
	    uniqueConstraints = {
	        @UniqueConstraint(columnNames = {"student_id", "course_id"})
	    }
	)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    private Instant enrolledAt;

    private Double amountPaid;

    private Double progressPercentage = 0.0;
}
