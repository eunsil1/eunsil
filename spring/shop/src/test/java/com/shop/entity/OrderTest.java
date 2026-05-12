package com.shop.entity;

import static org.junit.jupiter.api.Assertions.*;

import com.shop.constant.ItemSellStatus;
import com.shop.repository.ItemRepository;
import com.shop.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.shop.repository.MemberRepository;
import com.shop.repository.OrderItemRepository;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
@Transactional

class OrderTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;

    @Autowired
    ItemRepository itemRepository;

    public Item createItem(){
        Item item = new Item();
        item.setItemNm("테스트 성공");
        item.setPrice(10000);
        item.setItemDetail("상세설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());

        item.setUpdateTime(LocalDateTime.now());
        return item;

    }

    @Test
    @DisplayName("영속성 전이 테스트")
    public void cascadeTest(){
        Order order = new Order(); //주문 객체 생성
        for (int i = 0; i < 3; i++){ //3번 반복
            Item item = this.createItem(); //상품 생성
            itemRepository.save(item); //상품 저장
            OrderItem orderItem = new OrderItem(); //주문상품생성
            orderItem.setItem(item); //상품 연결(OrderItem -> Item)
            orderItem.setCount(10);
            orderItem.setOrderPrice(1000);
            orderItem.setOrder(order); //주문연결(OrderItem) - 자식에게 부모를 알려줌
            order.getOrderItems().add(orderItem); //부모의 리스트에 자식을 추가
            //양방향 연관관계 맞춤 - order에 orderItem 3개 반복
        }
        //cascade = CascadeType.ALL
        //내부적으로 save(order) 저장
        //save(orderitem1), save(orderitem2), save(orderitem3)
        orderRepository.saveAndFlush(order); //저장 후 즉시 DB 반영
        em.clear();

        Order saveOrder = orderRepository.findById(order.getId())
                .orElseThrow(EntityNotFoundException::new);
        assertEquals(3, saveOrder.getOrderItems().size());

    }

    public Order createOrder(){
        Order order = new Order();
        for(int i = 0; i < 3; i++){
            Item item = createItem();
            itemRepository.save(item); //주문할 원본 상품을 DB에 먼저 등록
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item); //무엇을 살것인지(상품연결)
            orderItem.setCount(10);
            orderItem.setOrderPrice(10000);
            orderItem.setOrder(order); //이 상품은 어느 주문서에 속하는지(자식 -> 부모 연결)
            order.getOrderItems().add(orderItem); //주문서 이 상품을 추가(부모 -> 자식 연결)
        }
        Member member = new Member();
        memberRepository.save(member); //주문하는 사람 DB 저장
        order.setMember(member); //이 주문서의 주인은 이 사람이다(외래키 설정)
        orderRepository.save(order); //주문 저장(주문서 한 장만 저장)
        return order;
    }

    @Test
    @DisplayName("고아 객체 제거 테스트")
    public void orphanRemovalTest(){
        Order order = this.createOrder(); //주문생성(내부에는 orderItem 3개)
        //주문상품 리스트에서 첫번째 (index 0) 상품을 제거
        order.getOrderItems().remove(0);
        //DB 삭제 명령을 직접 내리는것이 아니라 , 단지 리스트에서 제거
        em.flush();
        //영속성 컨텍스트의 변경내용을 DB에 반영

    }

    @Test
    @DisplayName("지연 로딩 테스트")
    public void lazyLoadingTest(){
        Order order = this.createOrder();
        Long orderItemId = order.getOrderItems().get(0).getId();
        em.flush();
        em.clear();
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(EntityNotFoundException::new);
        //주문 상품을 조회, 주문 상품과 연결된 주문(order)은 가져오지 않음
        System.out.println("Order class : " + orderItem.getOrder().getClass());
        //class com.shop.entity.Order$HibernateProxy
        System.out.println("=========================");
        orderItem.getOrder().getOrderDate();
        //실제로 주문데이터가 필요해서 접근하는 순간, 비로소 select 쿼리가 한번더 나간다.
        System.out.println("=========================");
    }

    @Test
    @DisplayName("즉시 로딩(EAGER) 다중 쿼리 발생 테스트")
    public void eagerLoadingTest() {
        // 1. 데이터 준비: 주문 3개를 만들고 각각 저장 (createOrder 내부 로직 활용)
        for(int i=0; i<3; i++) {
            this.createOrder();
        }

        em.flush();
        em.clear();
        System.out.println("======= 준비 끝, 조회 시작 =======");

        // 2. 모든 주문 상품을 조회
        // 이론상 쿼리 1번으로 끝나야 하지만, EAGER 설정 때문에...
        List<OrderItem> orderItems = orderItemRepository.findAll();

        System.out.println("======= 조회 완료 =======");
    }
}