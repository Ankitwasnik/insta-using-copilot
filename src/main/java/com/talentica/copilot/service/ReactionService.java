package com.talentica.copilot.service;

import com.talentica.copilot.dto.ReactionResponse;
import com.talentica.copilot.enums.ReactionType;

public interface ReactionService {

  ReactionResponse reactOnPost(Long postId, Long userId, ReactionType reaction);
}
