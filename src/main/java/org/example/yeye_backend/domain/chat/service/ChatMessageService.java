package org.example.yeye_backend.domain.chat.service;

import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.chat.domain.ChatMessage;
import org.example.yeye_backend.domain.chat.domain.repository.ChatMessageRepository;
import org.example.yeye_backend.domain.user.domain.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    @Transactional
    public ChatMessage saveUserMessage(String sessionId, String message, User user){
        ChatMessage saved = ChatMessage.builder()
                .sessionId(sessionId)
                .role(ChatMessage.Role.user)
                .content(message)
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();

        return chatMessageRepository.save(saved);
    }

    @Transactional
    public void saveAssistantMessage(String sessionId, String message, User user){
        chatMessageRepository.save(ChatMessage.builder()
                .sessionId(sessionId)
                .role(ChatMessage.Role.assistant)
                .content(message)
                .createdAt(LocalDateTime.now())
                .user(user)
                .build());
    }

    public List<ChatMessage> loadRecentMessagesBefore(ChatMessage currentMessage, User user) {
        return chatMessageRepository.findRecentUserMessagesByCreatedAtAndUserId(
                currentMessage.getCreatedAt(),
                user.getId(),
                PageRequest.of(0, 4)
        );
    }
}
