    package org.example.yeye_backend.domain.chat.presentation.dto.response;

    import lombok.Builder;
    import org.example.yeye_backend.domain.chat.domain.ChatMessage;

    @Builder
    public record ChatResponseDto(
            String role,
            String content,
            String createdAt
    ) {
        public static ChatResponseDto from(ChatMessage message) {
            return new ChatResponseDto(
                    message.getRole().name(),
                    message.getContent(),
                    message.getCreatedAt().toString()
            );
        }
    }
