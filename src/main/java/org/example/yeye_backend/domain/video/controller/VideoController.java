package org.example.yeye_backend.domain.video.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.video.dto.request.CreateVideoRequestDto;
import org.example.yeye_backend.domain.video.dto.request.UpdateVideoRequestDto;
import org.example.yeye_backend.domain.video.dto.response.CreateVideoResponseDto;
import org.example.yeye_backend.domain.video.dto.response.GetMyVideoListResponseDto;
import org.example.yeye_backend.domain.video.dto.response.GetRandomVideoDetailResponseDto;
import org.example.yeye_backend.domain.video.usecase.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
public class VideoController {
    private final CreateVideoUseCase createVideoUseCase;
    private final DeleteVideoUseCase deleteVideoUseCase;
    private final UpdateVideoUseCase updateVideoUseCase;
    private final GetMyVideoListUseCase getMyVideoListUseCase;
    private final GetRandomVideoDetailUseCase getRandomVideoDetailUseCase;
    private final ToggleLikeUseCase toggleLikeUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CreateVideoResponseDto createVideo(
        @RequestPart("video") MultipartFile video,
        @RequestPart("thumbnail") MultipartFile thumbnail,
        @RequestPart("body") @Valid CreateVideoRequestDto request
    ) {
        return createVideoUseCase.execute(video, thumbnail, request);
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
    @GetMapping("/my")
    public GetMyVideoListResponseDto getMyVideoList() {
        return getMyVideoListUseCase.execute();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Map<String, List<GetRandomVideoDetailResponseDto>> getRandomVideoDetail() {
        return Map.of("videos", getRandomVideoDetailUseCase.execute());
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{videoId}")
    public void toggleLike(@PathVariable UUID videoId) {
        toggleLikeUseCase.execute(videoId);
    }
}
