package org.example.yeye_backend.domain.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.auth.domain.RefreshToken;
import org.example.yeye_backend.domain.auth.domain.repository.RefreshTokenRepository;
import org.example.yeye_backend.domain.auth.exception.InvalidRefreshTokenException;
import org.example.yeye_backend.domain.auth.exception.RefreshTokenNotFoundException;
import org.example.yeye_backend.domain.auth.presentation.response.TokenResponse;
import org.example.yeye_backend.global.security.jwt.JwtProperties;
import org.example.yeye_backend.global.security.jwt.JwtTokenProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class ReissueService {
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProperties jwtProperties;


    @Transactional
    public TokenResponse reissue(HttpServletRequest request){
        String parseToken = jwtTokenProvider.resolveToken(request);
        if(parseToken == null){
            throw InvalidRefreshTokenException.EXCEPTION;
        }

        RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(parseToken)
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        String accountId = refreshToken.getAccountId();
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(accountId);
        refreshToken.update(newRefreshToken, jwtProperties.refreshExp());
        refreshTokenRepository.save(refreshToken);

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(accountId))
                .accessExpiredAt(now.plusSeconds(jwtProperties.accessExp()))
                .refreshToken(newRefreshToken)
                .refreshExpiredAt(now.plusSeconds(jwtProperties.refreshExp()))
                .build();
    }
}
