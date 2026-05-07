package com.example.crud2.service;

import com.example.crud2.dto.DoDto;
import com.example.crud2.entity.DoIt;
import com.example.crud2.repository.DoRepository;
import jakarta.transaction.Transactional;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoService {

    private final DoRepository doRepository;

    public DoService(DoRepository doRepository) {
        this.doRepository = doRepository;
    }

    @Transactional
    public DoIt create(DoDto dto){
//        DoIt entity = new DoIt(null ,dto.getTitle(), dto.getContent());
//        DoIt doit = DoIt.builder()
//                .title(dto.getTitle())
//                .content(dto.getContent())
//                .build();
        DoIt doit = DoIt.from(dto);
        return doRepository.save(doit);
    }

    public List<DoIt> findAll() {
        return doRepository.findAll();
    }

    public Optional<DoIt> findById(Long num) {
        return doRepository.findById(num);
    }

    @Transactional
    public Optional<DoIt> update(DoDto dto) {
        if(dto.getNum() == null){
            return Optional.empty();
        }
//        Optional<DoIt> result = doRepository.findById(dto.getNum());
//        if(result.isPresent()){
//            DoIt existing = result.get();
//
//            //기존 객체를 수정
//            existing.setTitle(dto.getTitle());
//            existing.setContent(dto.getContent());
//            return Optional.of(existing); //save 안해도 됨(영속성 컨텍스트)
//        }else{
//            return Optional.empty();
//        }

        return doRepository.findById(dto.getNum()).map(existing -> {
            //DoIt merged = new DoIt(dto.getNum(), dto.getTitle(), dto.getContent());
            existing.update(dto.getTitle(), dto.getContent());
            //builder 쓰는 이유는 가독성, 파라미터 순서 실수 방지
            //new DoIt("내용","제목") - 생성자 방식은 문제 발생
            return existing;
            //Dirty Checking
            //jpa 엔티티 변경사항을 자동으로 감지해서 db를 업데이트
        });
        //@Transactional 안에서는 값만 바꾸면 자동으로 update - Dirty Checking
    }

    @Transactional
    public boolean delete(Long num) {
//        if문 방식
//        Optional<DoIt> result = doRepository.findById(num);
//        if(result.isPresent()){
//            doRepository.delete(result.get());
//            return true;
//        }else{
//            return false;
//        }
        return doRepository.findById(num)
                .map(entity -> {
                    doRepository.delete(entity);
                    return true;
                }).orElse(false);
    }
}

