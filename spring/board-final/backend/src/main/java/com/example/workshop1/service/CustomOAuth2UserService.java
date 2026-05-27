package com.example.workshop1.service;

import com.example.workshop1.entity.Member;
import com.example.workshop1.repository.MemberRepository;
import com.example.workshop1.security.LoginUserDetails;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 작업형 4 — Google OIDC 로그인 처리
 * 최초 로그인 시 회원 자동 생성, 재로그인 시 정보 갱신
 */
@Service
public class CustomOAuth2UserService extends OidcUserService {

    private final MemberRepository memberRepository;

    public CustomOAuth2UserService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public OidcUser loadUser(OidcUserRequest userRequest)
            throws OAuth2AuthenticationException {

        OidcUser oidcUser = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration()
                                     .getRegistrationId();  // "google"
        String subject  = oidcUser.getSubject();
        String email    = oidcUser.getEmail();
        String nickname = oidcUser.getFullName() != null
                ? oidcUser.getFullName() : email;

        Member member = memberRepository
            .findByOauthProviderAndOauthProviderSubject(provider, subject)
            .map(m -> { m.updateOAuth(nickname, email); return m; })
            .orElseGet(() -> memberRepository.save(
                Member.createOAuth(email, nickname, provider, subject)));

        return new OidcLoginUserDetails(member, oidcUser);
    }

    /* ── OidcUser + LoginUserDetails 복합 구현 ─ */
    public static class OidcLoginUserDetails extends LoginUserDetails
            implements OidcUser {

        private final OidcUser delegate;

        public OidcLoginUserDetails(Member member, OidcUser delegate) {
            super(member);
            this.delegate = delegate;
        }

        @Override public java.util.Map<String, Object> getClaims()    { return delegate.getClaims(); }
        @Override public org.springframework.security.oauth2.core.oidc.OidcUserInfo getUserInfo() { return delegate.getUserInfo(); }
        @Override public org.springframework.security.oauth2.core.oidc.OidcIdToken getIdToken()  { return delegate.getIdToken(); }
        @Override public java.util.Map<String, Object> getAttributes(){ return delegate.getAttributes(); }
        @Override public String getName()                              { return delegate.getName(); }
    }
}
