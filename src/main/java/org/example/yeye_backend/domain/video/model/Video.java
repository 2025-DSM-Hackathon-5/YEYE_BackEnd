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

import java.util.UUID;

@Getter
@Entity(name = "tbl_video")
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID videoId;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(targetEntity = User.class, optional = false)
    @JoinColumn(referencedColumnName = "accountId", name = "accountId", nullable = false)
    private User user;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String videoUrl;

    @Column(columnDefinition = "VARCHAR(15)", nullable = false)
    private String title;
}
