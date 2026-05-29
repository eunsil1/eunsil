package com.example.game_ranking.controller;

import com.example.game_ranking.entity.Game;
import com.example.game_ranking.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    // 전체 게임 목록 조회
    // GET /api/games
    @GetMapping
    public ResponseEntity<List<Game>> getAllGames(){
        return ResponseEntity.ok(gameService.getAllGames());
    }

    // 게임 단건 조회
    // GET /api/games/1
    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGame(@PathVariable Long gameId){
        return ResponseEntity.ok(gameService.getGame(gameId));
    }

    // 카테고리별 조회
    // GET /api/games/category?name=퍼즐
    @GetMapping("/category")
    public ResponseEntity<List<Game>> getGamesByCategory(@RequestParam String name){
        return ResponseEntity.ok(gameService.getGamesByCategory(name));
    }

    // 게임 등록
    // POST /api/games
    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game){
        return ResponseEntity.ok(gameService.createGame(game));
    }
}
