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
}
