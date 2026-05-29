package com.example.game_ranking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MyScoreResponse {

    private String gameName; //게임이름
    private String category; //카테고리
    private Integer score; //점수
    private LocalDateTime playedAt;
}
