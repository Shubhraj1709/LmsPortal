package com.lmsportal.dto;

import lombok.Data;

@Data
public class ChatbotRequestDto {
    private String question;
    private Long studentId;     // optional
    private Long courseId;      // optional
    private Long lessonId;      // optional
}
