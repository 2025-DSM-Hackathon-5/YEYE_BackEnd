package org.example.yeye_backend.domain.chat.service.manager;

import org.example.yeye_backend.domain.chat.exception.SessionNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatSessionManager {
    private final Map<String, SseEmitter> emitterMap = new ConcurrentHashMap<>();

    public String createSession(){ //스트림 메세지를 보낼 세션 방 만들기
        String sessionId = UUID.randomUUID().toString();
        SseEmitter emitter= new SseEmitter(60 * 60 * 1000L);
        emitterMap.put(sessionId, emitter);


        //연결 종료 시
        emitter.onCompletion(() -> emitterMap.remove(sessionId));
        emitter.onTimeout(() -> {
            emitter.complete();
            emitterMap.remove(sessionId);
        });
        return sessionId;
    }

    public SseEmitter getEmitter(String sessionId){
        SseEmitter emitter = emitterMap.get(sessionId);

        if(emitter == null ) throw SessionNotFoundException.EXCEPTION;
        return emitter;
    }

    public void emitMessage(String sessionId, String message){
        SseEmitter emitter = emitterMap.get(sessionId);
        if(emitter != null){
            try{
                emitter.send(SseEmitter.event().name("message").data(message));
            } catch (IOException e){
                emitter.completeWithError(e);
                emitterMap.remove(sessionId);
            }
        }
    }

    public void complete(String sessionId) {
        SseEmitter emitter = emitterMap.get(sessionId);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event().name("end").data("[DONE]"));
            } catch (IOException e) {
                // 무시하고 정리
            } finally {
                //emitter.complete();
                //emitterMap.remove(sessionId);
            }
        }
    }
}
