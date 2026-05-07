package com.example.firstProject.service;

import com.example.firstProject.dto.ArticleForm;
import com.example.firstProject.entity.Article;
import com.example.firstProject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;


    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        //생성할때 id를 넣었으면 null을 리턴
        if(article.getId() != null){
            return  null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        //1. dto -> Entity로 변환
        Article article = dto.toEntity();
        log.info("id:{},article:{}",id,article.toString());

        //2. 타겟 조회
        Article target = articleRepository.findById(id).orElse(null);

        //3. 잘못된 요청
        if (target == null || id != article.getId()){
            //400, 잘못된 요청 응답
            log.info("잘못된 요청 id:{},article:{}",id,article.toString());
            return null;
        }

        //4. 업데이트 및 정상 응답(200)
        target.patch(article); //일부만 변경
        Article updated = articleRepository.save(target);
        return updated;
    }

    public Article delete(Long id) {
        //대상찾기
        Article target = articleRepository.findById(id).orElse(null);

        //잘못된 요청 처리
        if (target == null){
            return null;
        }

        //대상삭제
        articleRepository.delete(target);
        return target;
    }


}
