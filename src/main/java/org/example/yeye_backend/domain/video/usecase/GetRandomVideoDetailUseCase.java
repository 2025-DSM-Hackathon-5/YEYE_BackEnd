package org.example.yeye_backend.domain.video.usecase;

import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.user.domain.User;
import org.example.yeye_backend.domain.user.facade.UserFacade;
import org.example.yeye_backend.domain.video.dto.response.GetRandomVideoDetailResponseDto;
import org.example.yeye_backend.domain.video.model.Video;
import org.example.yeye_backend.domain.video.service.CheckLikeService;
import org.example.yeye_backend.domain.video.service.GetLikeService;
import org.example.yeye_backend.domain.video.service.GetVideoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetRandomVideoDetailUseCase {
    private final UserFacade userFacade;
    private final GetLikeService getLikeService;
    private final GetVideoService getVideoService;
    private final CheckLikeService checkLikeService;

    public List<GetRandomVideoDetailResponseDto> execute() {
        User user = userFacade.getCurrentUser();

        List<Video> videoList = getVideoService.getAllVideo();

        List<GetRandomVideoDetailResponseDto> response = videoList.stream()
                .map(video -> GetRandomVideoDetailResponseDto.of(
                        getVideoService.getVideoAndWriterDataByVideoId(video.getVideoId()),
                        checkLikeService.getCheckLikeExistsByUserAndVideoResult(user, video),
                        getLikeService.getLikeCntByVideoId(video.getVideoId())
                ))
                .toList();

        return response;
    }
}
