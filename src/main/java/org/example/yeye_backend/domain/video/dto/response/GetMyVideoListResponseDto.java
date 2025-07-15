package org.example.yeye_backend.domain.video.dto.response;

import org.example.yeye_backend.domain.video.repository.vo.VideoListItemVO;

import java.util.List;

public record GetMyVideoListResponseDto(
    List<VideoListItemVO> videos
) {
}
