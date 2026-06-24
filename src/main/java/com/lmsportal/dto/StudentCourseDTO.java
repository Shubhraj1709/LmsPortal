package com.lmsportal.dto;

import lombok.Data;

@Data
public class StudentCourseDTO {

    private Long id;
    private String title;
    private Double price;
    private boolean enrolled;

}
