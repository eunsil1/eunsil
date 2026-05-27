package com.example.board.service;

import com.example.board.dto.MemberDto;
import com.example.board.entity.Member;
import com.example.board.exception.DuplicateUsernameException;
import com.example.board.exception.ResourceNotFoundException;
import com.example.board.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /* ── 회원가입 ──────────────────────────────────── */
    @Transactional
    public MemberDto.Response register(MemberDto.RegisterRequest req) {
        if (memberRepository.existsByUsername(req.username())) {
            throw new DuplicateUsernameException("이미 사용 중인 아이디입니다: " + req.username());
        }
        Member member = Member.create(req.username(), req.password(), req.nickname());
        return MemberDto.Response.from(memberRepository.save(member));
    }

    /* ── 단건 조회 ─────────────────────────────────── */
    public MemberDto.Response findById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("회원을 찾을 수 없습니다. id=" + id));
        return MemberDto.Response.from(member);
    }

    /* ── 서비스 내부 전용: 엔티티 반환 ──────────────── */
    public Member getMemberEntity(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("회원을 찾을 수 없습니다. id=" + id));
    }
}
