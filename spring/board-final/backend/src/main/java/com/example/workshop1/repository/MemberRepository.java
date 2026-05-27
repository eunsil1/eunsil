package com.example.workshop1.repository;

import com.example.workshop1.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
    Optional<Member> findByOauthProviderAndOauthProviderSubject(String provider, String subject);
    Optional<Member> findByEmail(String email);
    boolean existsByUsername(String username);
}
