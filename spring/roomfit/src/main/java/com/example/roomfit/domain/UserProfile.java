package com.example.roomfit.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_profiles")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY) //member 하나에 프로필 1개
    @JoinColumn(nullable = false, unique = true)
    private Member member;

    //방크기 8.5평 12.0평, 전체 자리수 4, 소수점 자리수 1
    @Column(nullable = false, precision = 4, scale = 1)
    private BigDecimal roomSize;

    //예산
    @Column(nullable = false)
    private Integer budget;

    //인테리어 스타일
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private InteriorStyle preferredStyle;

    //생활패턴
    @Column(length = 50)
    private String lifeStyle;

    //가구 보유 현황
    @Builder.Default
    @Column(nullable = false)
    private boolean hasFurniture = false;

    //수면패턴
    @Column(length = 20)
    private String sleepPattern;

    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();


}
