package org.example.yeye_backend.domain.user.domain.repository;

import org.example.yeye_backend.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByAccountId(String accountId);
}
