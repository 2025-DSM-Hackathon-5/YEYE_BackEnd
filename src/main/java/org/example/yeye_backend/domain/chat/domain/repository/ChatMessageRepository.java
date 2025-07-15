package org.example.yeye_backend.domain.chat.domain.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.example.yeye_backend.domain.chat.domain.ChatMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    @Query("""
    SELECT m FROM ChatMessage m 
    WHERE m.createdAt < :createdAt 
      AND m.role = 'user'
      AND m.user.id = :userId
    ORDER BY m.createdAt DESC
""")
    List<ChatMessage> findRecentUserMessagesByCreatedAtAndUserId(
            @org.springframework.data.repository.query.Param("createdAt") java.time.LocalDateTime createdAt,
            @org.springframework.data.repository.query.Param("userId") Long userId,
            Pageable pageable
    );

    @Query("""
    SELECT m FROM ChatMessage m
    WHERE m.user.id = :userId
    ORDER BY m.createdAt DESC
""")
    List<ChatMessage> findByUserIdOrderByCreatedAtDesc(@Param("userId") Long userId);




}
