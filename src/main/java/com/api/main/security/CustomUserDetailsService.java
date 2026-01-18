package com.api.main.security;

import com.api.main.constants.Constants;
import com.api.main.entity.User;
import com.api.main.repositories.UserRepository;
import java.util.Collections;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
 * Custom implementation of Spring Security's UserDetailsService.
 * Loads user-specific data from the database during authentication.
 * Converts the application's User entity to Spring Security's UserDetails.
 * Maps user roles with the ROLE_ prefix for proper authorization checks.
 * Throws UsernameNotFoundException if the user does not exist.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

  /* Repository for accessing user data from the database */
  private final UserRepository userRepository;

  /*
   * Constructor for CustomUserDetailsService
   * @param userRepository Repository to access user data
   *
   */
  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /*
   * Load user details by username for authentication.
   * @param username The username of the user to load
   * @return UserDetails object containing user information and authorities
   * @throws UsernameNotFoundException if the user is not found
   *
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user =
        userRepository
            .findByUsername(username)
            .orElseThrow(
                () -> new UsernameNotFoundException(Constants.USER_NOT_FOUND_MESSAGE + username));

    return new org.springframework.security.core.userdetails.User(
        user.getUsername(),
        user.getPasswordHash(),
        user.isEnabled(),
        true,
        true,
        true,
        Collections.singletonList(new SimpleGrantedAuthority(Constants.ROLE + user.getRole())));
  }
}
