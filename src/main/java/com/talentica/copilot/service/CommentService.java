package com.talentica.copilot.service;

import com.talentica.copilot.dto.CommentResponse;

public interface CommentService {

  CommentResponse commentOnPost(Long postId, Long userId, String comment);
}
