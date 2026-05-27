package com.example.workshop1.service;

import com.example.workshop1.entity.Member;
import com.example.workshop1.repository.MemberRepository;
import com.example.workshop1.security.LoginUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() ->
                    new UsernameNotFoundException("사용자 없음: " + username));
        return new LoginUserDetails(member);
    }
}
