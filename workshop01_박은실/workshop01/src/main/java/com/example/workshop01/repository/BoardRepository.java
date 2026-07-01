package com.example.workshop01.repository;

import com.example.workshop01.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("SELECT b FROM Board b JOIN FETCH b.author ORDER BY b.createdAt DESC")
    List<Board> findAllWithAuthor();

    // ← 이미지까지 포함한 전체 목록 쿼리 추가
    @Query("SELECT DISTINCT b FROM Board b JOIN FETCH b.author LEFT JOIN FETCH b.images ORDER BY b.createdAt DESC")
    List<Board> findAllWithAuthorAndImages();

    @Query("SELECT DISTINCT b FROM Board b JOIN FETCH b.author LEFT JOIN FETCH b.images WHERE b.id = :id")
    Optional<Board> findByIdWithAuthorAndImages(@Param("id") Long id);
}