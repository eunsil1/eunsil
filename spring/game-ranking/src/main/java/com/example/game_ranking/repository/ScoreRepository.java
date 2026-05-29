package com.example.game_ranking.repository;

import com.example.game_ranking.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score ,Long> {

    // 특정 게임의 랭킹 (점수 높은 순)
    List<Score> findByGameIdOrderByScoreDesc(Long gameId);

    // 특정 유저의 전체 점수 기록
    List<Score> findByUserId(Long userId);

    // 특정 유저의 특정 게임 점수 기록
    List<Score> findByUserIdAndGameIdOrderByPlayedAtDesc(Long userId, Long gameId);

    // 특정 게임 TOP 10 랭킹
    List<Score> findTop10ByGameIdOrderByScoreDesc(Long gameId);

    //특정 유저의 모든 점수(최신순)
    List<Score> findByUserIdOrderByPlayedAtDesc(Long userID);
}
