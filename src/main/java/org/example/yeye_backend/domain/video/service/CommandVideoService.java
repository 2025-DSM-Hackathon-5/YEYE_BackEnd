package org.example.yeye_backend.domain.video.service;

import org.example.yeye_backend.domain.video.model.Video;

import java.util.UUID;

public interface CommandVideoService {
    UUID saveVideo(Video video);

    void deleteVideoById(UUID videoId);
}
