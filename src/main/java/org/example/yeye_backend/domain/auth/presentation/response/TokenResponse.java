package org.example.yeye_backend.domain.auth.presentation.response;

import lombok.Builder;
import java.time.ZonedDateTime;

@Builder
public record TokenResponse(
        String accessToken,
        ZonedDateTime accessExpiredAt,
        String refreshToken,
        ZonedDateTime refreshExpiredAt
) { }

