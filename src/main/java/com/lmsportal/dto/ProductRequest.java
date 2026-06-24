package com.lmsportal.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private Long amount;      // paise
    private Long quantity;
    private String name;
    private String currency;
    private Long courseId;
    private Long studentId;
}

