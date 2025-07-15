package org.example.yeye_backend.domain.chat.service;

import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.chat.domain.repository.PresetRepository;
import org.example.yeye_backend.domain.chat.presentation.dto.response.PresetResponseDto;
import org.example.yeye_backend.domain.user.facade.UserFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryPresetService {

    private final PresetRepository presetRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public PresetResponseDto getPreset(){
        Long userId = userFacade.getCurrentUser().getId();
        return presetRepository.findByUserId(userId)
                .stream()
                .map(PresetResponseDto::from)
                .findFirst()
                .orElse(null); //TODO : EXCEPTION 추가
    }
}
