package com.lmsportal.dto;

import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscussionResponseDTO {
    private Long id;
    private String question;
    private Long studentId;
    private String studentName;
    private List<ReplyDTO> replies;
}

