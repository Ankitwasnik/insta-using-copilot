package com.talentica.copilot.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResource> handleResourceNotFoundException(ResourceNotFoundException ex) {
    ErrorResource errorResource = new ErrorResource(ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResource);
  }

  @ExceptionHandler(ResourceAlreadyExistsException.class)
  public ResponseEntity<ErrorResource> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
    ErrorResource errorResource = new ErrorResource(ex.getMessage());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResource);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResource> handleException(Exception ex) {
    log.error("Internal Error Occurred", ex);
    ErrorResource errorResource = new ErrorResource("Internal Error Occurred");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResource);
  }
}
