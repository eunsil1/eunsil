package com.example.crud2Api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoIt {

    @Id//기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //db 자동생성 auto increment
    private Long num;

    @Column //테이블 컬럼 - 생략가능
    private String title;

    @Column
    private String content;
}

//@Entity - 이타입은 영속성 대상이고, 테이블과 매핑
//CREATE TABLE do_it (
//        num BIGINT AUTO_INCREMENT PRIMARY KEY,
//        title VARCHAR(255),
//content VARCHAR(255)
//);