package org.example.yeye_backend.domain.chat.service;

import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.chat.domain.repository.ChatMessageRepository;
import org.example.yeye_backend.domain.chat.presentation.dto.response.ChatHistoryListResponseDto;
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
    public ChatHistoryListResponseDto getChatMessages() {
        String userId = userFacade.getCurrentUser().getAccountId();

        return ChatHistoryListResponseDto.from(
                chatMessageRepository.findByUserIdOrderByCreatedAtDesc(userId)
        );
    }
}
