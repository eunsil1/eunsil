package com.example.secondProject.entity;

import com.example.secondProject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="article_Id")
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {
        if (dto.getId() != null){
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야함");
        }
        if (dto.getArticleId() != article.getId()){
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못되었습니다.");
        }
        return Comment.builder()
                .id(dto.getId())
                .article(article)
                .nickname(dto.getNickname())
                .body(dto.getBody())
                .build();
    }

    public void patch(CommentDto dto) {
        if(this.id != dto.getId()){
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id 입력");
        }
        //객체 갱신
        if(dto.getNickname() != null){
            this.nickname = dto.getNickname();
        }
        if(dto.getBody() != null){
            this.body = dto.getBody();
        }
    }
}
