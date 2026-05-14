package com.shop.entity;

import com.shop.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime orderDate; //주문일

    //속성값 order로 적어준 이유는 OrderItem에 있는 Order에 의해 관리됨
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL
    , orphanRemoval = true, fetch = FetchType.LAZY)
    //cascade = CascadeType.ALL(REMOVE) 게시글 전체 댓글 삭제
    //orphanRemoval = true
    //post.getComments().remove() - 게시글(부모)은 그대로 두고
    //댓글 리스트에서 득정 댓글 삭제
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; //주문상태

    private LocalDateTime regTime;

    private LocalDateTime updateTime;

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem); //Order 안의 리스트 추가
        orderItem.setOrder(this); //OrderItem에 현재 Order 설정
    }

    public static Order createOrder(Member member, List<OrderItem> orderItemList){
        Order order = new Order();
        order.setMember(member); //1. 주문한 회원 정보 설정

        //2. 여러개의 주문 상품(OrderItem)을 주문 객체에 연결
        for(OrderItem orderItem : orderItemList){
            order.addOrderItem(orderItem);
            //여기서 addOrderItem 메서드는 단순히 리스트에 담는것을 넘어
            //orderItem 객체에도 Order 정보를 세팅해주는 양방향 연관관계 편의 메서드

            order.setOrderStatus(OrderStatus.ORDER); //3. 주문상태를 ORDER 초기화
            order.setOrderDate(LocalDateTime.now()); //4. 현재 시간을 주문시간으로 설정


        }
        return order;
    }

    public int getTotalPrice(){
        int totalPrice = 0;
        for(OrderItem orderItem : orderItems){
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
    //Item 개당 가격정보
    //OrderItem(상품가격 * 수량)
    //Oder 각 OrderItem의 결과값

    public void cancelOrder(){
        this.orderStatus = OrderStatus.CANCEL;
        for(OrderItem orderItem : orderItems){
            orderItem.cancel(); //재고 수량 원상복구
        }
    }
}
