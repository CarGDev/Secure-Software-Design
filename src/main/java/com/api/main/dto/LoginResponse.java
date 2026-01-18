package com.api.main.dto;

/*
 * Data Transfer Object for authentication response.
 * Contains the token returned after successful login.
 * Used to transfer authentication results between layers.
 * The token should be stored securely and sent in subsequent requests.
 */
public class LoginResponse {

  private String status;
  private String message;
  private String token;

  public LoginResponse() {}

  public LoginResponse(String status, String message) {
    this.status = status;
    this.message = message;
  }

  public LoginResponse(String status, String message, String token) {
    this.status = status;
    this.message = message;
    this.token = token;
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

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
