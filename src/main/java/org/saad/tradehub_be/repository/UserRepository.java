package org.saad.tradehub_be.repository;

import org.saad.tradehub_be.entity.data.actors.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository defines the contract for a repository that manages User entities.
 */
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);

    User findByUserId(String userId);
}
