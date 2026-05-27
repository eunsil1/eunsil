package com.example.workshop1.service;

import com.example.workshop1.dto.MemberDto;
import com.example.workshop1.entity.Member;
import com.example.workshop1.exception.DuplicateUsernameException;
import com.example.workshop1.exception.ResourceNotFoundException;
import com.example.workshop1.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder  passwordEncoder;

    public MemberService(MemberRepository memberRepository,
                         PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder  = passwordEncoder;
    }

    /* ── 작업형 1 : 회원가입 ─────────────────── */
    @Transactional
    public MemberDto.Response register(MemberDto.RegisterRequest req) {
        if (memberRepository.existsByUsername(req.username())) {
            throw new DuplicateUsernameException(
                "이미 사용 중인 아이디입니다: " + req.username());
        }
        String encoded = passwordEncoder.encode(req.password());
        Member member  = Member.createLocal(req.username(), encoded, req.nickname());
        return MemberDto.Response.from(memberRepository.save(member));
    }

    public MemberDto.Response findById(Long id) {
        return MemberDto.Response.from(getMemberEntity(id));
    }

    public Member getMemberEntity(Long id) {
        return memberRepository.findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException("회원을 찾을 수 없습니다. id=" + id));
    }
}
