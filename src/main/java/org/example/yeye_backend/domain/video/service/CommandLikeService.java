package org.example.yeye_backend.domain.video.service;

import org.example.yeye_backend.domain.video.model.Like;
import org.example.yeye_backend.domain.video.model.LikeId;

public interface CommandLikeService {
    void saveLike(Like like);

    void deleteLikeById(LikeId likeId);
}
