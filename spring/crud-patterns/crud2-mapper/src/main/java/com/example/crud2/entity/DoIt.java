package com.example.crud2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//jpa 엔티티 생성시 기본 생성자 필요 - 외부에서 무분별한 생성을 막기위해 PROTECTED 사용
//@AllArgsConstructor
public class DoIt {

    @Id//기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //db 자동생성 auto increment
    private Long num;

    @Column //테이블 컬럼 - 생략가능
    private String title;

    @Column
    private String content;

    public DoIt(Long num, String title, String content) {
    this.num = num;
    this.title = title;
    this.content = content;
}}

//@Entity - 이타입은 영속성 대상이고, 테이블과 매핑
//CREATE TABLE do_it (
//        num BIGINT AUTO_INCREMENT PRIMARY KEY,
//        title VARCHAR(255),
//content VARCHAR(255)
//);