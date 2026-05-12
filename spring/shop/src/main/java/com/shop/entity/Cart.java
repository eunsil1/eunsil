package com.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cart")
@Getter
@Setter
@ToString
public class Cart extends BaseEntity{

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    //fetch = FetchType.LAZY -> 필요할 때 가져오기
    //EAGER -> 장바구니 조회 할 때 무조건(Member)도 가져와 - Join 발생
    //LAZY -> 장바구니만 가져오기, 필요할 때 cart.getMember().getName();
    //호출할 때만 가져와 -> 나중에 쿼리 발생


}
//장바구니와 회원과의 관계
//회원 1명 -> 장바구니 1개, 장바구니 1개 -> 회원 1명
//Cart 테이블에 member_id(fk) 컬럼이 생기고
//이 컬럼이 멤버 테이블의 기본 (pk) 가리키게 됨
//Cart 엔티티가 외래키를 들고 있으므로 이 연관 관계의 주인
//cart.getMember().getEmail()
