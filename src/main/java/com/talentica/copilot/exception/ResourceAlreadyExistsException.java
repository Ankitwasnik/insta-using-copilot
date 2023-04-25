package com.talentica.copilot.exception;

import java.io.Serial;

public class ResourceAlreadyExistsException extends BaseException {

  @Serial
  private static final long serialVersionUID = -4688944891097415434L;

  public ResourceAlreadyExistsException(String message) {
    super(message);
  }

  public ResourceAlreadyExistsException(String message, Throwable cause) {
    super(message, cause);
  }

  public ResourceAlreadyExistsException(Throwable cause) {
    super(cause);
  }

}
