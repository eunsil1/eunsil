package com.example.workshop1.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 작업형 1 : username / password / nickname
 * 작업형 4 : email / oauthProvider / oauthProviderSubject 추가
 */
@Entity
@Table(name = "members")
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 50)
    private String username;            // 폼 로그인용 (소셜 로그인 시 null 가능)

    private String password;            // BCrypt 암호화, 소셜 로그인 시 null

    @Column(nullable = false, length = 50)
    private String nickname;

    @Column(unique = true)
    private String email;               // 작업형 4

    @Column(length = 30)
    private String oauthProvider;       // "google" 등

    @Column(length = 200)
    private String oauthProviderSubject; // provider의 고유 ID (sub)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.USER;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    protected Member() {}

    /* ── 폼 회원가입 팩토리 ──────────────────── */
    public static Member createLocal(String username, String password, String nickname) {
        Member m = new Member();
        m.username = username;
        m.password = password;
        m.nickname = nickname;
        return m;
    }

    /* ── OAuth2 자동 가입 팩토리 ─────────────── */
    public static Member createOAuth(String email, String nickname,
                                     String provider, String subject) {
        Member m = new Member();
        m.email               = email;
        m.nickname            = nickname;
        m.oauthProvider       = provider;
        m.oauthProviderSubject = subject;
        return m;
    }

    /* ── OAuth2 재로그인 시 정보 갱신 ──────────── */
    public void updateOAuth(String nickname, String email) {
        this.nickname = nickname;
        this.email    = email;
    }

    // Getters
    public Long   getId()                  { return id; }
    public String getUsername()            { return username; }
    public String getPassword()            { return password; }
    public String getNickname()            { return nickname; }
    public String getEmail()               { return email; }
    public String getOauthProvider()       { return oauthProvider; }
    public String getOauthProviderSubject(){ return oauthProviderSubject; }
    public Role   getRole()                { return role; }
    public List<Post> getPosts()           { return posts; }
}
