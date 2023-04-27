package com.talentica.copilot.controller;

import com.talentica.copilot.dto.CommentResponse;
import com.talentica.copilot.service.CommentService;
import com.talentica.copilot.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Controller to handle comments on the posts
@RestController
@RequestMapping("/v1/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

  private final CommentService commentService;

  // API to comment on the post
  @PostMapping()
  public ResponseEntity<CommentResponse> commentOnPost(@PathVariable Long postId,
      @RequestParam String comment) {
    CommentResponse commentResponse = commentService.commentOnPost(postId, SecurityUtil.getUserId(), comment);
    return new ResponseEntity<>(commentResponse, HttpStatus.CREATED);
  }

}
