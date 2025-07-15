package org.example.yeye_backend.domain.video.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.video.dto.request.CreateVideoRequestDto;
import org.example.yeye_backend.domain.video.dto.request.UpdateVideoRequestDto;
import org.example.yeye_backend.domain.video.dto.response.GetRandomVideoDetailResponseDto;
import org.example.yeye_backend.domain.video.usecase.CreateVideoUseCase;
import org.example.yeye_backend.domain.video.usecase.DeleteVideoUseCase;
import org.example.yeye_backend.domain.video.usecase.GetRandomVideoDetailUseCase;
import org.example.yeye_backend.domain.video.usecase.UpdateVideoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
public class VideoController {
    private final CreateVideoUseCase createVideoUseCase;
    private final DeleteVideoUseCase deleteVideoUseCase;
    private final UpdateVideoUseCase updateVideoUseCase;
    private final GetRandomVideoDetailUseCase getRandomVideoDetailUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createVideo(
        @RequestPart("video") MultipartFile video,
        @RequestPart("thumbnail") MultipartFile thumbnail,
        @RequestPart("body") @Valid CreateVideoRequestDto request
    ) {
        createVideoUseCase.execute(video, thumbnail, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{videoId}")
    public void deleteVideo(@PathVariable UUID videoId) {
        deleteVideoUseCase.execute(videoId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{videoId}")
    public void updateVideo(
        @PathVariable UUID videoId,
        @RequestBody @Valid UpdateVideoRequestDto request
    ) {
        updateVideoUseCase.execute(videoId, request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public GetRandomVideoDetailResponseDto getRandomVideoDetail() {
        return getRandomVideoDetailUseCase.execute();
    }
}
