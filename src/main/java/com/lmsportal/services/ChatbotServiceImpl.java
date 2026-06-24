package com.lmsportal.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lmsportal.dto.ChatbotRequestDto;
import com.lmsportal.dto.ChatbotResponseDto;
import com.lmsportal.models.ChatbotFaq;
import com.lmsportal.repositories.ChatbotFaqRepository;

@Service
public class ChatbotServiceImpl implements ChatbotService {

    private final ChatbotFaqRepository faqRepo;
    private final AiClient aiClient;

    public ChatbotServiceImpl(ChatbotFaqRepository faqRepo, AiClient aiClient) {
        this.faqRepo = faqRepo;
        this.aiClient = aiClient;
    }

    @Override
    public List<ChatbotFaq> getActiveFaqs() {
        return faqRepo.findByIsActiveTrue();
    }

    @Override
    public ChatbotResponseDto ask(ChatbotRequestDto request) {

        String answer = aiClient.ask(request.getQuestion());

        ChatbotResponseDto response = new ChatbotResponseDto();
        response.setAnswer(answer);

        return response;
    }


    private String buildPrompt(ChatbotRequestDto request) {
        return """
        You are an AI assistant for an LMS portal.
        Answer clearly in simple steps.

        Question:
        """ + request.getQuestion();
    }
}
