package com.api.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Main entry point for the Secure Software Design API application.
 * This class bootstraps the Spring Boot application and initializes
 * all necessary configurations, security filters, and components.
 * Uses @SpringBootApplication for auto-configuration and component scanning.
 */
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
