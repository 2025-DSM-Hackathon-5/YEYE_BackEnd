package org.example.yeye_backend.domain.chat.presentation.dto.response;

import org.example.yeye_backend.domain.chat.domain.Preset;

public record PresetResponseDto(
        String name,
        String profile,
        String prompt
) {
    public static PresetResponseDto from(Preset preset){
        return new PresetResponseDto(
                preset.getName(),
                preset.getProfile(),
                preset.getSystemPrompt()
        );
    }
}
