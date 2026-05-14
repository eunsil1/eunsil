package com.shop.repository;

import com.shop.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    //사용자가 장바구니 상품을 담을때 이미 이 상품이 장바구니에 들어있는가를 판단
    CartItem findByCartIdAndItemId(Long cartId, Long itemId);
}
