package com.example.roomfit.repository;

import com.example.roomfit.domain.Comment;
import com.example.roomfit.domain.PostStatus;
import com.example.roomfit.domain.PostType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment ,Long> {

    @EntityGraph(attributePaths = {"author", "parent"})
    List<Comment> findByPostTypeAndPostIdAndStatusOrderByCreatedAtAsc(
            PostType postType, Long postId, PostStatus status
    );
    //댓글 조회
    //특정 게시물의 댓글 조회
    //@EntityGraph 있으면 comment.getAuthor().getNickname()
    //@EntityGraph 없으면 select * from member where id = ? 댓글마다 실행 N + 1
    //"parent" 댓글 -> 답글 parent 써주면 comment.getParent()

    long countByPostTypeAndPostIdAndStatus(PostType postType, Long postId, PostStatus status);
    //특정 게시글 댓글 수 조회
    //PostType - 인테리어 게시글, 자유게시글, 공지사항
}
