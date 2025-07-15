package org.example.yeye_backend.domain.auth.presentation.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
        @NotBlank
        String accountId,
        @NotBlank
        String password
) { }
