package com.lmsportal.services;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeminiContent {
    private String role;          // REQUIRED: "user"
    private List<Part> parts;
}
