package com.example.game_ranking.controller;

import com.example.game_ranking.dto.MyScoreResponse;
import com.example.game_ranking.dto.RankingResponse;
import com.example.game_ranking.dto.ScoreRequest;
import com.example.game_ranking.entity.Score;
import com.example.game_ranking.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scores")
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService scoreService;

    // 점수 등록
    // POST /api/scores
    @PostMapping
    public ResponseEntity<Score> saveScore(@RequestBody ScoreRequest request,
                                           Authentication authentication){ //로그인 정보 자동 주입
        // 로그인 안했으면 거부
        if(authentication == null) {
            return ResponseEntity.status(401).build();
        }

        // 로그인한 유저의 username 가져오기
        String username = authentication.getName();
        Score saved = scoreService.saveScore(username, request);
        return ResponseEntity.ok(saved);
    }

    // 게임별 TOP 10 랭킹 조회
    // GET /api/scores/ranking/1
    @GetMapping("/ranking/{gameId}")
    public ResponseEntity<List<RankingResponse>> getRanking(@PathVariable Long gameId){
        return ResponseEntity.ok(scoreService.getRanking(gameId));
    }

    // 특정 유저의 게임별 점수 기록
    // GET /api/scores/user/1/game/2
    @GetMapping("/user/{userId}/game/{gameId}")
    public ResponseEntity<List<Score>> getUserScores(
            @PathVariable Long userId,
            @PathVariable Long gameId){
        return ResponseEntity.ok(scoreService.getUserScores(userId, gameId));
    }

    // 내 점수 기록 조회
    // GET /api/scores/my
    @GetMapping("/my")
    public ResponseEntity<List<MyScoreResponse>> getMyScores(Authentication authentication){
        if(authentication == null){
            return ResponseEntity.status(401).build();
        }
        String username = authentication.getName();
        return ResponseEntity.ok(scoreService.getMyScores(username));
    }
}
