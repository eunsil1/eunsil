package com.example.board.repository;

import com.example.board.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * 목록 조회 시 N+1 방지: member 를 FETCH JOIN
     */
    @Query("SELECT p FROM Post p JOIN FETCH p.member")
    Page<Post> findAllWithMember(Pageable pageable);
}
