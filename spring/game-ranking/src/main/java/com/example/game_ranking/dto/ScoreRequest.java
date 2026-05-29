package com.example.game_ranking.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScoreRequest { //점수 등록 요청

    private Long gameId;
    private Integer score;
}
