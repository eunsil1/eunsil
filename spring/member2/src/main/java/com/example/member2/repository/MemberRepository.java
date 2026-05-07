package com.example.member2.repository;

import com.example.member2.entity.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long > {
    List<Member> findAll();

    Optional<Member> findByEmail(String email);
}
