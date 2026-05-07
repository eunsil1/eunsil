package com.example.firstProject.repository;

import com.example.firstProject.entity.Comment;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long > {

//    @Query(value =
//            "SELECT * FROM comment WHERE article_id = :articleId",
//            nativeQuery = true)
//    List<Comment> findByArticleId(@Param("articleId") Long articleId);

    //특정 게시글의 댓글 조회
    List<Comment> findByArticleId(@Param("articleId") Long articleId);

    //특정 닉네임의 모든 댓글 조회
    List<Comment> findByNickname(String nickname);

    //내용에 특정 단어 포함
    List<Comment> findByBodyContaining(String keyword);

    //최신댓글 순 정렬(정렬 + 조건)
    List<Comment> findByArticle_IdOrderByIdDesc(@Param("articleId") Long articleId);

    //댓글 갯수 구하기
    long countByArticle_Id(Long articleId);

    //특정글 댓글 삭제
    void deleteByArticle_Id(Long articleId);

    //특정 닉네임 댓글 존재여부
    boolean existsByNickname(String nickname);

}
