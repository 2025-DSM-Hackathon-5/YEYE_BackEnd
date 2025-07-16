package org.example.yeye_backend.domain.chat.domain.repository;

import org.springframework.data.repository.query.Param;
import org.example.yeye_backend.domain.chat.domain.ChatMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    @Query("""
    SELECT m FROM ChatMessage m 
    WHERE m.createdAt < :createdAt 
      AND m.role = 'user'
      AND m.user.accountId = :userId
    ORDER BY m.createdAt DESC
""")
    List<ChatMessage> findRecentUserMessagesByCreatedAtAndUserId(
            @org.springframework.data.repository.query.Param("createdAt") java.time.LocalDateTime createdAt,
            @org.springframework.data.repository.query.Param("userId") String userId,
            Pageable pageable
    );

    @Query("""
    SELECT m FROM ChatMessage m
    WHERE m.user.accountId = :userId
    ORDER BY m.createdAt DESC
""")
    List<ChatMessage> findByUserIdOrderByCreatedAtDesc(@Param("userId") String userId);




}
