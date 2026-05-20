package com.example.roomfit.repository;

import com.example.roomfit.domain.InteriorStyle;
import com.example.roomfit.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    //ShopService.recommentProducts() - 프로필 기반 맞춤 소품 추천
    //OnsaleTrue - 판매중인 상품만 onSale = true
    //StyleTag - 선호 스타일과 일치
    //PriceLessThanEquals - 가격 <= maxPrice
    //orderByAvgRatingDesc - 평점 높은 순
    //판매중(true) + 모던스타일 + 10만원 이하 상품을 평점 높은 순으로 조회
    List<Product> findByOnSaleTrueAndStyleTagAndPriceLessThanEqualOrderByAvgRatingDesc(
            InteriorStyle styleTag, int maxPrice);

    //판매중 상품 전체, 평점 내림차준
    //ShopService.listAll() - /shop 목록
    List<Product> findByOnSaleTrueOrderByAvgRatingDesc();

    Optional<Product> findByName(String name);

    //전체 상품, id 내림 차순(최신 등록이 먼저 Page + pageable - 페이징)
    //productAdminService - 관리자 상품 목록
    Page<Product> findAllByOrderByIdDesc(Pageable pageable);

    //상품명 부분 검색(대소문자 무시) id 내림차순 + 페이징
    Page<Product> findByNameContainingIgnoreCaseOrderByIdDesc(Pageable pageable, String name);


}
//Data JPA 네이밍 규칙
//findBy + 필드명 + 조건 + OrderBy + 정렬필드 + Desc/Asc
//SELECT *
//FROM product
//WHERE on_sale = true
//AND style_tag = ?
//AND price <= ?
//ORDER BY avg_rating DESC;