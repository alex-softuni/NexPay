package org.softuni.nexpay.user.repository;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.softuni.nexpay.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);
}
