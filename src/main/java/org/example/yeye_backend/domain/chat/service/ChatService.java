package org.example.yeye_backend.domain.chat.service;

import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.chat.domain.ChatMessage;
import org.example.yeye_backend.domain.chat.domain.Preset;
import org.example.yeye_backend.domain.chat.service.manager.ChatSessionManager;
import org.example.yeye_backend.domain.chat.service.manager.LLMClient;
import org.example.yeye_backend.domain.user.domain.User;
import org.example.yeye_backend.domain.user.facade.UserFacade;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatSessionManager chatSessionManager;
    private final ChatMessageService chatMessageService;
    private final PresetService presetService;
    private final LLMClient llmClient;
    private final UserFacade userFacade;

    public String handleUserMessage(String message){
        String sessionId = chatSessionManager.createSession();
        User user = userFacade.getCurrentUser();

        ChatMessage current = chatMessageService.saveUserMessage(sessionId, message, user);

        List<ChatMessage> history = chatMessageService.loadRecentMessagesBefore(current, user);
        Preset preset = presetService.loadOrCreatePreset(user);

        llmClient.chat(message, history, preset, sessionId, user);
        return sessionId;
    }

    public SseEmitter subscribe(String sessionId){
        return chatSessionManager.getEmitter(sessionId);
    }

    public void updatePreset(String name, String prompt, MultipartFile profile) {
        User user = userFacade.getCurrentUser();
        presetService.updatePreset(user, name, prompt, profile);
    }
}
