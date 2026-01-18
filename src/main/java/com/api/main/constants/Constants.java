package com.api.main.constants;

/*
 * Centralized constants for the application.
 * Contains reusable string literals to avoid hardcoding.
 * Includes JPQL queries, error messages, and HTTP-related constants.
 * Promotes consistency and maintainability across the codebase.
 * All constants are static and final for immutability.
 */
public class Constants {

  /*
   * JPQL queries use named parameters (:username) instead of string concatenation.
   * Hibernate binds these parameters safely, preventing SQL injection attacks.
   * User input is never directly interpolated into the query string.
   */
  public static final String ADDING_TOKEN_QUERY =
      "UPDATE Token t SET t.revoked = true WHERE t.username = :username AND t.revoked = false";

  public static final String DELETING_TOKEN_QUERY =
      "DELETE FROM Token t WHERE t.expiresAt < CURRENT_TIMESTAMP";

  public static final String USER_NOT_FOUND_MESSAGE = "User not found: ";

  public static final String ROLE = "ROLE_";

  public static final String AUTHORIZATION = "Authorization";

  public static final String BEARER_PREFIX = "Bearer ";

  public static final String JWT_TOKEN_INVALID_MESSAGE = "JWT token validation failed: ";

  public static final String UNAUTHORIZED_MESSAGE = "Unauthorized access attempt";

  public static final String STATUS = "status";

  public static final String SUCCESS = "success";

  public static final String MESSAGE = "message";

  public static final String LOGGED_OUT_SUCCESSFULLY = "Logged out successfully";

  public static final String ERROR = "error";

  public static final String LOGOUT_FAILED = "Logout failed";

  public static final String UP = "UP";

  public static final String INVALID_CREDENTIALS = "Invalid credentials";

  public static final String INTERNAL_SERVER_ERROR = "Internal server error";
}
