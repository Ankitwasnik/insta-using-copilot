package com.talentica.copilot.dto;

import java.time.Instant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

  private Long id;
  private String name;
  private String email;
  private Instant createdTime;
  private Instant updatedTime;
  private Boolean deleted;
  private Instant verifiedAt;
  private Instant deactivatedAt;

}
