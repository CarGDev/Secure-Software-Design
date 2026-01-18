package com.api.main.repositories;

import com.api.main.constants.Constants;
import com.api.main.entity.Token;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/*
 * Repository interface for Token entity database operations.
 * Manages JWT token persistence and lifecycle.
 * Provides methods for token validation and revocation.
 * Supports bulk operations for revoking user tokens and cleanup.
 * Uses JPQL queries for efficient token management.
 *
 * SQL Injection Protection:
 * All queries use parameterized statements. Derived methods (findByToken) and
 * @Query annotations with named parameters (:username) are safely bound by
 * Hibernate. No raw SQL or string concatenation is used anywhere.
 */
@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

  /*
   * Find a token by its string value.
   * @param token The JWT token string
   * @return Optional containing the Token if found, else empty
   *
   */
  Optional<Token> findByToken(String token);

  /*
   * Find a non-revoked token by its string value.
   * @param token The JWT token string
   * @return Optional containing the Token if found and not revoked, else empty
   *
   */
  Optional<Token> findByTokenAndRevokedFalse(String token);

  /*
   * Find the most recent token for a given username.
   * @param username The username associated with the token
   * @return Optional containing the most recent Token if found, else empty
   *
   */
  Optional<Token> findTopByUsernameOrderByExpiresAtDesc(String username);

  /*
   * Revoke all tokens associated with a specific username.
   * @param username The username whose tokens are to be revoked
   *
   */
  @Modifying(clearAutomatically = true, flushAutomatically = true)
  @Query(Constants.ADDING_TOKEN_QUERY)
  void revokeAllUserTokens(@Param("username") String username);

  /*
   * Delete all expired tokens from the database.
   *
   */
  @Modifying
  @Query(Constants.DELETING_TOKEN_QUERY)
  void deleteExpiredTokens();
}
