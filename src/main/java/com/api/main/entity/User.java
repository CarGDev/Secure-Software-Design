package com.api.main.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/*
 * JPA Entity representing a user in the system.
 * Stores user credentials and profile information securely.
 * Password is stored as a BCrypt hash, never in plain text.
 * Contains validation constraints for username, email, and password.
 * Used for authentication and authorization throughout the application.
 */
@Entity
@Table(name = "users")
public class User {

  /*
   * Primary key for the user entity
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /*
   * Username of the user
   * Must be unique and between 3 to 50 characters
   * @return username
   */
  @NotBlank
  @Size(min = 3, max = 50)
  @Column(unique = true, nullable = false)
  private String username;

  /*
   * Email address of the user
   * Must be unique and a valid email format
   * @return email
   */
  @NotBlank
  @Email
  @Column(unique = true, nullable = false)
  private String email;

  /*
   * BCrypt hashed password of the user
   * Stored securely, never in plain text
   * @return passwordHash
   */
  @NotBlank
  @Column(nullable = false)
  private String passwordHash;

  /*
   * Role of the user (e.g., ROLE_USER, ROLE_ADMIN)
   * Used for authorization purposes
   * @return role
   */
  @NotBlank
  @Column(nullable = false)
  private String role;

  /*
   * Flag indicating if the user account is enabled
   * Disabled accounts cannot authenticate
   * @return enabled
   */
  @Column(nullable = false)
  private boolean enabled = true;

  /* Default constructor */
  public User() {}

  /*
   * Constructor with parameters
   * @param username Username of the user
   * @param email Email address of the user
   * @param passwordHash BCrypt hashed password
   * @param role Role of the user
   *
   */
  public User(String username, String email, String passwordHash, String role) {
    this.username = username;
    this.email = email;
    this.passwordHash = passwordHash;
    this.role = role;
    this.enabled = true;
  }

  /*
   * Constructor with all parameters
   * @param id User ID
   * @param username Username of the user
   * @param email Email address of the user
   * @param passwordHash BCrypt hashed password
   * @param role Role of the user
   * @param enabled Account enabled status
   *
   */
  public User(
      Long id, String username, String email, String passwordHash, String role, boolean enabled) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.passwordHash = passwordHash;
    this.role = role;
    this.enabled = enabled;
  }

  /* Getters and Setters */

  /*
   * Get the user ID
   * @return id
   *
   */
  public Long getId() {
    return id;
  }

  /*
   * Set the user ID
   * @param id User ID
   *
   */
  public void setId(Long id) {
    this.id = id;
  }

  /*
   * Get the username
   * @return username
   *
   */
  public String getUsername() {
    return username;
  }

  /*
   * Set the username
   * @param username Username of the user
   *
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /*
   * Get the email address
   * @return email
   *
   */
  public String getEmail() {
    return email;
  }

  /*
   * Set the email address
   * @param email Email address of the user
   *
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /*
   * Get the BCrypt hashed password
   * @return passwordHash
   *
   */
  public String getPasswordHash() {
    return passwordHash;
  }

  /*
   * Set the BCrypt hashed password
   * @param passwordHash BCrypt hashed password
   *
   */
  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }

  /*
   * Get the user role
   * @return role
   *
   */
  public String getRole() {
    return role;
  }

  /*
   * Set the user role
   * @param role Role of the user
   *
   */
  public void setRole(String role) {
    this.role = role;
  }

  /*
   * Check if the user account is enabled
   * @return enabled
   *
   */
  public boolean isEnabled() {
    return enabled;
  }

  /*
   * Set the user account enabled status
   * @param enabled Account enabled status
   *
   */
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  /*
   * String representation of the User entity
   * @return String representation
   *
   */
  @Override
  public String toString() {
    return "User{"
        + "id="
        + id
        + ", username='"
        + username
        + '\''
        + ", email='"
        + email
        + '\''
        + ", role='"
        + role
        + '\''
        + ", enabled="
        + enabled
        + '}';
  }
}
