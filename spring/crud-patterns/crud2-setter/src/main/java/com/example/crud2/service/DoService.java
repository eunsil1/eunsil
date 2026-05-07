package com.example.crud2.service;

import com.example.crud2.dto.DoDto;
import com.example.crud2.entity.DoIt;
import com.example.crud2.repository.DoRepository;
import jakarta.transaction.Transactional;
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
        //DoIt entity = new DoIt(null ,dto.getTitle(), dto.getContent());
        DoIt entity = new DoIt();
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        return doRepository.save(entity);
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
            existing.setTitle(dto.getTitle());
            existing.setContent(dto.getContent());
            return doRepository.save(existing);
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

