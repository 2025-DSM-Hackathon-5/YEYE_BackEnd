package org.example.yeye_backend.domain.video.repository;

import org.example.yeye_backend.domain.user.domain.User;
import org.example.yeye_backend.domain.video.model.Video;
import org.example.yeye_backend.domain.video.repository.vo.VideoAndWriterData;
import org.example.yeye_backend.domain.video.repository.vo.VideoListItemVO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VideoRepository extends CrudRepository<Video, UUID> {

    @Query("""
        SELECT new org.example.yeye_backend.domain.video.repository.vo.VideoAndWriterData(
            v.videoId,
            v.videoUrl,
            v.thumbnailUrl,
            v.title,
            u.name,
            u.profileImageUrl
        )
        FROM tbl_video v INNER JOIN tbl_user u ON v.user = u
        WHERE v.videoId = :videoId
    """)
    Optional<VideoAndWriterData> getVideoAndWriterDataByVideoId(@Param("videoId") UUID videoId);

    @Query("""
        SELECT v.videoId
        FROM tbl_video v
    """)
    List<UUID> getAllIds();

    @Query("""
        SELECT new org.example.yeye_backend.domain.video.repository.vo.VideoListItemVO(
            v.videoId,
            v.videoUrl,
            v.thumbnailUrl,
            v.title
        )
        FROM tbl_video v
        WHERE v.user = :user
    """)
    List<VideoListItemVO> getVideoListByUser(@Param("user") User user);

    @Query("""
        SELECT v
        FROM tbl_video v
    """)
    List<Video> findAllVideo();
}
