package org.example.yeye_backend.domain.video.repository.vo;

import java.util.UUID;

public record VideoAndWriterData(
    UUID videoId,
    String videoUrl,
    String thumbnailUrl,
    String title,
    String writer,
    String writerProfile
) {
}
