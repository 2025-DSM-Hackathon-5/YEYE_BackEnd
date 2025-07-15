package org.example.yeye_backend.domain.video.repository.vo;

import java.util.UUID;

public record VideoListItemVO(
    UUID videoId,
    String videoUrl,
    String thumbnailUrl,
    String title
) {
}
