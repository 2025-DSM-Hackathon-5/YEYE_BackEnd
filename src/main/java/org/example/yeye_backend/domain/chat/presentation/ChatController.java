package org.example.yeye_backend.domain.chat.presentation;

import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.chat.presentation.dto.request.ChatRequestDto;
import org.example.yeye_backend.domain.chat.presentation.dto.request.PresetRequestDto;
import org.example.yeye_backend.domain.chat.presentation.dto.response.ChatResponseDto;
import org.example.yeye_backend.domain.chat.presentation.dto.response.PresetResponseDto;
import org.example.yeye_backend.domain.chat.service.ChatService;
import org.example.yeye_backend.domain.chat.service.QueryChatMessagesListService;
import org.example.yeye_backend.domain.chat.service.QueryPresetService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;
    private final QueryChatMessagesListService queryChatMessagesListService;
    private final QueryPresetService queryPresetService;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody ChatRequestDto dto){
        String sessionId = chatService.handleUserMessage(dto.message());
        return ResponseEntity.ok(sessionId);
    }

    @GetMapping(value = "/stream/{sessionId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter stream(@PathVariable String sessionId){
        return chatService.subscribe(sessionId);
    }

    @PostMapping("/preset")
    public ResponseEntity<Void> updatePreset(@RequestBody PresetRequestDto dto){
        chatService.updatePreset(dto.name(), dto.prompt());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<ChatResponseDto> getChatMessage(){
        return queryChatMessagesListService.getChatMessages();
    }

    @GetMapping("/preset")
    public PresetResponseDto getPreset(){
        return queryPresetService.getPreset();
    }
}
