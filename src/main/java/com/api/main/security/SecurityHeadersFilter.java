package com.api.main.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*
 * Filter that adds security headers to all HTTP responses.
 * Implements defense-in-depth by configuring multiple security headers:
 * - X-Frame-Options: Prevents clickjacking attacks
 * - X-Content-Type-Options: Prevents MIME type sniffing
 * - X-XSS-Protection: Enables browser XSS filtering
 * - Content-Security-Policy: Restricts resource loading sources
 * - Strict-Transport-Security: Enforces HTTPS connections
 * - Permissions-Policy: Disables unnecessary browser features
 * - Cache-Control: Prevents caching of sensitive data
 * Runs with highest precedence to ensure headers are applied early.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityHeadersFilter implements Filter {

  /* Initialization method for the filter */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    if (response instanceof HttpServletResponse httpResponse) {
      // Prevent clickjacking
      httpResponse.setHeader("X-Frame-Options", "DENY");

      // Prevent MIME type sniffing
      httpResponse.setHeader("X-Content-Type-Options", "nosniff");

      // Enable XSS filter in browsers
      httpResponse.setHeader("X-XSS-Protection", "1; mode=block");

      // Control referrer information
      httpResponse.setHeader("Referrer-Policy", "strict-origin-when-cross-origin");

      // Content Security Policy - restrict resource loading
      httpResponse.setHeader(
          "Content-Security-Policy",
          "default-src 'self'; "
              + "script-src 'self'; "
              + "style-src 'self' 'unsafe-inline'; "
              + "img-src 'self' data:; "
              + "font-src 'self'; "
              + "frame-ancestors 'none'; "
              + "form-action 'self'");

      // HTTP Strict Transport Security (HSTS)
      httpResponse.setHeader(
          "Strict-Transport-Security", "max-age=31536000; includeSubDomains; preload");

      // Permissions Policy - disable unnecessary features
      httpResponse.setHeader(
          "Permissions-Policy", "geolocation=(), microphone=(), camera=(), payment=(), usb=()");

      // Prevent caching of sensitive data
      httpResponse.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, private");
      httpResponse.setHeader("Pragma", "no-cache");
    }
    chain.doFilter(request, response);
  }
}
