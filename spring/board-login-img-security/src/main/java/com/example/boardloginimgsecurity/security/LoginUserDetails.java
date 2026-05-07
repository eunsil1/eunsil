package com.example.boardloginimgsecurity.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

//현재 사용자 - spring security가 로그인한 사용자 정보를 다루기위한 표준 인터페이스(UserDetails)
//로그인 성공한 사용자 객체
public class LoginUserDetails implements UserDetails {
    private final Long id;
    private final String username;
    private final String password;
    private final String displayName; //화면에 보여줄 이름

    public LoginUserDetails(Long id, String username, String password, String displayName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.displayName = displayName;
    }
    //로그인 시 db에서 조회한 값을 담아온다.

    public Long getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    //모든 사용자를 user 권한 하나로 고정(Role_Admin, Role_Manager)
    //사용자가 가진 권한(Role) 목록 반환 getAuthorities - ROLE_USER

    //타임리프
//    <!-- 시큐리티 확장 태그 사용 시 -->
//    <div th:with="user=${#authentication.principal}">
//    <p>반갑습니다, <span th:text="${user.displayName}">홍길동</span>님!</p>
//    </div>

    @Override
    public String getPassword() {
        return password;
    }
    //스프링 시큐리티가 로그인 검증, 사용하는 메서드
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정잠금, 만료 , 활성화
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
