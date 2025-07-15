package org.example.yeye_backend.domain.video.model;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.user.domain.User;

import java.io.Serializable;

@EqualsAndHashCode
@RequiredArgsConstructor
public class LikeId implements Serializable {
    private final Video video;
    private final User user;
}
