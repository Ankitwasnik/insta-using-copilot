package com.talentica.copilot.exception;

import java.io.Serial;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 4698287299391144527L;
  private String message;

  public BaseException(String message) {
    super(message);
    this.message = message;
  }

  public BaseException(String message, Throwable cause) {
    super(message, cause);
  }

  public BaseException(Throwable cause) {
    super(cause);
  }
}
