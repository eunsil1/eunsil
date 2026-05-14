package com.shop.repository;

import com.shop.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    //특정 사용자의 주문을 가져오되, 최신순(desc)으로 정렬하고 페이징까지 한번에 처리
    @Query("select o from Order o where o.member.email = :email order by o.orderDate desc ")
    List<Order> findOrders(@Param("email") String email, Pageable pageable);

    //페이징처리를 위해서는 전체 데이터 갯수가 몇개인지 - 별도의 카운트 처리
    @Query("select count(o) from Order o where o.member.email = :email")
    Long countOrder(@Param("email") String email);

}
