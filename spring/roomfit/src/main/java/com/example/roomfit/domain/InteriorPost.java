package com.example.roomfit.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "interior_post")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class InteriorPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Member author;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private InteriorStyle style;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private BigDecimal roomSize;
    private Integer budget;

    @Builder.Default
    private int viewCount = 0;

    @Builder.Default
    private int likeCount = 0;

    @Builder.Default
    private int commentCount = 0;

    @Builder.Default
    private boolean hasFurnitureTag = false;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private PostStatus status = PostStatus.VISIBLE;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sortOrder ASC")
    @Builder.Default
    private List<PostImage> images = new ArrayList<>();

    public void addImage(PostImage image) { //연관관계 편의 메서드
        images.add(image); //내(InteriorPost) 자식 리스트에서 이미지 추가
        image.setPost(this); //상대방(PostImage)에게 주인지정
    }
    //post.addImage(image) 호출하여도 양쪽 연결고리가 완벽하게 동기화

    public void increaseViewCount() {
        this.viewCount++;
    }

    /** 목록/메인용 썸네일 (images 가 @EntityGraph 로 로드된 뒤 사용) */
    public String getThumbnailPath() {
        if (images == null || images.isEmpty()) { //이미지가 없으면 기본이미지
            return "/images/no-image.svg";
        }
        String path = images.stream()
                .filter(PostImage::isThumbnail) //thumbnail - true(대표썸네일 필터링)
                .map(PostImage::getFilePath) //첫번째 유효한 filepath
                .filter(p -> p != null && !p.isBlank()) //null이나 빈 문자열 제거
                .findFirst()//있으면 추출
                .orElseGet(() -> images.stream() //썸네일 지정이 없으면 -> 아무 이미지나 첫장
                        .map(PostImage::getFilePath)
                        .filter(p -> p != null && !p.isBlank())
                        .findFirst() //첫번째 이미지 추출
                        .orElse(null));
        if (path == null) {
            return "/images/no-image.svg"; //데이터가 전부 유효하지 않다면 최종 반환
        }
        return path;
    }



}
