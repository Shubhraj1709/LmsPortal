package com.lmsportal.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discussion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;
    
    @OneToMany(
            mappedBy = "discussion",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
        )
        private List<Reply> replies;
}
