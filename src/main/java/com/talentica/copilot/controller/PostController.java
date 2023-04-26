package com.talentica.copilot.controller;

import com.talentica.copilot.dto.CreatePostRequest;
import com.talentica.copilot.dto.PostDto;
import com.talentica.copilot.dto.PostTrendDto;
import com.talentica.copilot.enums.Interval;
import com.talentica.copilot.service.PostService;
import jakarta.validation.Valid;
import java.net.URI;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

// Rest controller for user's post, comment, like, etc.
@RestController
@RequestMapping("/v1/posts")
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;

  // Create a post
  @PostMapping()
  public ResponseEntity<PostDto> createPost(@RequestBody @Valid CreatePostRequest request) {
    PostDto postDto = postService.createPost(request);
    URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}")
        .buildAndExpand(postDto.getId()).toUri();
    return ResponseEntity.created(location).body(postDto);
  }

  //Get a post by id
  @GetMapping("/{id}")
  public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
    return ResponseEntity.ok(postService.getPostById(id));
  }

  //Get all posts paginated
  @GetMapping()
  public ResponseEntity<Page<PostDto>> getAllPosts(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    Page<PostDto> posts = postService.getAllPosts(page, size);

    return ResponseEntity.ok(posts);
  }

  // Get trend of posts created over time
  @GetMapping("/trend")
  public ResponseEntity<List<PostTrendDto>> getPostsTrend(@RequestParam Interval interval, @RequestParam Instant from, @RequestParam Instant to) {
    List<PostTrendDto> postTrend = postService.getPostsTrend(interval, from, to);
    return ResponseEntity.ok(postTrend);
  }



}
