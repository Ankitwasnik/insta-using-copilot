package com.talentica.copilot.dto;

import com.talentica.copilot.enums.ReactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReactionResponse {

  private Long id;
  private ReactionType reaction;
  private Long postId;
  private Long userId;
}
