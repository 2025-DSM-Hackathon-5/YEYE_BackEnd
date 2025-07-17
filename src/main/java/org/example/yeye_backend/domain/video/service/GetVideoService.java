package org.example.yeye_backend.domain.video.service;

import org.example.yeye_backend.domain.user.domain.User;
import org.example.yeye_backend.domain.video.model.Video;
import org.example.yeye_backend.domain.video.repository.vo.VideoAndWriterData;
import org.example.yeye_backend.domain.video.repository.vo.VideoListItemVO;

import java.util.List;
import java.util.UUID;

public interface GetVideoService {
    Video getVideoById(UUID videoId);

    List<UUID> getIdList();

    VideoAndWriterData getVideoAndWriterDataByVideoId(UUID videoId);

    List<VideoListItemVO> getVideoListByUser(User user);

    List<Video> getAllVideo();

    List<String> getLikedVideoThumbnails(User user);
}
