package com.shop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartOrderDto {

    private Long cartItemId;
    private List<CartOrderDto> cartOrderDtoList;

}
//장바구니 주문하면 기존 주문 로직과 차이점
//여러개 상품을 하나의 주문에 담을수 있음
//주문한 상품은 장바구니에서 삭제
