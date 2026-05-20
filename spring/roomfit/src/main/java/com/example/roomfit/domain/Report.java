package com.example.roomfit.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Member reporter; //신고한 사람

    @Column(nullable = false, length = 20)
    private String targetType; //신고대상종류

    @Column(nullable = false)
    private  Long targetId; //신고대상 id

    @Column(nullable = false, length = 500)
    private String reason; //신고 이유(욕설, 광고성)

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ReportStatus status = ReportStatus.PENDING; //처리 상태 - 기본(PENDING)

    @Column(length = 500)
    private String adminNote; //관리자메모(처리시 작성)

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now(); //신고시각

}
//targetType = "community_post" targetId = 5 - 커뮤니티 게시판의 5번글 신고

