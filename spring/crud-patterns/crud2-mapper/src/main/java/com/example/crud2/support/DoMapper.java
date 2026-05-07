package com.example.crud2.support;

import com.example.crud2.dto.DoDto;
import com.example.crud2.entity.DoIt;

//dto <-> entity 변환을 한곳에서 모음
public class DoMapper {

    public DoMapper() {
    }

    public static DoIt toNewEntity(DoDto dto){
        return new DoIt(null, dto.getTitle(), dto.getContent());
    }

    public static DoIt toEntityWithId(DoDto dto){
        return new DoIt(dto.getNum(), dto.getTitle(), dto.getContent());
    }
}
