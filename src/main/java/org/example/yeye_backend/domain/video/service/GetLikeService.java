package org.example.yeye_backend.domain.video.service;

import java.util.UUID;

public interface GetLikeService {
    int getLikeCntByVideoId(UUID videoId);
}
