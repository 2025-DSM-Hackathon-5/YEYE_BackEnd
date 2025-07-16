package org.example.yeye_backend.domain.video.usecase;

import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.user.domain.User;
import org.example.yeye_backend.domain.user.facade.UserFacade;
import org.example.yeye_backend.domain.video.model.Like;
import org.example.yeye_backend.domain.video.model.LikeId;
import org.example.yeye_backend.domain.video.model.Video;
import org.example.yeye_backend.domain.video.service.CheckLikeService;
import org.example.yeye_backend.domain.video.service.CommandLikeService;
import org.example.yeye_backend.domain.video.service.GetVideoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ToggleLikeUseCase {
    private final UserFacade userFacade;
    private final GetVideoService getVideoService;
    private final CommandLikeService commandLikeService;
    private final CheckLikeService checkLikeService;

    public void execute(UUID videoId) {
        User user = userFacade.getCurrentUser();
        Video video = getVideoService.getVideoById(videoId);

        if (checkLikeService.getCheckLikeExistsByUserAndVideoResult(user, video)) {
            commandLikeService.deleteLikeById(new LikeId(video, user));
            return;
        }

        commandLikeService.saveLike(Like.builder()
                .user(user)
                .video(video)
                .build()
        );
    }
}
