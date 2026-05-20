package com.example.roomfit.service;

import com.example.roomfit.repository.UserProfileRepository;
import com.example.roomfit.domain.Member;
import com.example.roomfit.domain.UserProfile;
import com.example.roomfit.dto.ProfileFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final MemberService memberService;

    @Transactional
    public void saveOrUpdate(Long memberId, ProfileFormDto dto){
        Member member = memberService.findById(memberId); //멤버 조회
        UserProfile profile = userProfileRepository.findByMemberId(memberId)
                .orElse(UserProfile.builder().member(member).build());
        //member만 연결한 빈 UserProfile 생성
        profile.setRoomSize(dto.getRoomSize());
        profile.setBudget(dto.getBudget());
        profile.setPreferredStyle(dto.getPreferredStyle());
        profile.setLifeStyle(dto.getLifestyle());
        profile.setHasFurniture(dto.isHasFurniture());
        profile.setSleepPattern(dto.getSleepPattern());
        profile.setUpdatedAt(LocalDateTime.now());
        userProfileRepository.save(profile);
    }//memberId로 회원 조회 - 기존 프로필 조회 - 있으면 수정 없으면 새 객체 생성
    //dto값 세팅 저장

    public UserProfile findByMemberId(Long memberId){
        return userProfileRepository.findByMemberId(memberId)
                .orElse(null);
    }


}
