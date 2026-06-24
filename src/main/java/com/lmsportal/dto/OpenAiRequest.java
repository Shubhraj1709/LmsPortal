package com.lmsportal.dto;

import java.util.List;
import lombok.Data;

@Data
public class OpenAiRequest {
    private String model;
    private List<Message> messages;
}
