package com.example.roomfit.repository;

import com.example.roomfit.domain.ProductReview;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {

    @EntityGraph(attributePaths = "member") //작성자 정보 함께 로드
    List<ProductReview> findByProductIdOrderByCreatedAtDesc(Long productId);
    //해당 상품 리뷰만 최신 작성순으로 작성자 함께 로드

    //중복확인 - 특정회원이 특정상품에 이미 리뷰를 썼는지 조회 -> 상품당 1리뷰
    Optional<ProductReview> findByProductIdAndMemberId(Long productId, Long memberId);
}
