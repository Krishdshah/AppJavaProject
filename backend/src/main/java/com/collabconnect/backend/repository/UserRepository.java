package com.collabconnect.backend.repository;

import com.collabconnect.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // This method will allow us to find a user by their email
    Optional<User> findByEmail(String email);
}