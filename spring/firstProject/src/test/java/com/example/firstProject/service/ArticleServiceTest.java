package com.example.firstProject.service;

import com.example.firstProject.dto.ArticleForm;
import com.example.firstProject.entity.Article;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Test
    void index() {
        //예상
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));

        //실제
        List<Article> articles = articleService.index();
        //검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공__존재하는_id_입력() {
        //예상
        Long id = 1L;
        Article expected = new Article(1L, "가가가가", "1111");
        //실제
        Article article = articleService.show(id);
        //비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_성공__존재하지_않는__id_입력() {
        //예상
        Long id = -1L;
        Article expected = null;
        //실제
        Article article = articleService.show(id);
        //비교
        assertEquals(expected, article);
    }

    @Test
    void create_성공__title과_content만_있는_dto_입력() {
        //예상
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);
        //실제
        Article article = articleService.create(dto);
        //비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void create_실패__id가_포함된_dto_입력() {
        //예상
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(4L, title, content);
        Article expected = null;
        //실제
        Article article = articleService.create(dto);
        //비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void update() {
        //준비
        Long id = 1L;
        String newTitle = "가가가가(수정)";
        String newContent = "1111(수정)";
        ArticleForm dto = new ArticleForm(id, newTitle, newContent);
        //수정할 데이터 준비
        //수행 - 실제 서비스 호출
        Article updated = articleService.update(id,dto);

        //검증
        assertNotNull(updated); //결과가 null 아니어야함
        assertEquals(id, updated.getId());
        assertEquals(newTitle, updated.getTitle());
        assertEquals(newContent, updated.getContent());
    }

    @Test
    @Transactional
    void delete() {
        //준비
        Long id = 2L;
        //수행
        Article deleted = articleService.delete(id);
        //검증 : 삭제된 엔티티 반환
        assertNotNull(deleted);
        assertEquals(id, deleted.getId());
        //검증 : 실제로 조회 안됨

        Article after = articleService.show(id); //다시 조회했을때
        assertNull(after); //없어야 정상 -> 삭제 후 DB에 진짜 없는지까지 확인

    }
}