package com.example.roomfit.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name; //상품명

    @Column(nullable = false)
    private int price; //가격

    @Builder.Default
    private int stock = 100; //재고 - 기본값 100개

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private InteriorStyle styleTag; //미니멀, 북유럽

    private BigDecimal roomSizeMin; //추천 최소 평수
    private BigDecimal roomSizeMax; //추천 최대 평수

    @Column(length = 255)
    private String imagePath; //이미지 경로

    @Builder.Default
    private double avgRating = 0.0; //평균 평점

    @Builder.Default
    private int reviewCount = 0; //리뷰수

    @Builder.Default
    private boolean onSale = true; //판매여부 기본값 - true

    /** 목록·상세용 이미지 URL (없으면 placeholder) */
    public String getDisplayImagePath() {
        if (imagePath != null && !imagePath.isBlank()) {
            return imagePath; //imagePath가 있으면 그 url 사용
        }
        return "https://via.placeholder.com/400x300?text=No+Image"; //없으면 더미데이터 이미지 넣기
    }
}
