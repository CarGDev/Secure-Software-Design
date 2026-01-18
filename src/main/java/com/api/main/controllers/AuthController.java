package com.api.main.controllers;

import com.api.main.constants.Constants;
import com.api.main.dto.ErrorResponse;
import com.api.main.dto.LoginRequest;
import com.api.main.dto.LoginResponse;
import com.api.main.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

/*
 * REST controller for authentication operations.
 * Handles user login and token generation.
 * Validates credentials against stored user data.
 * Returns secure tokens for subsequent API requests.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
    try {
      LoginResponse response = authService.authenticate(request);
      return ResponseEntity.ok(response);
    } catch (BadCredentialsException e) {
      return ResponseEntity.status(401)
          .body(new ErrorResponse(Constants.ERROR, Constants.INVALID_CREDENTIALS));
    } catch (Exception e) {
      return ResponseEntity.status(500)
          .body(new ErrorResponse(Constants.ERROR, Constants.INTERNAL_SERVER_ERROR));
    }
  }
}
