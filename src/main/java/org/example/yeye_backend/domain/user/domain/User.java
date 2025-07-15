package org.example.yeye_backend.domain.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Getter
@Builder
@Table(name = "tbl_user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String profileImageUrl;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(10)")
    private String accountId;

    @Column(nullable = false, columnDefinition = "VARCHAR(8)")
    private String name;

    @Column(nullable = false)
    private String password;
}
