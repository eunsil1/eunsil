package com.example.firstProject.controller;

import com.example.firstProject.dto.ArticleForm;
import com.example.firstProject.dto.CommentDto;
import com.example.firstProject.entity.Article;
import com.example.firstProject.repository.ArticleRepository;
import com.example.firstProject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j //로깅을 위한 롬복 어노테이션
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        //System.out.println(form.toString());
        log.info(form.toString());

        //1. dto -> entity로 변환
        Article article = form.toEntity();
        //System.out.println(article.toString());
        log.info(article.toString());

        //2. Repository에게 Entity를 DB로 저장
        Article saved = articleRepository.save(article);
        //System.out.println(saved.toString());
        log.info(saved.toString());


        return "redirect:/articles/" + saved.getId();
    }

    //단건조회
    @GetMapping("/articles/{id}") // /articles/2 - 해당 url 요청을 처리선언
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);

        //1. id로 데이터를 조회해서 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);
        //2. 가져온 데이터를 모델에 등록
        model.addAttribute("article", articleEntity); //게시글
        model.addAttribute("commentDtos", commentDtos); //댓글
        //3. 보여줄 페이지를 설정
        return "articles/show";
    }

    //모든 데이터 조회
    @GetMapping("/articles")
    public String index(Model model){
        //1. 모든 Article을 가져온다.
        List<Article> articleEntityList = articleRepository.findAll();
        //2. 가져온 Article 묶음을 뷰로 전달
        model.addAttribute("articleList", articleEntityList);
        //3. 뷰 페이지 설정
        return "articles/index";

    }

    //수정화면
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //모델에 등록
        model.addAttribute("article", articleEntity);

        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());
        //1. DTO를 Entity로 변환
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        //2. Entity를 DB에 저장
        //2-1 DB에서 기존 데이터를 가져옴
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        //2-2 기존 데이터가 있다면, 값을 갱신
        if (target != null){
            articleRepository.save(articleEntity);
        }
        //3. 수정 결과 페이지로 리다이렉트
        return "redirect:/articles/"+ articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다.");
        //0. 상세페이지에 빨간색 삭제 버튼 만들기
        //1. 삭제 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        //2. 대상을 삭제
        if(target != null){
            articleRepository.delete(target);
        }
        //3. 결과 페이지(리스트)로 리다이렉트
        return "redirect:/articles";
    }
}
