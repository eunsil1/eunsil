package com.example.roomfit.repository;

import com.example.roomfit.domain.Cart;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {

    //특정 repository 메서드에서만 강제로 eager(연관관계를 다 들고옴)처럼 가져옴
    @EntityGraph(attributePaths = {"items", "items.product"})
    Optional<Cart> findWithItemsByMemberId(Long memberId);

    //findByMemberId - 회원아이디로 cart를 조회
    //Cart만 가져옴 - items

    //findWithItemsByMemberId 회원 아이디로 cart 를 조회
    //@EntityGraph로 연관 데이터를 한번에 로드
    //items - CartItem 목록
    //items.product - 각 item의 Product(이름, 가격, 이미지) 가져옴
    //cart.getItems(), items.getProduct()

    Optional<Cart> findByMemberId(Long memberId);
}
