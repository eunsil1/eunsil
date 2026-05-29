package com.example.game_ranking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class RankingResponse { //랭킹 응답

    private int rank; //순위
    private String username; //유저 이름
    private Integer score; //점수
    private LocalDateTime playedAt;
}
