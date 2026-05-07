package com.example.secondProject.service;

import com.example.secondProject.dto.CommentDto;
import com.example.secondProject.entity.Article;
import com.example.secondProject.entity.Comment;
import com.example.secondProject.repository.ArticleRepository;
import com.example.secondProject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public List<CommentDto> comments(Long articleId){
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        return commentRepository.findByArticleId(articleId)//commentRepository 목록 조회
                .stream()//stream 변경
                .map(comment -> CommentDto.createCommentDto(comment)) //createCommentDto를 통해 comment를 하나하나 전달하여 dto 변환
                .collect(Collectors.toList());
    }

    public CommentDto create(Long articleId, CommentDto dto) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없음"));

        Comment comment = Comment.createComment(dto, article);


        Comment created = commentRepository.save(comment);


        return CommentDto.createCommentDto(created);
    }

    public CommentDto update(Long id, CommentDto dto) {
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));
        target.patch(dto);
        Comment updated = commentRepository.save(target);
        return CommentDto.createCommentDto(updated);
    }

    public CommentDto delete(Long id) {
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상 댓글이 없음"));
        commentRepository.delete(target);
        return CommentDto.createCommentDto(target);
    }
}
