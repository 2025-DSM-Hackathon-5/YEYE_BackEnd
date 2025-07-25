package org.example.yeye_backend.domain.user.domain.controller;

import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.user.domain.dto.response.GetUserDataResponseDto;
import org.example.yeye_backend.domain.user.domain.usecase.DeleteUserUseCase;
import org.example.yeye_backend.domain.user.domain.usecase.GetCurrentUserUseCase;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final GetCurrentUserUseCase getCurrentUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @GetMapping
    public GetUserDataResponseDto getCurrentUserData() {
        return getCurrentUserUseCase.getCurrentUserData();
    }

    @DeleteMapping
    public void deleteUser() {
        deleteUserUseCase.execute();
    }
}
