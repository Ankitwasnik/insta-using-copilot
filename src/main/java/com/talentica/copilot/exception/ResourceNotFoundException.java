package com.talentica.copilot.exception;

import java.io.Serial;

public class ResourceNotFoundException extends BaseException {

  @Serial
  private static final long serialVersionUID = -5476978056577615786L;

  public ResourceNotFoundException(String message) {
    super(message);
  }

  public ResourceNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public ResourceNotFoundException(Throwable cause) {
    super(cause);
  }
}
