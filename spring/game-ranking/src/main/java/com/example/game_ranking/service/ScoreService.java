package com.example.game_ranking.service;

import com.example.game_ranking.dto.MyScoreResponse;
import com.example.game_ranking.dto.RankingResponse;
import com.example.game_ranking.dto.ScoreRequest;
import com.example.game_ranking.entity.Game;
import com.example.game_ranking.entity.Score;
import com.example.game_ranking.entity.User;
import com.example.game_ranking.repository.GameRepository;
import com.example.game_ranking.repository.ScoreRepository;
import com.example.game_ranking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScoreService {

    private final GameRepository gameRepository;
    private final ScoreRepository scoreRepository;
    private final UserRepository userRepository;

    // 점수 등록
    @Transactional
    public Score saveScore(String username, ScoreRequest request){
        //로그인한 유저를 username으로 찾기
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        Game game = gameRepository.findById(request.getGameId())
                .orElseThrow(() -> new IllegalArgumentException("게임을 찾을 수 없습니다."));
        Score score = new Score();
        score.setUser(user);
        score.setGame(game);
        score.setScore(request.getScore());

        return scoreRepository.save(score);
    }

    // 게임별 TOP 10 랭킹 조회
    public List<RankingResponse> getRanking(Long gameId){
        List<Score> scores = scoreRepository.findTop10ByGameIdOrderByScoreDesc(gameId);

        List<RankingResponse> ranking = new ArrayList<>();
        int rank = 1;
        for(Score s : scores){
            ranking.add(new RankingResponse(
                    rank++,
                    s.getUser().getUsername(),
                    s.getScore(),
                    s.getPlayedAt()
            ));
        }
        return ranking;

    }

    // 특정 유저의 게임별 점수 기록
    public List<Score> getUserScores(Long userId, Long gameId){
        return scoreRepository.findByUserIdAndGameIdOrderByPlayedAtDesc(userId, gameId);
    }

    //내 점수 기록 (username으로 조회)
    public List<MyScoreResponse> getMyScores(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        List<Score> scores = scoreRepository.findByUserIdOrderByPlayedAtDesc(user.getId());

        List<MyScoreResponse> result = new ArrayList<>();
        for(Score s : scores){
            result.add(new MyScoreResponse(
                    s.getGame().getName(),
                    s.getGame().getCategory(),
                    s.getScore(),
                    s.getPlayedAt()
            ));
        }
        return result;
    }
}
