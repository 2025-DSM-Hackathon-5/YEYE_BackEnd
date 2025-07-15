package org.example.yeye_backend.domain.video.repository;

import org.example.yeye_backend.domain.video.model.Video;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface VideoRepository extends CrudRepository<Video, UUID> {
}
