package com.example.roomfit.repository;

import com.example.roomfit.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike ,Long> {
    boolean existsByMemberIdAndPostId(Long memberId, Long postId);
    //해당 회원이 해당 게시글 눌렀는지 확인

    Optional<PostLike> findByMemberIdAndPostId(Long memberId, Long postId);
    //회원 + 게시글 좋아요 데이터 가져오기 - 좋아요 취소시 삭제하려고
    //좋아요 행 1건 조회(Optional.empty()) - 있으면 취소(delete), 없으면 insert

    long countByPostId(Long postId);
    //특정 게시글 좋아요 수

    @Query("SELECT pl.post.id FROM PostLike pl WHERE pl.member.id In :memberIds")
    List<Long> findPostIdsLikedByMembers(List<Long> memberIds);
    //비슷한 사용자들이 좋아한게시글 id 조회(추천)

    //회원 좋아요 목록
    List<PostLike> findByMemberId(Long memberId);
}
