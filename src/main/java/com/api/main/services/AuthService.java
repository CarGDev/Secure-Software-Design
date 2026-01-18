package com.api.main.services;

import com.api.main.dto.LoginRequest;
import com.api.main.dto.LoginResponse;
import com.api.main.dto.UserResponse;
import com.api.main.entity.Token;
import com.api.main.entity.User;
import com.api.main.repositories.TokenRepository;
import com.api.main.repositories.UserRepository;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Collections;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * Service layer for authentication and user management operations.
 * Handles user login, registration, and logout functionality.
 * Generates and validates tokens for authenticated sessions.
 * Stores tokens in database for revocation support.
 * Uses transactional operations for data consistency.
 * Passwords are hashed using BCrypt before storage.
 */
@Service
public class AuthService {

  private final UserRepository userRepository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  @Value("${token.expiration:86400000}")
  private Long tokenExpiration;

  public AuthService(
      UserRepository userRepository,
      TokenRepository tokenRepository,
      PasswordEncoder passwordEncoder,
      AuthenticationManager authenticationManager) {
    this.userRepository = userRepository;
    this.tokenRepository = tokenRepository;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
  }

  @Transactional
  public LoginResponse authenticate(LoginRequest request) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

      User user =
          userRepository
              .findByUsername(request.getUsername())
              .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

      String tokenValue = generateToken();

      Instant now = Instant.now();
      Instant expiresAt = now.plus(tokenExpiration, ChronoUnit.MILLIS);
      Token token = new Token(tokenValue, user.getUsername(), now, expiresAt);
      tokenRepository.save(token);

      return new LoginResponse("success", "Authentication successful", tokenValue);
    } catch (AuthenticationException e) {
      throw new BadCredentialsException("Invalid username or password");
    }
  }

  private String generateToken() {
    byte[] randomBytes = new byte[32];
    new java.security.SecureRandom().nextBytes(randomBytes);
    return UUID.randomUUID().toString() + "-" + Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
  }

  public UserResponse getCurrentUser(String username) {
    User user =
        userRepository
            .findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));

    return new UserResponse(
        user.getId(),
        user.getUsername(),
        user.getEmail(),
        Collections.singletonList(user.getRole()));
  }

  @Transactional
  public User registerUser(String username, String email, String password, String role) {
    if (userRepository.existsByUsername(username)) {
      throw new RuntimeException("Username already exists");
    }
    if (userRepository.existsByEmail(email)) {
      throw new RuntimeException("Email already exists");
    }

    User user = new User();
    user.setUsername(username);
    user.setEmail(email);
    user.setPasswordHash(passwordEncoder.encode(password));
    user.setRole(role);
    user.setEnabled(true);

    return userRepository.save(user);
  }

  @Transactional
  public void logout(String token) {
    tokenRepository
        .findByToken(token)
        .ifPresent(
            t -> {
              t.setRevoked(true);
              tokenRepository.save(t);
            });
  }

  public boolean isTokenValid(String token) {
    return tokenRepository.findByTokenAndRevokedFalse(token).isPresent();
  }
}
