package com.api.main.controllers;

import com.api.main.constants.Constants;
import com.api.main.dto.CreateUserRequest;
import com.api.main.dto.ErrorResponse;
import com.api.main.dto.UserResponse;
import com.api.main.entity.User;
import com.api.main.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.Collections;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/*
 * REST controller for user management operations.
 * Provides endpoints for user profile access, creation, and logout.
 * Admin-only endpoints are protected with @PreAuthorize.
 * All endpoints require valid authentication.
 * Supports token invalidation for secure logout.
 */
@RestController
@RequestMapping("/users")
public class UserController {

  private final AuthService authService;

  public UserController(AuthService authService) {
    this.authService = authService;
  }

  @GetMapping("/me")
  public ResponseEntity<?> getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      return ResponseEntity.status(401)
          .body(new ErrorResponse(Constants.ERROR, Constants.UNAUTHORIZED_MESSAGE));
    }

    try {
      String username = authentication.getName();
      UserResponse userResponse = authService.getCurrentUser(username);
      return ResponseEntity.ok(userResponse);
    } catch (RuntimeException e) {
      return ResponseEntity.status(401)
          .body(new ErrorResponse(Constants.ERROR, Constants.UNAUTHORIZED_MESSAGE));
    }
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/create")
  public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest request) {
    try {
      User user =
          authService.registerUser(
              request.getUsername(), request.getEmail(), request.getPassword(), request.getRole());
      UserResponse response =
          new UserResponse(
              user.getId(),
              user.getUsername(),
              user.getEmail(),
              Collections.singletonList(user.getRole()));
      return ResponseEntity.status(201).body(response);
    } catch (RuntimeException e) {
      return ResponseEntity.status(400).body(new ErrorResponse(Constants.ERROR, e.getMessage()));
    }
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logout(HttpServletRequest request) {
    try {
      String authHeader = request.getHeader("Authorization");
      if (authHeader != null && authHeader.startsWith("Bearer ")) {
        String token = authHeader.substring(7);
        authService.logout(token);
      }
      return ResponseEntity.ok(
          Map.of(
              Constants.STATUS,
              Constants.SUCCESS,
              Constants.MESSAGE,
              Constants.LOGGED_OUT_SUCCESSFULLY));
    } catch (RuntimeException e) {
      return ResponseEntity.status(500)
          .body(new ErrorResponse(Constants.ERROR, Constants.LOGOUT_FAILED));
    }
  }
}
