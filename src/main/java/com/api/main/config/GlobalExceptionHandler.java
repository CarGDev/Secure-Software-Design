package com.api.main.config;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 * Global exception handler for the application.
 * Catches and processes validation exceptions across all controllers.
 * Returns structured error responses with field-level validation messages.
 * Ensures consistent error format for API clients.
 * Uses @ControllerAdvice to apply globally to all request mappings.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> fieldErrors = new HashMap<>();

    ex.getBindingResult()
        .getFieldErrors()
        .forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));

    Map<String, Object> response = new HashMap<>();
    response.put("status", "error");
    response.put("message", "Validation failed");
    response.put("errors", fieldErrors);

    return ResponseEntity.badRequest().body(response);
  }
}
