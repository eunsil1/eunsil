package com.shop.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainItemDto {

    private Long id;

    private String itemNm;

    private String itemDetail;

    private String imgUrl;

    private Integer price;

    //QueryDSL 조회 결과를 DTO에 바로 넣기 위함
    @QueryProjection
    public MainItemDto(Long id, String itemNm, String itemDetail, String imgUrl, Integer price){
        this.id = id;
        this.itemNm = itemNm;
        this.itemDetail = itemDetail;
        this.imgUrl = imgUrl;
        this.price = price;
    }

//    .select(
//    new QMainItemDto(
//        item.id,
//        item.itemNm,
//        item.itemDetail,
//        itemImg.imgUrl,
//        item.price
//    )
//)
}
