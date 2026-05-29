package com.example.workshop01.repository;

import com.example.workshop01.domain.User;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    Optional<User> findByOauthProviderAndOauthProviderSubject(String oauthProvider, String oauthProviderSubject);

    Optional<User> findByEmail(String email);
}

