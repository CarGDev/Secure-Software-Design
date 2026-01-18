package com.api.main.controllers;

import com.api.main.constants.Constants;
import com.api.main.dto.HealthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * REST controller for application health monitoring.
 * Provides a public endpoint for checking API availability.
 * Used by load balancers and monitoring systems.
 * Does not require authentication for accessibility.
 */
@RestController
public class Health {

  @GetMapping("/health")
  public ResponseEntity<HealthResponse> healthCheck() {
    return ResponseEntity.ok(new HealthResponse(Constants.UP));
  }
}
