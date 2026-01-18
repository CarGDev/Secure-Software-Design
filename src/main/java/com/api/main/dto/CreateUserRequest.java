package com.api.main.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/*
 * Data Transfer Object for user registration requests.
 * Contains validation constraints for secure user creation:
 * - Username: 3-50 characters, must be unique
 * - Email: Valid format, must be unique
 * - Password: Minimum 8 chars with uppercase, lowercase, number, and special char
 * - Role: Required for authorization assignment
 * Input validation helps prevent injection attacks and data integrity issues.
 */
public class CreateUserRequest {

  @NotBlank(message = "Username is required")
  @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
  private String username;

  @NotBlank(message = "Email is required")
  @Email(message = "Email must be valid")
  private String email;

  @NotBlank(message = "Password is required")
  @Size(min = 8, message = "Password must be at least 8 characters")
  @Pattern(
      regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
      message =
          "Password must contain at least one uppercase letter, one lowercase letter, one number,"
              + " and one special character (@$!%*?&)")
  private String password;

  @NotBlank(message = "Role is required")
  private String role;

  public CreateUserRequest() {}

  public CreateUserRequest(String username, String email, String password, String role) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.role = role;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}
