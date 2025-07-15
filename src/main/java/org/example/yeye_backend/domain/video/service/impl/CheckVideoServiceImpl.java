package org.example.yeye_backend.domain.video.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.user.domain.User;
import org.example.yeye_backend.domain.video.exception.IsNotOwnerOfVideoException;
import org.example.yeye_backend.domain.video.model.Video;
import org.example.yeye_backend.domain.video.service.CheckVideoService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckVideoServiceImpl implements CheckVideoService {

    @Override
    public void CheckUserIsOwnerOfVideo(User user, Video video) {
        if (user.getAccountId().equals(video.getUser().getAccountId())) {
            throw IsNotOwnerOfVideoException.EXCEPTION;
        }
    }
}
