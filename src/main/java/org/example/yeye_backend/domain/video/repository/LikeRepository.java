package org.example.yeye_backend.domain.video.repository;

import org.example.yeye_backend.domain.video.model.Like;
import org.example.yeye_backend.domain.video.model.LikeId;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface LikeRepository extends CrudRepository<Like, LikeId> {

    int countByVideoVideoId(UUID videoId);
}
