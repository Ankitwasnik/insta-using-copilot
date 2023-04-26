package com.talentica.copilot.service;

import com.talentica.copilot.dto.CreatePostRequest;
import com.talentica.copilot.dto.PostDto;
import com.talentica.copilot.dto.PostTrendDto;
import com.talentica.copilot.enums.Interval;
import com.talentica.copilot.exception.ResourceNotFoundException;
import com.talentica.copilot.model.Post;
import com.talentica.copilot.model.User;
import com.talentica.copilot.repository.PostRepository;
import com.talentica.copilot.util.SecurityUtil;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;
  private final UserService userService;

  @Override
  // Create a new post
  public PostDto createPost(CreatePostRequest request) {
    log.info("Creating a new post for user: {}", SecurityUtil.getUserId());
    log.debug("Request: {}", request);

    Post post = mapToEntity(request, userService.getLoggedInUser());
    Post savedPost = postRepository.save(post);
    return mapToDto(savedPost);
  }

  @Override
  public PostDto getPostById(Long id) {
     log.info("Getting post by id: {}", id);
    Post post = postRepository.findById(id).orElseThrow(() -> {
      log.error("Post not found with id: {}", id);
      return new ResourceNotFoundException("Post not found");
    });
    return mapToDto(post);
  }

  @Override
  public Page<PostDto> getAllPosts(int page, int size) {
    log.info("Getting all posts");
    Page<Post> posts = postRepository.findAllByUserId(SecurityUtil.getUserId(), PageRequest.of(page, size));
    return posts.map(this::mapToDto);
  }

  @Override
  // aggregate the number of posts for each interval and map to PostTrendDto
  public List<PostTrendDto> getPostsTrend(Interval interval, Instant from, Instant to) {
    log.info("Getting posts trend");
    String intervalString = interval.toString().toLowerCase();
    LocalDateTime fromDateTime = LocalDateTime.ofInstant(from, ZoneId.of("UTC"));
    LocalDateTime toDateTime = LocalDateTime.ofInstant(to, ZoneId.of("UTC"));
    List<Object[]> posts = postRepository.getPostsTrend(intervalString, fromDateTime, toDateTime);
    return mapToPostTrendDto(posts);
  }

  private List<PostTrendDto> mapToPostTrendDto(List<Object[]> posts) {
    return posts.stream()
        .map(post -> {
          PostTrendDto dto = new PostTrendDto();
          dto.setTime(((Timestamp) post[0]).toInstant());
          dto.setCount((Long) post[1]);
          return dto;
        })
        .toList();
  }

  private PostDto mapToDto(Post post) {
    return PostDto.builder()
        .id(post.getId())
        .caption(post.getCaption())
        .mediaUrl(post.getMediaUrl())
        .build();
  }

  private Post mapToEntity(CreatePostRequest request, User user) {
    return Post.builder()
        .user(user)
        .caption(request.getCaption())
        .mediaUrl(request.getMediaUrl())
        .build();
  }


}
