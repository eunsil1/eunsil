package com.example.secondProject.controller;

import com.example.secondProject.dto.ArticleForm;
import com.example.secondProject.entity.Article;
import com.example.secondProject.repository.ArticleRepository;
import com.example.secondProject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ArticleApiController {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleService articleService;

//    @GetMapping("/api/hello")
//    public String hello(){
//        return "hello world!";
//    }

    //GET
    //모두 조회
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleService.index();
    }

//    [
//    { "id": 1, "title": "...", "content": "..." }
//    { "id": 2, "title": "...", "content": "..." }
//    ]

    //단건조회
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleService.show(id);
    }

    //POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){
        Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.CREATED).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
//    {
//        "title": "제목",
//            "content": "내용"
//    }

    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto){
        Article updated = articleService.update(id,dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        //상태 코드 + 데이터 반환
    }

    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() : //204
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        //데이터조회 -> 없으면 400 -> 있으면 삭제
    }
    //조회 - GET
    //생성 - POST
    //수정 - PATCH
    //삭제 - DELETE
    //@RequestBody JSON - java 객체로 변경
    //ResponseEntity 상태 코드 제어 200 - OK 400 - BAD_REQUEST
    //JSON 통신 - 프론트와 데이터 주고받기
}
