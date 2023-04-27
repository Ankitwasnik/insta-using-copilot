package com.talentica.copilot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {

  private Long id;
  private String comment;
  private Long postId;
  private Long userId;
}
