package org.example.yeye_backend.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.auth.exception.PasswordMisMatchException;
import org.example.yeye_backend.domain.auth.presentation.request.LoginRequestDto;
import org.example.yeye_backend.domain.auth.presentation.response.TokenResponse;
import org.example.yeye_backend.domain.user.domain.User;
import org.example.yeye_backend.domain.user.domain.exception.UserNotFoundException;
import org.example.yeye_backend.domain.user.domain.repository.UserRepository;
import org.example.yeye_backend.global.security.jwt.JwtProperties;
import org.example.yeye_backend.global.security.jwt.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;

    @Transactional(readOnly = true)
    public TokenResponse login(LoginRequestDto dto){
        User user = userRepository.findByAccountId(dto.accountId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if(!passwordEncoder.matches(dto.password(), user.getPassword())){
            throw PasswordMisMatchException.EXCEPTION;
        }

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(dto.accountId()))
                .accessExpiredAt(now.plusSeconds(jwtProperties.accessExp()))
                .refreshToken(jwtTokenProvider.generateRefreshToken(dto.accountId()))
                .refreshExpiredAt(now.plusSeconds(jwtProperties.refreshExp()))
                .build();
    }
}
