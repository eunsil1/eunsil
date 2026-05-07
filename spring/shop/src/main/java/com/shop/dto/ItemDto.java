package com.shop.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ItemDto {

    private Long id;
    private String itemNm; //상품명
    private Integer price;
    private String itemDetail; //상품 상세 설명
    private String sellStatCd;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;
}
