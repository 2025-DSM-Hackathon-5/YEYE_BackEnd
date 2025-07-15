package org.example.yeye_backend.domain.chat.service;

import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.chat.domain.repository.ChatMessageRepository;
import org.example.yeye_backend.domain.chat.presentation.dto.response.ChatResponseDto;
import org.example.yeye_backend.domain.user.facade.UserFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryChatMessagesListService {
    private final UserFacade userFacade;
    private final ChatMessageRepository chatMessageRepository;

    @Transactional(readOnly = true)
    public List<ChatResponseDto> getChatMessages() {
        Long userId = userFacade.getCurrentUser().getId();

        return chatMessageRepository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(ChatResponseDto::from)
                .toList();
    }
}
