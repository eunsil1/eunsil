package com.example.roomfit.Repository;

import com.example.roomfit.domain.Member;
import com.example.roomfit.domain.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member ,Long> {
    Optional<Member> findByLoginId(String loginId);
    //1. 로그인 인증 처리 / 회원 정보 조회

    Optional<Member> findByEmail(String email);
    //2. 이메일로 아이디/비밀번호 찾기/중복검사

    Optional<Member> findByNameAndEmail(String name, String email);
    //3. 아이디 찾기(이름과 이메일 일치여부)

    boolean existsByLoginId(String loginId);
    //4. 회원가입 시 아이디 중복확인

    boolean existsByEmail(String email);
    //5. 회원가입 시 이메일 중복확인

    List<Member> findByStatus(MemberStatus status);
    //6. 특정상태 : 정지회원, 탈퇴회원 목록 조회

    Long countByStatus(MemberStatus status);
    //7. 대시보드 통계용(현재 활동중인 회원 수)
}
