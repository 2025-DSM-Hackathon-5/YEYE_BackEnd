package org.example.yeye_backend.global.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.auth.domain.RefreshToken;
import org.example.yeye_backend.domain.auth.domain.repository.RefreshTokenRepository;
import org.example.yeye_backend.domain.user.domain.User;
import org.example.yeye_backend.domain.user.domain.repository.UserRepository;
import org.example.yeye_backend.global.security.auth.AuthDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtProperties.secretKey().getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String accountId, String type, Long exp){
        return Jwts.builder()
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .setSubject(accountId)
                .setHeaderParam("type", type)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .compact();
    }

    public String generateAccessToken(String accountId){
        return generateToken(accountId, "access", jwtProperties.accessExp());
    }

    public String generateRefreshToken(String accountId){
        String refreshToken = generateToken(accountId, "refresh", jwtProperties.refreshExp());
        refreshTokenRepository.save(RefreshToken.builder()
                .accountId(accountId)
                .refreshToken(refreshToken)
                .ttl(jwtProperties.refreshExp())
                .build());
        return refreshToken;
    }

    public String parseToken(String bearerToken){
        if(bearerToken != null && bearerToken.startsWith(jwtProperties.prefix())){
            return bearerToken.replace(jwtProperties.prefix(), "").trim();
        }
        return null;
    }

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(jwtProperties.header());
        return parseToken(bearerToken);
    }

    public Claims getTokenBody(String token){
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (io.jsonwebtoken.ExpiredJwtException e){
            throw new RuntimeException(e); // TODO : 알맞은 EXCEPTION 추가
        } catch (Exception e){
            throw new RuntimeException(e); // TODO : 알맞은 EXCEPTION 추가
        }
    }

    public String getTokenSubject(String token){
        return getTokenBody(token).getSubject();
    }

    public Authentication getAuthentication(String token){
        String accountId = getTokenSubject(token);
        User user = userRepository.findByAccountId(accountId)
                .orElseThrow(() -> new UsernameNotFoundException("인증 실패"));
        UserDetails userDetails = new AuthDetails(user);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
