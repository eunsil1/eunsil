package com.shop.repository;

import com.shop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart ,Long> {

    //현재 로그인한 회원 cart 엔티티 조회
    Cart findByMemberId(Long memberId);

}
