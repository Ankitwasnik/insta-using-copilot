package com.talentica.copilot.service;

import com.talentica.copilot.dto.CommentResponse;
import com.talentica.copilot.exception.ResourceNotFoundException;
import com.talentica.copilot.model.Comment;
import com.talentica.copilot.model.Post;
import com.talentica.copilot.model.User;
import com.talentica.copilot.repository.CommentRepository;
import com.talentica.copilot.repository.PostRepository;
import com.talentica.copilot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;
  private final PostRepository postRepository;
  private final UserRepository userRepository;

  @Override
  @Transactional
  public CommentResponse commentOnPost(Long postId, Long userId, String text) {
    log.info("Commenting on post with id: {} by user with id: {}", postId, userId);
    Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    Comment comment = new Comment();
    comment.setText(text);
    comment.setUser(user);
    comment.setPost(post);
    Comment savedComment = commentRepository.save(comment);
    return mapToCommentResponse(savedComment);
  }

  private static CommentResponse mapToCommentResponse(Comment savedComment) {
    CommentResponse commentResponse = new CommentResponse();
    commentResponse.setId(savedComment.getId());
    commentResponse.setComment(savedComment.getText());
    commentResponse.setPostId(savedComment.getPost().getId());
    commentResponse.setUserId(savedComment.getUser().getId());
    return commentResponse;
  }
}
