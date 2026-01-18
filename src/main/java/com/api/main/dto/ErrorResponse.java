package com.api.main.dto;

/*
 * Data Transfer Object for error responses.
 * Provides a standardized format for API error messages.
 * Contains status and message fields for consistent error handling.
 * Used across all endpoints to return meaningful error information.
 */
public class ErrorResponse {

  private String status;
  private String message;

  public ErrorResponse() {}

  public ErrorResponse(String message) {
    this.status = "error";
    this.message = message;
  }

  public ErrorResponse(String status, String message) {
    this.status = status;
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
