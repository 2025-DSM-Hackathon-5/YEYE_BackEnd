package org.example.yeye_backend.domain.video.usecase;

import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.user.domain.User;
import org.example.yeye_backend.domain.user.domain.facade.UserFacade;
import org.example.yeye_backend.domain.video.model.Video;
import org.example.yeye_backend.domain.video.service.CheckVideoService;
import org.example.yeye_backend.domain.video.service.CommandVideoService;
import org.example.yeye_backend.domain.video.service.GetVideoService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteVideoUseCase {
    private final UserFacade userFacade;
    private final GetVideoService getVideoService;
    private final CheckVideoService checkVideoService;
    private final CommandVideoService commandVideoService;

    public void execute(UUID videoId) {
        User user = userFacade.getCurrentUser();
        Video video = getVideoService.getVideoById(videoId);

        checkVideoService.CheckUserIsOwnerOfVideo(user, video);

        commandVideoService.deleteVideoById(videoId);
    }
}
