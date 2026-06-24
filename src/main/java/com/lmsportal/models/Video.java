package com.lmsportal.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String videoUrl;
    
    private Integer duration;

    @OneToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;
    
    
}
