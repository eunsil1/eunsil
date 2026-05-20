package com.example.roomfit.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cart_items", uniqueConstraints = @UniqueConstraint(columnNames = {"cart_id", "product_id"}))
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //장바구니 1개, CartItem 여러개
    @JoinColumn(nullable = false)
    private Cart cart; //어느 장바구니에 속하는지

    @ManyToOne(fetch = FetchType.LAZY) //상품 1개가 여러 장바구니에 담길 수 있음
    @JoinColumn(nullable = false)
    private Product product; //어떤 상품인지

    @Builder.Default
    private int quantity = 1; //수량, 기본값 = 1
}
//장바구니 화면에서 상품명, 가격, 이미지 조회
//@Table(name = "cart_items", uniqueConstraints = @UniqueConstraint(columnNames = {"cart_id", "product_id"}))
//같은 상품을 다시 담으면 새 row를 만들지 말고 shopService.addCart()에서 기존 row의 수량만 증가합니다.