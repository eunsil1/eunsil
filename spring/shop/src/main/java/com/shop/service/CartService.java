package com.shop.service;

import com.shop.dto.CartDetailDto;
import com.shop.dto.CartItemDto;
import com.shop.dto.CartOrderDto;
import com.shop.dto.OrderDto;
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
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

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

    @Transactional(readOnly = true)
    public List<CartDetailDto> getCartList(String email){
        List<CartDetailDto> cartDetailDtoList = new ArrayList<>();
        Member member = memberRepository.findByEmail(email);
        Cart cart = cartRepository.findByMemberId(member.getId());
        if(cart == null){ //장바구니가 없으면 빈 리스트 반환
            return cartDetailDtoList;
        }

        cartDetailDtoList = cartItemRepository.findCartDetailDtoList(cart.getId());
        //상품명 가격 수량 대표이미지
        return cartDetailDtoList;

    }

    //로그인한(나의) 장바구니인지 확인
    @Transactional(readOnly = true)
    public boolean validateCartItem(Long cartItemId, String email){
        Member curMember = memberRepository.findByEmail(email); //로그인한 사용자 조회
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);
        Member savedMember = cartItem.getCart().getMember(); //장바구니 주인 조회
        if (!StringUtils.equals(curMember.getEmail(), savedMember.getEmail())){
            //StringUtils.equals NullpointerException 안남 -> a.equals(b) NullpointerException 남
            return false; //
        }
        return true;
    }
//    return StringUtils.equals(
//        curMember.getEmail(),
//        savedMember.getEmail());
    public void updateCartItemCount(Long cartItemId, int count){
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);
        cartItem.updateCount(count); //변경감지
    }

    public void deleteCartItem(Long cartItemId){
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);
        cartItemRepository.delete(cartItem);
    }


    public Long orderCartItem(List<CartOrderDto> cartOrderDtoList, String email) {
        List<OrderDto> orderDtoList = new ArrayList<>(); //사용자가 체크한 장바구니 목록 상품1번 상품3번
        for(CartOrderDto cartOrderDto : cartOrderDtoList){ //장바구니 상품 반복조회(사용자가 선택한 장바구니 반복)
            CartItem cartItem = cartItemRepository //DB에서 장바구니 상품 조회
                    .findById(cartOrderDto.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);
            OrderDto orderDto = new OrderDto(); //OrderDto 생성
            orderDto.setItemId(cartItem.getItem().getId()); //상품번호 저장
            orderDto.setCount(cartItem.getCount()); //수량 저장
            orderDtoList.add(orderDto); //리스트에 추가
            //장바구니 데이터 -> 주문용 Dto 데이터로 변환
           }
        //orders() 메서드 전달 - 주문
        Long orderId = orderService.orders(orderDtoList, email);

        //주문 완료 후 장바구니 삭제
        for(CartOrderDto cartOrderDto : cartOrderDtoList){
            CartItem cartItem = cartItemRepository //CartItem 재조회
                    .findById(cartOrderDto.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);
            cartItemRepository.delete(cartItem);
            //cartItem 삭제(장바구니 비우기)
        }

        return orderId;
    }
}
