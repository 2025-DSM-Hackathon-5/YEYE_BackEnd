package org.example.yeye_backend.domain.chat.service.manager;

import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.chat.domain.ChatMessage;
import org.example.yeye_backend.domain.chat.domain.Preset;
import org.example.yeye_backend.domain.chat.service.ChatMessageService;
import org.example.yeye_backend.domain.user.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class LLMClient {
    @Qualifier("llmWebClient")
    private final WebClient webClient;

    private final ChatMessageService chatMessageService;
    private final ChatSessionManager chatSessionManager;

    public void chat(String question, List<ChatMessage> history, Preset preset, String sessionID, User user){
        List<Map<String, String>> chatHistory = history.stream()
                .map(m -> Map.of("role", m.getRole().name(), "content", m.getContent()))
                .toList();

        Map<String, Object> requestBody = Map.of(
                "question", question,
                "chat_history", chatHistory,
                "name", preset.getName(),
                "prompt", preset.getSystemPrompt()
        );

        StringBuilder buffer = new StringBuilder();

        webClient.post()
                .uri("/chat/stream")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve() //응답 받기
                .bodyToFlux(String.class)
                .doOnNext(chunk -> {
                    buffer.append(chunk);
                    chatSessionManager.emitMessage(sessionID, chunk);
                })
                .doOnError(error -> {
                    chatSessionManager.emitMessage(sessionID, "[ERROR]");
                    chatSessionManager.complete(sessionID);
                })
                .doOnComplete(() -> {
                    String fullResponse = buffer.toString();
                    chatMessageService.saveAssistantMessage(sessionID, fullResponse, user);
                    chatSessionManager.complete(sessionID);
                })
                .subscribe();
    }
}
