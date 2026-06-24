package com.lmsportal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmsportal.dto.GeminiResponse;

@Component
public class GeminiClient implements AiClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    @Value("${gemini.api.key}")
    private String apiKey;

    private static final String GEMINI_URL =
        "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=";

    @Override
    public String ask(String prompt) {

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            GeminiRequest request = new GeminiRequest(
                List.of(
                    new GeminiContent(
                        "user",
                        List.of(new Part(prompt))
                    )
                )
            );

            String json = mapper.writeValueAsString(request);

            HttpEntity<String> entity = new HttpEntity<>(json, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(
                GEMINI_URL + apiKey,
                entity,
                String.class
            );

            GeminiResponse geminiResponse =
                mapper.readValue(response.getBody(), GeminiResponse.class);

            return geminiResponse
                    .getCandidates()
                    .get(0)
                    .getContent()
                    .getParts()
                    .get(0)
                    .getText();

        } catch (Exception e) {
            e.printStackTrace(); // 🔥 see real error
            throw new RuntimeException("Gemini API error: " + e.getMessage());
        }
    }
}
