package com.api.main.dto;

import jakarta.validation.constraints.NotBlank;

/*
 * Data Transfer Object for authentication requests.
 * Contains username and password for user login.
 * Both fields are required and validated before processing.
 * Passwords are never stored or logged in plain text.
 */
public class LoginRequest {

  @NotBlank(message = "Username is required")
  private String username;

  @NotBlank(message = "Password is required")
  private String password;

  public LoginRequest() {}

  public LoginRequest(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
