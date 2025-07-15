package org.example.yeye_backend.domain.auth.presentation.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public record RegisterRequestDto(
        MultipartFile profileImage,

        @NotBlank(message = "아이디 입력은 필수 입력값입니다.")
        @Size(max = 10, message = "아이디는 10글자 이하입니다.")
        String accountId,

        @NotBlank(message = "비밀번호는 필수 입력값입니다.")
        @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하여야 합니다.")
        String password,

        @NotBlank(message = "닉네임 입력은 필수 입력값입니다.")
        @Size(max = 8, message = "닉네임은 8글자 이하입니다.")
        String name
){ }
