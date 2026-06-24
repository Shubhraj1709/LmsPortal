package com.lmsportal.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
	public class EnrollmentRequest {
    private Long studentId;
    private Double amountPaid;
    private Double progressPercentage;

    // getters & setters
}
