package org.example.yeye_backend.domain.video.service;

import org.example.yeye_backend.domain.video.model.Video;
import org.example.yeye_backend.domain.video.repository.vo.VideoAndWriterData;

import java.util.List;
import java.util.UUID;

public interface GetVideoService {
    Video getVideoById(UUID videoId);

    List<UUID> getIdList();

    VideoAndWriterData getVideoAndWriterDataByVideoId(UUID videoId);
}
