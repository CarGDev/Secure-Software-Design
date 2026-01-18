package com.api.main.dto;

/*
 * Data Transfer Object for health check responses.
 * Returns the current status of the API service.
 * Used by monitoring systems to verify application availability.
 * Typically returns "UP" when the service is running correctly.
 */
public class HealthResponse {

  private String status;

  public HealthResponse() {}

  public HealthResponse(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
