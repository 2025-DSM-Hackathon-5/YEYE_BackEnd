package org.example.yeye_backend.domain.user.domain.usecase;

import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.user.domain.dto.response.GetUserDataResponseDto;
import org.example.yeye_backend.domain.user.domain.facade.UserFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetCurrentUserUseCase {
    private final UserFacade userFacade;

    public GetUserDataResponseDto getCurrentUserData() {
        return GetUserDataResponseDto.of(userFacade.getCurrentUser());
    }
}
