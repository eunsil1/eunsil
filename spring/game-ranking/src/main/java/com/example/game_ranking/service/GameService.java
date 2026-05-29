package com.example.game_ranking.service;

import com.example.game_ranking.entity.Game;
import com.example.game_ranking.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GameService {

    private final GameRepository gameRepository;

    // 전체 게임 목록
    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }

    // 게임 단건 조회
    public Game getGame(Long gameId){
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("게임을 찾을 수 없습니다."));
    }

    // 카테고리별 조회
    public List<Game> getGamesByCategory(String category){
        return gameRepository.findByCategory(category);
    }

    // 게임 등록
    @Transactional
    public Game createGame(Game game){
        return gameRepository.save(game);
    }


}
