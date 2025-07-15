package org.example.yeye_backend.domain.video.usecase;

import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.common.service.FileUploadService;
import org.example.yeye_backend.domain.user.domain.User;
import org.example.yeye_backend.domain.user.domain.facade.UserFacade;
import org.example.yeye_backend.domain.video.dto.request.CreateVideoRequestDto;
import org.example.yeye_backend.domain.video.model.Video;
import org.example.yeye_backend.domain.video.service.CommandVideoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CreateVideoUseCase {
    private final UserFacade userFacade;
    private final FileUploadService fileUploadService;
    private final CommandVideoService commandVideoService;

    public void execute(MultipartFile video, MultipartFile thumbnail, CreateVideoRequestDto request) {
        String videoUrl = fileUploadService.uploadFile(video);
        String thumbnailUrl = fileUploadService.uploadFile(thumbnail);
        User user = userFacade.getCurrentUser();

        commandVideoService.saveVideo(Video.builder()
                        .title(request.title())
                        .videoUrl(videoUrl)
                        .thumbnailUrl(thumbnailUrl)
                        .user(user)
                        .build()
        );
    }
}
