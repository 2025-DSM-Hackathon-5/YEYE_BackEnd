package org.example.yeye_backend.domain.user.domain.dto.response;

import org.example.yeye_backend.domain.user.domain.User;

public record GetUserDataResponseDto(
    String accountId,
    String name,
    String profile
) {
    public static GetUserDataResponseDto of(User user) {
        return new GetUserDataResponseDto(
            user.getAccountId(),
            user.getName(),
            user.getProfileImageUrl()
        );
    }
}
