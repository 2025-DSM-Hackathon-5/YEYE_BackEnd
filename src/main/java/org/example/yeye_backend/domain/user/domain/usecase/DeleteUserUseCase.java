package org.example.yeye_backend.domain.user.domain.usecase;

import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.user.domain.User;
import org.example.yeye_backend.domain.user.domain.service.CommandUserService;
import org.example.yeye_backend.domain.user.facade.UserFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteUserUseCase {
    private final UserFacade userFacade;
    private final CommandUserService commandUserService;

    public void execute() {
        User user = userFacade.getCurrentUser();

        commandUserService.deleteUserByUserId(user.getId());
    }
}
