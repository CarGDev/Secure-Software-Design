package com.api.main.entity;

import jakarta.persistence.*;
import java.time.Instant;

/*
 * JPA Entity representing a JWT token in the database.
 * Enables token tracking, validation, and revocation.
 * Stores token metadata including creation and expiration timestamps.
 * Revoked tokens are invalidated even before expiration.
 * Essential for implementing secure logout and token management.
 */
@Entity
@Table(name = "tokens")
public class Token {

  /* Primary key for the token entity */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /* The JWT token string */
  @Column(nullable = false, unique = true, length = 500)
  private String token;

  /* Username associated with the token */
  @Column(nullable = false)
  private String username;

  /* Timestamp when the token was created */
  @Column(nullable = false)
  private Instant createdAt;

  /* Timestamp when the token expires */
  @Column(nullable = false)
  private Instant expiresAt;

  /* Flag indicating if the token has been revoked */
  @Column(nullable = false)
  private boolean revoked = false;

  /* Default constructor */
  public Token() {}

  /*
   * Constructor with parameters
   * @param token The JWT token string
   * @param username Username associated with the token
   * @param createdAt Timestamp when the token was created
   * @param expiresAt Timestamp when the token expires
   *
   */
  public Token(String token, String username, Instant createdAt, Instant expiresAt) {
    this.token = token;
    this.username = username;
    this.createdAt = createdAt;
    this.expiresAt = expiresAt;
    this.revoked = false;
  }

  /* Getters and Setters */

  /* Get the token ID
   * @return id
   *
   */
  public Long getId() {
    return id;
  }

  /* Set the token ID
   * @param id Token ID
   *
   */
  public void setId(Long id) {
    this.id = id;
  }

  /* Get the JWT token string
   * @return token
   *
   */
  public String getToken() {
    return token;
  }

  /* Set the JWT token string
   * @param token The JWT token string
   *
   */
  public void setToken(String token) {
    this.token = token;
  }

  /* Get the username associated with the token
   * @return username
   *
   */
  public String getUsername() {
    return username;
  }

  /* Set the username associated with the token
   * @param username Username associated with the token
   *
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /* Get the creation timestamp
   * @return createdAt
   *
   */
  public Instant getCreatedAt() {
    return createdAt;
  }

  /* Set the creation timestamp
   * @param createdAt Timestamp when the token was created
   *
   */
  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  /* Get the expiration timestamp
   * @return expiresAt
   *
   */
  public Instant getExpiresAt() {
    return expiresAt;
  }

  /* Set the expiration timestamp
   * @param expiresAt Timestamp when the token expires
   *
   */
  public void setExpiresAt(Instant expiresAt) {
    this.expiresAt = expiresAt;
  }

  /* Check if the token is revoked
   * @return revoked
   *
   */
  public boolean isRevoked() {
    return revoked;
  }

  /* Set the revoked status
   * @param revoked Flag indicating if the token has been revoked
   *
   */
  public void setRevoked(boolean revoked) {
    this.revoked = revoked;
  }
}
