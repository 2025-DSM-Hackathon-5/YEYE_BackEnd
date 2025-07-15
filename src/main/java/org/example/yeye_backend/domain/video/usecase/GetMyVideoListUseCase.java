package org.example.yeye_backend.domain.video.usecase;

import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.user.domain.User;
import org.example.yeye_backend.domain.user.domain.facade.UserFacade;
import org.example.yeye_backend.domain.video.dto.response.GetMyVideoListResponseDto;
import org.example.yeye_backend.domain.video.repository.vo.VideoListItemVO;
import org.example.yeye_backend.domain.video.service.GetVideoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetMyVideoListUseCase {
    private final UserFacade userFacade;
    private final GetVideoService getVideoService;

    public GetMyVideoListResponseDto execute() {
        User user = userFacade.getCurrentUser();
        List<VideoListItemVO> videoList = getVideoService.getVideoListByUser(user);

        return new GetMyVideoListResponseDto(videoList);
    }
}
