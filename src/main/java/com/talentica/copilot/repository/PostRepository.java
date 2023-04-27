package com.talentica.copilot.repository;

import com.talentica.copilot.model.Post;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {

  Page<Post> findAllByUserId(Long userId, PageRequest pageRequest);

  @Query(value = "SELECT DATE_TRUNC(?1, p.created_time) AS time, COUNT(*) AS count FROM posts p WHERE p.created_time BETWEEN ?2 AND ?3 GROUP BY time ORDER BY time", nativeQuery = true)
  List<Object[]> getPostsTrend(String interval, LocalDateTime fromDateTime, LocalDateTime toDateTime);

  List<Post> findAllByUserId(Long userId);
}
