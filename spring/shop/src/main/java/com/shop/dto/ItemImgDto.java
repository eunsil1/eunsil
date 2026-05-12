package com.shop.dto;

import com.shop.entity.ItemImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class ItemImgDto {

    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repImgYn;

    //static으로 한 이유 매번 객체 생성 안하려고
    private static ModelMapper modelMapper = new ModelMapper();

    public static ItemImgDto of(ItemImg itemImg){
        return modelMapper.map(itemImg, ItemImgDto.class);
    }
}
//ItemImg 엔티티 객체를 ItemImgDto 객체로 변환

//ItemImgDto dto = new ItemImgDto();
//dto.setId(itemImg.getId());
//        dto.setImgName(itemImg.getImgName());
//        dto.setOriImgName(itemImg.getOriImgName());
//        dto.setImgUrl(itemImg.getImgUrl());

// modelMapper.map(itemImg, ItemImgDto.class);

//service에서 ItemImgDto itemImgDto = ItemImgDto.of(itemImg);
