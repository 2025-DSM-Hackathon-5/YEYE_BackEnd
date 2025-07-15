package org.example.yeye_backend.global.configration;

import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.global.security.jwt.JwtTokenFilter;
import org.example.yeye_backend.global.security.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(authorization -> {
                    // user
                    authorization
                        .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user/signup").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/user/detail").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/user").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/user/password").authenticated()
                        .requestMatchers(HttpMethod.GET, "/user").authenticated();

                    // video
                    authorization
                        .requestMatchers(HttpMethod.POST, "/video").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/video/:video").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/video/:video").authenticated()
                        .requestMatchers(HttpMethod.GET, "/video").authenticated()
                        .requestMatchers(HttpMethod.GET, "/video/my").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/video/:video").authenticated();

                    // chat

                    authorization.anyRequest().denyAll();
                })

                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })

                .headers(header -> {
                    header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin);
                })

                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)

                .build();
    }
}
