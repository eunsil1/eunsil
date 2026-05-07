package com.example.member2.service;

import com.example.member2.dto.MemberDto;
import com.example.member2.entity.Member;
import com.example.member2.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public void create(MemberDto dto){
        Member member = new Member(dto);
        memberRepository.save(member);
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public Member findById(Long id){
        return memberRepository.findById(id).orElse(null);
    }



    public boolean isEmailRegistered(String email) {
        if(email == null || email.isBlank()){
            return false;
        }
        return memberRepository.findByEmail(email.trim()).isPresent();
    }

    public void update(Long id, MemberDto dto) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if(optionalMember.isPresent()){
            Member member = optionalMember.get();
            member.updateFromDto(dto);
            memberRepository.save(member);
        }
    }

    public void delete(Long id) {
        memberRepository.deleteById(id);
    }
}
