package com.example.boardloginimgsecurityoauth.security;

//카카오 구글 네이버 에서 받은 사용자 정보를 공통 형식으로 담은 dto
public class OAuthUserProfile {

    private final String providerUserId;
    private final String email;
    private final String name;

    public OAuthUserProfile(String providerUserId, String email, String name) {
        this.providerUserId = providerUserId;
        this.email = email;
        this.name = name;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
