package com.example.crud2Api.dto;

import com.example.crud2Api.entity.DoIt;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor//기본생성자
@AllArgsConstructor//모든생성자
@ToString
public class DoDto {
    private Long num;
    private String title;
    private String content;

    public DoIt toEntity(){
        return new DoIt(num, title, content);
    }
}
