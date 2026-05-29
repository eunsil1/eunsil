package com.example.game_ranking.config;

import com.example.game_ranking.entity.Game;
import com.example.game_ranking.entity.Score;
import com.example.game_ranking.entity.User;
import com.example.game_ranking.repository.GameRepository;
import com.example.game_ranking.repository.ScoreRepository;
import com.example.game_ranking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;



@Component
@RequiredArgsConstructor
public class DataInitializer  implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ScoreRepository scoreRepository;
    private final GameRepository gameRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        //이미 데이터가 있으면 중복 생성 방지
        if (userRepository.count() > 0) {
            System.out.println("데이터가 이미 존재합니다.");
            return;
        }

        //1. 유저생성
        User[] users ={
                createUser("speedrunner", "고수왕"),
                createUser("casualgamer", "초보탈출"),
                createUser("proplayer", "랭킹1위"),
                createUser("newbie", "겜린이"),
                createUser("tetris_god", "테트리스신")
        };
        userRepository.saveAll(Arrays.asList(users));

        // ===== 2. 게임 생성 =====
        Game tetris  = createGame("테트리스", "블록을 쌓아 줄을 없애는 클래식 퍼즐", "퍼즐");
        Game snake   = createGame("스네이크", "먹이를 먹고 몸을 키우는 게임", "아케이드");
        Game game2048 = createGame("2048", "같은 숫자를 합쳐 2048을 만들기", "퍼즐");
        Game flappy  = createGame("플래피버드", "장애물을 피해 멀리 날아가기", "아케이드");
        Game minesweeper = createGame("지뢰찾기", "지뢰를 피해 칸을 여는 두뇌게임", "전략");

        gameRepository.saveAll(Arrays.asList(tetris, snake, game2048, flappy, minesweeper));

        // ===== 3. 점수 생성 =====
        // 테트리스 점수
        saveScore(users[0], tetris, 152000);
        saveScore(users[2], tetris, 248000);
        saveScore(users[4], tetris, 310000);
        saveScore(users[1], tetris, 89000);
        saveScore(users[3], tetris, 45000);

        // 스네이크 점수
        saveScore(users[0], snake, 1250);
        saveScore(users[2], snake, 2100);
        saveScore(users[1], snake, 870);
        saveScore(users[3], snake, 540);

        // 2048 점수
        saveScore(users[2], game2048, 32768);
        saveScore(users[0], game2048, 16384);
        saveScore(users[4], game2048, 20480);
        saveScore(users[1], game2048, 8192);

        // 플래피버드 점수
        saveScore(users[3], flappy, 42);
        saveScore(users[0], flappy, 128);
        saveScore(users[2], flappy, 95);

        // 지뢰찾기 점수 (낮을수록 좋지만, 일단 점수로 저장)
        saveScore(users[4], minesweeper, 850);
        saveScore(users[1], minesweeper, 620);
        saveScore(users[2], minesweeper, 990);

        System.out.println("✅ 더미데이터 생성 완료!");
        System.out.println("   - 유저 " + userRepository.count() + "명");
        System.out.println("   - 게임 " + gameRepository.count() + "개");
        System.out.println("   - 점수 " + scoreRepository.count() + "건");

    }

    //헬퍼 메서드
    private Game createGame(String name, String description, String category) {
        Game game = new Game();
        game.setName(name);
        game.setDescription(description);
        game.setCategory(category);
        return game;
    }


    private User createUser(String username, String nickname) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode("1234")); //임시 비밀번호(나중에 암호화)
        user.setEmail(username + "@test.com");
        return user;
    }


    private void saveScore(User user, Game game, int score){
        Score s = new Score();
        s.setUser(user);
        s.setGame(game);
        s.setScore(score);
        scoreRepository.save(s);
    }
}
