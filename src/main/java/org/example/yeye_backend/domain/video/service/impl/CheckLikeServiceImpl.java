package org.example.yeye_backend.domain.video.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.user.domain.User;
import org.example.yeye_backend.domain.video.model.LikeId;
import org.example.yeye_backend.domain.video.model.Video;
import org.example.yeye_backend.domain.video.repository.LikeRepository;
import org.example.yeye_backend.domain.video.service.CheckLikeService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckLikeServiceImpl implements CheckLikeService {
    private final LikeRepository likeRepository;

    public boolean getCheckLikeExistsByUserAndVideoResult(User user, Video video) {
        return likeRepository.existsById(new LikeId(video, user));
    }
}
