package com.talentica.copilot.repository;

import com.talentica.copilot.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
