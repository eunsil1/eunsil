package com.example.crud2Api.controller;

import com.example.crud2Api.dto.DoDto;
import com.example.crud2Api.entity.DoIt;
import com.example.crud2Api.service.DoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doits")
public class DoApiController {

    private final DoService doService; //불변성 보장, 테스트 쉬움, 강제 주입

    public DoApiController(DoService doService) {
        this.doService = doService;
    }

    @GetMapping
    public List<DoIt> list(){
        return doService.findAll();
    }

    @GetMapping("/{num}")
    public ResponseEntity<DoIt> get(@PathVariable("num") Long num){
        return doService.findById(num)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    //.map 데이터가 존재하면 데이터 꺼내서 200 ok 상태코드와 ResponseEntity 객체변환
    //.orElse 데이터가 없을때 404 NOT FOUND 바디없이 헤더만 있는 응답

    @PostMapping
    public ResponseEntity<DoIt> create(@RequestBody DoDto dto){
        DoIt saved = doService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    //@RequestBody json 데이터를 -> java 객체로 자동 변환
    //HttpStatus.CREATED 201 생성 성공

    @PutMapping("/{num}")
    public ResponseEntity<DoIt> update(@PathVariable("num") Long num,
                                       @RequestBody DoDto dto){
        dto.setNum(num);
        return doService.update(dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{num}")
    public ResponseEntity<Void> delete(@PathVariable("num") Long num){
        if (doService.delete(num)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
