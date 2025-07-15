package org.example.yeye_backend.domain.video.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.video.exception.VideoNotFoundException;
import org.example.yeye_backend.domain.video.model.Video;
import org.example.yeye_backend.domain.video.repository.VideoRepository;
import org.example.yeye_backend.domain.video.service.GetVideoService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetVideoServiceImpl implements GetVideoService {
    private final VideoRepository videoRepository;

    @Override
    public Video getVideoById(UUID videoId) {
        return videoRepository.findById(videoId).orElseThrow(
            () -> VideoNotFoundException.EXCEPTION
        );
    }
}
