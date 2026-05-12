package com.shop.repository;

import com.shop.dto.ItemSearchDto;
import com.shop.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

//기본 JpaRepository 기능 외에
//직접 만든 QueryDSL 메서드를 추가하기 위한 인터페이스
//save findById delete 단순 crud만 제공
//동적 검색
public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}
//Page 현재 페이지데이터, 전체페이지수, 현재 페이지 번호
//다음 페이지 여부
