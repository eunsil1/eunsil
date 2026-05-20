package com.example.roomfit.repository;

import com.example.roomfit.domain.Member;
import com.example.roomfit.domain.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    //찜여부 확인
    boolean existsByMemberIdAndProductId(Long memberId, Long Product);
    //SELECT COUNT(*) > 0 FROM wishlists WHERE member_id = ? AND product_id = ?

    //토글(삭제)용 row(레코드) 조회
    Optional<Wishlist> findByMemberIdAndProductId(Long memberId, Long productId);

    //찜목록 최신순
    List<Wishlist> findByMemberIdOrderByCreatedAtDesc(Long memberId);

    Long member(Member member);
}
