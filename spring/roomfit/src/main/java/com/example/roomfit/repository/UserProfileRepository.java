package com.example.roomfit.repository;

import com.example.roomfit.domain.Member;
import com.example.roomfit.domain.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findByMemberId(Long memberId);


    Long member(Member member);
}
