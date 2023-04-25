package com.talentica.copilot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {

  @NotBlank
  @Size(min = 3, max = 100)
  private String name;

  @NotBlank
  @Size(min = 3, max = 100)
  private String email;

  @NotBlank
  @Size(min = 3, max = 100)
  private String password;
}
