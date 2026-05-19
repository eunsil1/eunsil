package com.example.roomfit.dto;

import com.example.roomfit.domain.InteriorPost;

public record ScorePostDto(InteriorPost post, int score) {

}
//dto.post(), dto.score() - 이렇게 사용 가능
//ScorePostDto dto = new ScorePostDto(post, 95);
//system.out.println(dto.score())

//record 자동 생성 기능 - 자동 생성됨
//post + score = 함께 담는 dto

//public class ScoredPostDto {
//
//    private final InteriorPost post;
//    private final int score;
//
//    public ScoredPostDto(
//            InteriorPost post,
//            int score
//    ) {
//        this.post = post;
//        this.score = score;
//    }
//
//    public InteriorPost getPost() {
//        return post;
//    }
//
//    public int getScore() {
//        return score;
//    }
//}