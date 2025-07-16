package org.example.yeye_backend.domain.chat.presentation.dto.response;
import org.example.yeye_backend.domain.chat.domain.ChatMessage;

import java.util.List;

public record ChatHistoryListResponseDto(
    List<ChatHistoryListItemDto> history
) {
    public static ChatHistoryListResponseDto from(List<ChatMessage> chatMessageList) {
        return new ChatHistoryListResponseDto(
                chatMessageList.stream()
                        .map(ChatHistoryListItemDto::from)
                        .toList()
        );
    }
}

record ChatHistoryListItemDto(
    String role,
    String content,
    String createdAt
) {
    public static ChatHistoryListItemDto from(ChatMessage message) {
        return new ChatHistoryListItemDto(
                message.getRole().name(),
                message.getContent(),
                message.getCreatedAt().toString()
        );
    }
}


