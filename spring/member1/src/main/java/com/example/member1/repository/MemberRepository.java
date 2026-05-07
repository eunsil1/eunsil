package com.example.member1.repository;

import com.example.member1.entity.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
    List<Member> findAll();

    Optional<Member> findByEmail(String email);
    //직접 구현 안해도 "메서드 이름"으로 쿼리를 자동 생성
    //findBy + Email
    //select * from member where email = ?
}