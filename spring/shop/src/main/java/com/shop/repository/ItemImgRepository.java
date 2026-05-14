package com.shop.repository;

import com.shop.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {
    //상품 이미지 아이디의 오름차순으로 가져오는 쿼리메서드
    List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);

    //주문 상품의 대표 이미지를 보여주기 위한 쿼리 작성
    ItemImg findByItemIdAndRepimgYn(Long itemId, String repimgYn);
}

