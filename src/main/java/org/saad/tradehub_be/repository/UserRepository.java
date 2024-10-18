package org.saad.tradehub_be.repository;

import org.saad.tradehub_be.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * UserRepository defines the contract for a repository that manages User entities.
 */
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
}
