package com.talentica.copilot.dto;

import java.time.Instant;
import lombok.Data;

@Data
public class PostTrendDto {

  private Instant time;
  private Long count;
}
