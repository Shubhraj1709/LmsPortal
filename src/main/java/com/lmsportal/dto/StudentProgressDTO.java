package com.lmsportal.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentProgressDTO {

    private Long studentId;
    private String studentName;
    private Double progress;
}
