package com.example.roomfit.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//회원이 상품에 남긴 리뷰 (평점 + 후기)를 저장하는 jpa 엔티티
@Entity
@Table(name = "product_reviews", uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "member_id"}))
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProductReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //상품 1개 리뷰 여러개
    @JoinColumn(nullable = false)
    private Product product; //리뷰 대상 상품

    @ManyToOne(fetch = FetchType.LAZY) //회원 1명 리뷰 여러개
    @JoinColumn(nullable = false)
    private Member member; //작성자

    @Column(nullable = false)
    private int rating; //1~5점 평점

    @Column(columnDefinition = "TEXT")
    private String content; //후기 내용

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
