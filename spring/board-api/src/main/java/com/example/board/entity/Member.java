package com.example.board.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 회원 엔티티
 * - username : 로그인 ID (unique)
 * - password : 평문 저장 (실습용; 운영에서는 BCrypt 등 사용)
 * - nickname : 화면 표시 이름
 */
@Entity
@Table(name = "members")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 50)
    private String nickname;

    /* ── 연관관계 (양방향) ─────────────────────────── */
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    /* ── 기본 생성자 (JPA 필수) ───────────────────── */
    protected Member() {}

    /* ── 정적 팩토리 ──────────────────────────────── */
    public static Member create(String username, String password, String nickname) {
        Member m = new Member();
        m.username = username;
        m.password = password;
        m.nickname = nickname;
        return m;
    }

    /* ── Getters ──────────────────────────────────── */
    public Long getId()       { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getNickname() { return nickname; }
    public List<Post> getPosts() { return posts; }
}
