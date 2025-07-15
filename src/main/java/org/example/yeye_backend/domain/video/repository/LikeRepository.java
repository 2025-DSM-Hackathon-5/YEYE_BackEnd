package org.example.yeye_backend.domain.video.repository;

import org.example.yeye_backend.domain.video.model.Like;
import org.example.yeye_backend.domain.video.model.LikeId;
import org.springframework.data.repository.CrudRepository;

public interface LikeRepository extends CrudRepository<Like, LikeId> {
}
