package com.example.boardlogin.repository;

import com.example.boardlogin.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
   // List<Board> findAll();
    @Query("SELECT b FROM Board b JOIN FETCH b.author ORDER BY b.createdAt DESC")
    List<Board> findAllWithAuthor();

    @Query("SELECT b FROM Board b JOIN FETCH b.author WHERE b.id = :id")
    Optional<Board> findByIdWithAuthor(@Param("id") Long id);
}

//Board(게시글) 조회할때 author(User) 까지 한번에 같이 가져오고, 최신순으로정렬
//실제 쿼리

//SELECT b.*, u.*
//FROM boards b
//JOIN users u ON b.author_id = u.id;