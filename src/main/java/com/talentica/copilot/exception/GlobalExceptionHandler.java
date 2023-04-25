package com.talentica.copilot.exception;

import org.flywaydb.core.api.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResource> handleResourceNotFoundException(ResourceNotFoundException ex) {
    ErrorResource errorResource = new ErrorResource(ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResource);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ErrorResource> handleBadRequestException(HttpRequestMethodNotSupportedException ex) {
    ErrorResource errorResource = new ErrorResource(ex.getMessage());
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorResource);
  }

  @ExceptionHandler(BaseException.class)
  public ResponseEntity<ErrorResource> handleBaseException(BaseException ex) {
    ErrorResource errorResource = new ErrorResource(ex.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResource);
  }
}
