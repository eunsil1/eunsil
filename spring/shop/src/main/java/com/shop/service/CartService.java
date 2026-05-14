package com.shop.service;

import com.shop.dto.CartItemDto;
import com.shop.entity.Cart;
import com.shop.entity.CartItem;
import com.shop.entity.Item;
import com.shop.entity.Member;
import com.shop.repository.CartItemRepository;
import com.shop.repository.CartRepository;
import com.shop.repository.ItemRepository;
import com.shop.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderService orderService;

    public Long addCart(CartItemDto cartItemDto, String email){

        //기본정보 조회 - 주문하려는 상품이 존재하는지 확인
        //현재 로그인한 회원(Member)
        Item item = itemRepository.findById(cartItemDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByEmail(email);

        //장바구니 존재 여부 체크 및 생성
        //해당 회원의 장바구니(cart)가 있는지 확인
        Cart cart = cartRepository.findByMemberId(member.getId());
        if(cart == null){ //처음 담는 사용자라면?
            cart =Cart.createCart(member); //Cart.createCart(member) 호출해 장바구니 새로 만든다.
            cartRepository.save(cart);
        }
        //상품 중복 체크 및 처리
        //findByCartIdAndItemId 해당 상품이 장바구니에 이미 담겨있는지 확인
        CartItem savedCartItem= cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());

        //이미 있다면? savedCartItem.addCount 호출해 기존 수량에 더해준다. (Dirty Checking save없어도 db반영)
        if(savedCartItem != null){
            savedCartItem.addCount(cartItemDto.getCount());
            return savedCartItem.getId();
        }else{ //새로운 상품이면 createCartItem 객체 생성한 후 저장(save)한다.
            CartItem cartItem = CartItem.createCartItem(cart, item, cartItemDto.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getId();
        }

    }


}
