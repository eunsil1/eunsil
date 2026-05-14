package com.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="cart_item")
@Getter
@Setter
public class CartItem extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;

    //3가지 주요 상황(처음 담기, 추가 담기, 수량을 직접 수정)

    //어떤 장바구니(Cart)에 어떤상품(item)을 몇개(count)를 담을지 결정하여
    //cartItem 객체를 생성
    public static CartItem createCartItem(Cart cart, Item item, int count){
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        cartItem.setCount(count);
        return cartItem;
    }

    //사용자가 이미 장바구니에 들어있는 상품을 상세페이지에서 다시 "장바구니 담기"
    public void addCount(int count){
        this.count += count;
    }

    //장바구니 목록 페이지에서 사용자가 직접 숫자를 입력하거나 +/- 버튼을 눌러 수량을 변경
    public void updateCount(int count){
        this.count = count;
    }


}
