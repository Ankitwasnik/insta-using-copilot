package com.talentica.copilot.repository;

import com.talentica.copilot.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  int countByPostIdAndUserIdNot(Long postId, Long userId);
}
