package com.lmsportal.services;

import java.util.List;

import com.lmsportal.dto.ChatbotRequestDto;
import com.lmsportal.dto.ChatbotResponseDto;
import com.lmsportal.models.ChatbotFaq;

public interface ChatbotService {

    List<ChatbotFaq> getActiveFaqs();

    ChatbotResponseDto ask(ChatbotRequestDto request);
}

