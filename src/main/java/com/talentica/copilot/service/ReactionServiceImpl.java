package com.talentica.copilot.service;

import com.talentica.copilot.dto.ReactionResponse;
import com.talentica.copilot.enums.ReactionType;
import com.talentica.copilot.exception.ResourceNotFoundException;
import com.talentica.copilot.model.Post;
import com.talentica.copilot.model.Reaction;
import com.talentica.copilot.model.User;
import com.talentica.copilot.repository.PostRepository;
import com.talentica.copilot.repository.ReactionRepository;
import com.talentica.copilot.repository.UserRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReactionServiceImpl implements ReactionService {

  private final ReactionRepository reactionRepository;
  private final PostRepository postRepository;
  private final UserRepository userRepository;

  @Override
  @Transactional
  public ReactionResponse reactOnPost(Long postId, Long userId, ReactionType reactionType) {
    log.info("Reacting on post with id: {} by user with id: {}", postId, userId);
    Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    Reaction reaction = reactionRepository.findByPostAndUser(post, user).orElse(new Reaction());
    reaction.setPost(post);
    reaction.setUser(user);
    reaction.setType(reactionType);
    Reaction savedReaction = reactionRepository.save(reaction);
    return mapToReactionResponse(savedReaction);
  }

  private ReactionResponse mapToReactionResponse(Reaction savedReaction) {
    ReactionResponse reactionResponse = new ReactionResponse();
    reactionResponse.setId(savedReaction.getId());
    reactionResponse.setReaction(savedReaction.getType());
    reactionResponse.setPostId(savedReaction.getPost().getId());
    reactionResponse.setUserId(savedReaction.getUser().getId());
    return reactionResponse;
  }
}
