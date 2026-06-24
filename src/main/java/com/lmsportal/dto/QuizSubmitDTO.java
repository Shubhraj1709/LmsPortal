package com.lmsportal.dto;

import java.util.Map;

import lombok.Data;

@Data
public class QuizSubmitDTO {
    private Long studentId;
    private Long lessonId;
    private Map<String, String> answers;  // questionId → chosenOption
    
}
