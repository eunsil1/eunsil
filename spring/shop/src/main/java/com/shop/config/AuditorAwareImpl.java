package com.shop.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
//현재 로그인한 사용자를 자동으로 가져오는 역할

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = "";
        if(authentication != null){
            userId = authentication.getName(); //로그인한 사용자 ID를 반환
        }
        return Optional.of(userId);
    }

}
//현재 로그인 한 사용자정보를 반환
