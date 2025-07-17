package org.example.yeye_backend.domain.video.usecase;

import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.user.domain.User;
import org.example.yeye_backend.domain.user.facade.UserFacade;
import org.example.yeye_backend.domain.video.service.GetVideoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetLikedVideosUseCase {
    private final UserFacade userFacade;
    private final GetVideoService getVideoService;

    public List<String> execute() {
        User user = userFacade.getCurrentUser();

        return getVideoService.getLikedVideoThumbnails(user);
    }
}
