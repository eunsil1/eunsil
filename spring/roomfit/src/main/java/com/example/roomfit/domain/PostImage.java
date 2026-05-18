package com.example.roomfit.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "post_images")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class PostImage { //인테리어 게시글(InteriorPost)에 - 이미지 여러장

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //여러 포스트 이미지 -> 하나의 인테리어 포스트
    @JoinColumn(nullable = false) //post_id FK, 반드시 어떤 글에 속해야함
    private InteriorPost post;

    @Column(nullable = false, length = 255)
    private String filePath; //저장된 파일 경로

    @Builder.Default
    private boolean thumbnail = false;

    @Builder.Default //한 글에서 이미지 표시 순서
    private int sortOrder = 0;
}
