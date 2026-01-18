package com.api.main.repositories;

import com.api.main.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * Repository interface for User entity database operations.
 * Extends JpaRepository for standard CRUD operations.
 * Provides custom query methods for user lookup and existence checks.
 * Used by authentication and user management services.
 *
 * SQL Injection Protection:
 * Spring Data JPA derived query methods (findByUsername, existsByUsername, etc.)
 * automatically use parameterized queries. User input is bound as parameters,
 * never concatenated into SQL strings, making SQL injection impossible.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);
}
