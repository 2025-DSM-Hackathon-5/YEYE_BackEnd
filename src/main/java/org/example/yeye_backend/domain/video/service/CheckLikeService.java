package org.example.yeye_backend.domain.video.service;

import org.example.yeye_backend.domain.user.domain.User;
import org.example.yeye_backend.domain.video.model.Video;

import java.util.UUID;

public interface CheckLikeService {
    boolean getCheckLikeExistsByUserAndVideoResult(User user, Video video);
}
