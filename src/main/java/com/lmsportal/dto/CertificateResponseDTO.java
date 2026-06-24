package com.lmsportal.dto;

import java.time.LocalDateTime;

public record CertificateResponseDTO(
        String certificateCode,
        String studentName,
        String courseTitle,
        LocalDateTime issueDate,
        String status

) {}
