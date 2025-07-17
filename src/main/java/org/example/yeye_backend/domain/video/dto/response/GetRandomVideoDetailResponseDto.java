package org.example.yeye_backend.domain.video.dto.response;

import org.example.yeye_backend.domain.video.repository.vo.VideoAndWriterData;

import java.util.UUID;

public record GetRandomVideoDetailResponseDto(
    UUID videoId,
    String videoUrl,
    String thumbnailUrl,
    String title,
    String writer,
    String writerProfile,
    boolean isLiked,
    int likeCnt
) {
    public static GetRandomVideoDetailResponseDto of(VideoAndWriterData data, boolean isLiked, int likeCnt) {
        return new GetRandomVideoDetailResponseDto(
                data.videoId(),
                data.videoUrl(),
                data.thumbnailUrl(),
                data.title(),
                data.writer(),
                data.writerProfile(),
                isLiked,
                likeCnt
        );
    }
}
