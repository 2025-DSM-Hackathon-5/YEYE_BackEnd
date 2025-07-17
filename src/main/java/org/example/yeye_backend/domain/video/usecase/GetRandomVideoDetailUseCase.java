package org.example.yeye_backend.domain.video.usecase;

import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.user.domain.User;
import org.example.yeye_backend.domain.user.facade.UserFacade;
import org.example.yeye_backend.domain.video.dto.response.GetRandomVideoDetailResponseDto;
import org.example.yeye_backend.domain.video.model.Video;
import org.example.yeye_backend.domain.video.repository.vo.VideoAndWriterData;
import org.example.yeye_backend.domain.video.service.CheckLikeService;
import org.example.yeye_backend.domain.video.service.GetLikeService;
import org.example.yeye_backend.domain.video.service.GetVideoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetRandomVideoDetailUseCase {
    private final UserFacade userFacade;
    private final GetLikeService getLikeService;
    private final GetVideoService getVideoService;
    private final CheckLikeService checkLikeService;

    public GetRandomVideoDetailResponseDto execute() {
        User user = userFacade.getCurrentUser();
        List<UUID> videoIdList = getVideoService.getIdList();

        Random random = new Random();
        UUID randomId = videoIdList.get(random.nextInt(videoIdList.size()));

        Video video = getVideoService.getVideoById(randomId);

        int likeCnt = getLikeService.getLikeCntByVideoId(randomId);
        VideoAndWriterData data = getVideoService.getVideoAndWriterDataByVideoId(randomId);
        boolean isLiked = checkLikeService.getCheckLikeExistsByUserAndVideoResult(user, video);

        return GetRandomVideoDetailResponseDto.of(data, isLiked, likeCnt);
    }
}
