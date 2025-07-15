package org.example.yeye_backend.domain.video.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.yeye_backend.domain.user.domain.User;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Entity(name = "tbl_like")
@IdClass(LikeId.class)
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
public class Like {
    @Id
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(targetEntity = User.class, optional = false)
    @JoinColumn(referencedColumnName = "accountId", name = "accountId", nullable = false)
    private User user;

    @Id
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(targetEntity = Video.class, optional = false)
    @JoinColumn(referencedColumnName = "videoId", name = "videoId", nullable = false)
    private Video video;
}
