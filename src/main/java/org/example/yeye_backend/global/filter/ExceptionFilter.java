package org.example.yeye_backend.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.yeye_backend.global.exception.ExceptionResponse;
import org.example.yeye_backend.global.exception.GlobalBusinessException;
import org.example.yeye_backend.global.exception.GlobalErrorCode;
import org.example.yeye_backend.global.exception.generalExceptions.errorCode.GeneralErrorCode;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class ExceptionFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (GlobalBusinessException e) {
            convertErrorToJson(response, e.errorCode);
        } catch (Exception e) {
            log.error(e.getMessage());
            convertErrorToJson(response, GeneralErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    private void convertErrorToJson(HttpServletResponse response, GlobalErrorCode errorCode) throws IOException {
        response.setStatus(errorCode.getErrorCode());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(ExceptionResponse.of(errorCode)));
    }
}
