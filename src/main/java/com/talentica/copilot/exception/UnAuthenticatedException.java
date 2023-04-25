package com.talentica.copilot.exception;

public class UnAuthenticatedException extends BaseException {

  public UnAuthenticatedException(String message) {
    super(message);
  }

  public UnAuthenticatedException(String message, Throwable cause) {
    super(message, cause);
  }

}
