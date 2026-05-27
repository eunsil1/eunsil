package com.example.workshop1.entity;

import jakarta.persistence.*;

/**
 * 작업형 3 — 이미지 첨부
 * 원본 파일명, 저장 파일명, 썸네일 파일명 보관
 */
@Entity
@Table(name = "post_images")
public class PostImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String originalName;   // 사용자가 올린 파일명

    @Column(nullable = false)
    private String storedName;     // 서버에 저장된 파일명 (UUID)

    @Column(nullable = false)
    private String thumbnailName;  // 썸네일 파일명

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    protected PostImage() {}

    public static PostImage create(String originalName, String storedName,
                                   String thumbnailName, Post post) {
        PostImage img = new PostImage();
        img.originalName  = originalName;
        img.storedName    = storedName;
        img.thumbnailName = thumbnailName;
        img.post          = post;
        return img;
    }

    public Long   getId()            { return id; }
    public String getOriginalName()  { return originalName; }
    public String getStoredName()    { return storedName; }
    public String getThumbnailName() { return thumbnailName; }
    public Post   getPost()          { return post; }
}
