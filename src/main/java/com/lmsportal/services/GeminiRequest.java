package com.lmsportal.services;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeminiRequest {
    private List<GeminiContent> contents;
}
