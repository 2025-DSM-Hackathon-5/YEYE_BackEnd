package org.example.yeye_backend.domain.auth.domain;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Builder
@RedisHash
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {
    @Id
    private String accountId;

    @Indexed
    private String refreshToken;

    @TimeToLive
    private Long ttl;

    public void update(String newToken, Long newTtl){
        this.refreshToken = newToken;
        this.ttl = newTtl;
    }
}
