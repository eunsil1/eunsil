package com.example.game_ranking.repository;

import com.example.game_ranking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 로그인할 때 username으로 유저 찾기
    Optional<User> findByUsername(String username);

    // 이메일 중복 체크
    boolean existsByEmail(String email);

    // username 중복 체크
    boolean existsByUsername(String username);
}
