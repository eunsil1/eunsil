package com.example.workshop1.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    /* ── 작업형 3: 이미지 목록 ────────────────── */
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostImage> images = new ArrayList<>();

    protected Post() {}

    public static Post create(String title, String content, Member member) {
        Post p = new Post();
        p.title   = title;
        p.content = content;
        p.member  = member;
        return p;
    }

    public void update(String title, String content) {
        this.title   = title;
        this.content = content;
    }

    public Long         getId()      { return id; }
    public String       getTitle()   { return title; }
    public String       getContent() { return content; }
    public Member       getMember()  { return member; }
    public List<PostImage> getImages() { return images; }
}
