package com.talentica.copilot.controller;

import com.talentica.copilot.dto.ReactionResponse;
import com.talentica.copilot.enums.ReactionType;
import com.talentica.copilot.service.ReactionService;
import com.talentica.copilot.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/posts/{postId}/reactions")
@RequiredArgsConstructor
public class ReactionController {

  private final ReactionService reactionService;

  // API to react on the post
  @PostMapping()
  public ResponseEntity<ReactionResponse> reactOnPost(@PathVariable Long postId,
      @RequestParam ReactionType reaction) {
    ReactionResponse reactionResponse = reactionService.reactOnPost(postId, SecurityUtil.getUserId(), reaction);
    return new ResponseEntity<>(reactionResponse, HttpStatus.CREATED);
  }
}
