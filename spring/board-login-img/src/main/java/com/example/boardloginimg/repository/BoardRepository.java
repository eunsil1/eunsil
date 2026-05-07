package com.example.boardloginimg.repository;

import com.example.boardloginimg.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
   // List<Board> findAll();
    @Query("SELECT b FROM Board b JOIN FETCH b.author ORDER BY b.createdAt DESC")
    List<Board> findAllWithAuthor();

    //게시글 1개를 조회하면 작성자와 이미지까지 한 번에 모두 가져오는 쿼리

    @Query("SELECT distinct b FROM Board b JOIN FETCH b.author  LEFT JOIN FETCH b.images  WHERE b.id = :id")
    Optional<Board> findByIdWithAuthorAndImages(@Param("id") Long id);
}
//SELECT distinct b 이미지가 여러개 하서 Board가 중복조회 되는걸 제거
//게시글 1대 이미지3 개 distinct 안쓰면 Board1 Board1 Board1 세개가나옴


//JOIN FETCH b.author  작성자도 같이 가져옴   board.getAuthor().getName()바로 사용가능


//LEFT JOIN FETCH b.images 이미지 리스트까지 같이 가져온다 LEFT JOIN 이미지 없는 게시글도 조회
//inner join 이미지 없는 게시글 제외

