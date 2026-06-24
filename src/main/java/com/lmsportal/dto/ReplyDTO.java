package com.lmsportal.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {
    private Long id;
    private String message;
    private Long instructorId;
    private String instructorName;
}

