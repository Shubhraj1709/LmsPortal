package com.lmsportal.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class QuizResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long studentId;
    private Long lessonId;
    private int totalQuestions;
    private int correctAnswers;
    private double scorePercentage;
    private boolean passed; 
}
