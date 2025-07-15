package org.example.yeye_backend.domain.video.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.video.model.Like;
import org.example.yeye_backend.domain.video.model.LikeId;
import org.example.yeye_backend.domain.video.repository.LikeRepository;
import org.example.yeye_backend.domain.video.service.CommandLikeService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandLikeServiceImpl implements CommandLikeService {
    private final LikeRepository likeRepository;

    @Override
    public void saveLike(Like like) {
        likeRepository.save(like);
    }

    @Override
    public void deleteLikeById(LikeId likeId) {
        likeRepository.deleteById(likeId);
    }
}
