package org.example.yeye_backend.domain.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Builder
@DynamicInsert
@Entity(name = "tbl_user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(10)")
    private String accountId;

    @Column(nullable = false)
    @ColumnDefault("'https://songju-bucket.s3.ap-northeast-2.amazonaws.com/DefaultProfile.png'")
    private String profileImageUrl;

    @Column(nullable = false, columnDefinition = "VARCHAR(8)")
    private String name;

    @Column(nullable = false)
    private String password;
}
