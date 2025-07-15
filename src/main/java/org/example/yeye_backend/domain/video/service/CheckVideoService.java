package org.example.yeye_backend.domain.video.service;

import org.example.yeye_backend.domain.user.domain.User;
import org.example.yeye_backend.domain.video.model.Video;

public interface CheckVideoService {
    void CheckUserIsOwnerOfVideo(User user, Video video);
}
