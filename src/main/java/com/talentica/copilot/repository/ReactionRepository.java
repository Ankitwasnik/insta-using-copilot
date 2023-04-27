package com.talentica.copilot.repository;

import com.talentica.copilot.model.Comment;
import com.talentica.copilot.model.Post;
import com.talentica.copilot.model.Reaction;
import com.talentica.copilot.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {

  Optional<Reaction> findByPostAndUser(Post post, User user);

  Long countByPostId(Long postId);
}
