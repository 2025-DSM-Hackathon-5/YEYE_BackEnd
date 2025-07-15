package org.example.yeye_backend.domain.chat.service;

import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.chat.domain.Preset;
import org.example.yeye_backend.domain.chat.domain.repository.PresetRepository;
import org.example.yeye_backend.domain.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PresetService {
    private final PresetRepository presetRepository;

    @Transactional
    public Preset loadOrCreatePreset(User user){
        return presetRepository.findByUser(user)
                .orElseGet(() -> presetRepository.save(
                        Preset.builder()
                                .user(user)
                                .name("친절한 AI")
                                .systemPrompt("어떠한 질문이든 친절히 대답해주세요")
                                .build()));
    }

    @Transactional
    public void updatePreset(User user, String name, String prompt) {
        Preset preset = presetRepository.findByUser(user)
                .orElseGet(() -> Preset.builder()
                        .user(user)
                        .name("친절한 AI")
                        .systemPrompt("어떠한 질문이든 친절히 대답해주세요.")
                        .build());

        preset.setName(name);
        preset.setSystemPrompt(prompt);

        presetRepository.save(preset);
    }

}
