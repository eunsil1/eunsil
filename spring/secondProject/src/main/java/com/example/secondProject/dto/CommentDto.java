package com.example.secondProject.dto;

import com.example.secondProject.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class CommentDto {

    private Long id;

    @JsonProperty("article_id")
    private Long articleId;
    private String nickname;
    private String body;

    public static CommentDto createCommentDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .articleId(comment.getArticle().getId())
                .nickname(comment.getNickname())
                .body(comment.getBody())
                .build();
    }
}
