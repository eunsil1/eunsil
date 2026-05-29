package com.example.game_ranking.repository;

import com.example.game_ranking.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {

    // 카테고리별 게임 목록 조회
    List<Game> findByCategory(String category);

    // 게임 이름으로 검색
    List<Game> findByNameContaining(String keyword);
}
