package org.example.yeye_backend.domain.video.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.video.model.Video;
import org.example.yeye_backend.domain.video.repository.VideoRepository;
import org.example.yeye_backend.domain.video.service.CommandVideoService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommandVideoServiceImpl implements CommandVideoService {
    private final VideoRepository videoRepository;

    @Override
    public UUID saveVideo(Video video) {
        return videoRepository.save(video).getVideoId();
    }

    @Override
    public void deleteVideoById(UUID videoId) {
        videoRepository.deleteById(videoId);
    }
}
