package com.lmsportal.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmsportal.dto.ChatbotRequestDto;
import com.lmsportal.dto.ChatbotResponseDto;
import com.lmsportal.models.ChatbotFaq;
import com.lmsportal.services.ChatbotService;

@RestController
@RequestMapping("/api/chatbot")
public class ChatbotController {

    private final ChatbotService chatbotService;

    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    
    @GetMapping("/faqs")
    public List<ChatbotFaq> getFaqs() {
        return chatbotService.getActiveFaqs();
    }

    @PostMapping("/ask")
    public ResponseEntity<ChatbotResponseDto> askQuestion(
            @RequestBody ChatbotRequestDto request
    ) {
        return ResponseEntity.ok(chatbotService.ask(request));
    }
}
