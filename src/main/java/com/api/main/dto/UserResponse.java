package com.api.main.dto;

import java.util.ArrayList;
import java.util.List;

/*
 * Data Transfer Object for user information responses.
 * Contains safe user data to expose via the API.
 * Excludes sensitive fields like password hash for security.
 * Used when returning user profile information to clients.
 */
public class UserResponse {

  private Long id;
  private String username;
  private String email;
  private List<String> roles;

  public UserResponse() {}

  public UserResponse(Long id, String username, String email, List<String> roles) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.roles = roles != null ? new ArrayList<>(roles) : null;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<String> getRoles() {
    return roles != null ? new ArrayList<>(roles) : null;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles != null ? new ArrayList<>(roles) : null;
  }
}
