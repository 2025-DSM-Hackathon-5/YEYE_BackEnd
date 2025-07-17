package org.example.yeye_backend.domain.video.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.video.repository.LikeRepository;
import org.example.yeye_backend.domain.video.service.GetLikeService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetLikeServiceImpl implements GetLikeService {
    private final LikeRepository likeRepository;

    @Override
    public int getLikeCntByVideoId(UUID videoId) {
        return likeRepository.countByVideoVideoId(videoId);
    }
}
