package com.example.board.entity;

import jakarta.persistence.*;

/**
 * 게시글 엔티티
 * - Member 와 @ManyToOne FK 연관관계
 * - title, content 는 Not Null
 */
@Entity
@Table(name = "posts")
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    /* ── 연관관계 ──────────────────────────────────── */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    /* ── 기본 생성자 ───────────────────────────────── */
    protected Post() {}

    /* ── 정적 팩토리 ──────────────────────────────── */
    public static Post create(String title, String content, Member member) {
        Post p = new Post();
        p.title   = title;
        p.content = content;
        p.member  = member;
        return p;
    }

    /* ── 수정 메서드 ──────────────────────────────── */
    public void update(String title, String content) {
        this.title   = title;
        this.content = content;
    }

    /* ── Getters ──────────────────────────────────── */
    public Long getId()      { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public Member getMember()  { return member; }
}
