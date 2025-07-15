package org.example.yeye_backend.domain.video.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.video.dto.request.CreateVideoRequestDto;
import org.example.yeye_backend.domain.video.usecase.CreateVideoUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
public class VideoController {
    private final CreateVideoUseCase createVideoUseCase;

    @PostMapping
    public void createVideo(
        @RequestPart("video") MultipartFile video,
        @RequestPart("thumbnail") MultipartFile thumbnail,
        @RequestPart("body") @Valid CreateVideoRequestDto request
    ) {
        createVideoUseCase.execute(video, thumbnail, request);
    }
}
