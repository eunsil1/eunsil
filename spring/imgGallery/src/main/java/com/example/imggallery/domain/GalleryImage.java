package com.example.imggallery.domain;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name="gallery_images")
public class GalleryImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String originalFilename;

    /** 디스크 파일명 (UUID + 확장자) */
    @Column(nullable = false, length = 255, unique = true)
    private String storedFilename;

    //LocalDateTime - 서버시간 기준
    //Instant (utc 기준 - 글로벌 기준)
    @Column(nullable = false)
    private Instant createdAt;

    protected GalleryImage() {
    }

    public GalleryImage(String originalFilename, String storedFilename, Instant createdAt) {
        this.originalFilename = originalFilename;
        this.storedFilename = storedFilename;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public String getStoredFilename() {
        return storedFilename;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
