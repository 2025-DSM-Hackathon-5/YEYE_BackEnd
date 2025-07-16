package org.example.yeye_backend.domain.chat.domain;


import jakarta.persistence.*;
import lombok.*;
import org.example.yeye_backend.domain.user.domain.User;

@Entity
@Table(name = "presets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Preset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "system_prompt", columnDefinition = "TEXT")
    private String systemPrompt;

    @Column(name = "profile", columnDefinition = "VARCHAR(255)")
    private String profile;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;
}
