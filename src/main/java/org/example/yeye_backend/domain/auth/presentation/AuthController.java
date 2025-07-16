package org.example.yeye_backend.domain.auth.presentation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.auth.presentation.request.LoginRequestDto;
import org.example.yeye_backend.domain.auth.presentation.request.RegisterRequestDto;
import org.example.yeye_backend.domain.auth.presentation.response.TokenResponse;
import org.example.yeye_backend.domain.auth.service.LoginService;
import org.example.yeye_backend.domain.auth.service.ReissueService;
import org.example.yeye_backend.domain.auth.service.SignupService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthController {
    private final SignupService signupService;
    private final LoginService loginService;
    private final ReissueService reissueService;

    @PostMapping("/signup")
    public void signUp(
        @RequestPart("profile") MultipartFile profileImage,
        @RequestPart("body") @Valid RegisterRequestDto dto
    ){
        signupService.signup(profileImage, dto);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid LoginRequestDto dto){
        return loginService.login(dto);
    }

    @PostMapping("/reissue")
    public TokenResponse reissue(HttpServletRequest request){
        return reissueService.reissue(request);
    }
}
