package org.example.yeye_backend.domain.video.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.user.domain.User;
import org.example.yeye_backend.domain.video.exception.VideoNotFoundException;
import org.example.yeye_backend.domain.video.model.Video;
import org.example.yeye_backend.domain.video.repository.VideoRepository;
import org.example.yeye_backend.domain.video.repository.vo.VideoAndWriterData;
import org.example.yeye_backend.domain.video.repository.vo.VideoListItemVO;
import org.example.yeye_backend.domain.video.service.GetVideoService;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public List<UUID> getIdList() {
        return videoRepository.getAllIds();
    }

    @Override
    public VideoAndWriterData getVideoAndWriterDataByVideoId(UUID videoId) {
        return videoRepository.getVideoAndWriterDataByVideoId(videoId).orElseThrow(
                () -> VideoNotFoundException.EXCEPTION
        );
    }

    @Override
    public List<VideoListItemVO> getVideoListByUser(User user) {
        return videoRepository.getVideoListByUser(user);
    }

    @Override
    public List<Video> getAllVideo() {
        return videoRepository.findAllVideo();
    }
}
