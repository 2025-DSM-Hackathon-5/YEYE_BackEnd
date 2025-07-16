package org.example.yeye_backend.domain.chat.domain.repository;

import org.example.yeye_backend.domain.chat.domain.Preset;
import org.example.yeye_backend.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PresetRepository extends JpaRepository<Preset, String> {
    Optional<Preset> findByUser(User user);
    Optional<Preset> findByUserAccountId(String userId);

}
