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
        Game clickGame  = createGame("클릭 챌린지", "10초 안에 버튼을 최대한 많이 클릭하세요!", "액션");
        Game reactionGame   = createGame("반응속도 테스트", "화면이 초록색으로 바뀌는 순간 빠르게 클릭!", "반응");
        Game targetGame = createGame("타겟 클릭", "30초 안에 나타나는 타겟을 빠르게 클릭하세요!", "액션");
        Game memoryGame = createGame("숫자 기억", "순서대로 나타나는 숫자를 기억하고 입력하세요!", "두뇌");

        gameRepository.saveAll(Arrays.asList(clickGame, reactionGame, targetGame, memoryGame));

        // ===== 3. 점수 생성 =====
        // 클릭 챌린지 (클릭 횟수 - 높을수록 좋음)
        saveScore(users[0], clickGame, 87);
        saveScore(users[2], clickGame, 95);
        saveScore(users[4], clickGame, 102);
        saveScore(users[1], clickGame, 64);
        saveScore(users[3], clickGame, 51);

        // 반응속도 테스트 점수(점수화된 값 - 높을수록 좋음)
        saveScore(users[2], reactionGame, 920);
        saveScore(users[0], reactionGame, 880);
        saveScore(users[1], reactionGame, 750);
        saveScore(users[3], reactionGame, 690);

        // 타겟 클릭 (맞춘 개수 - 높을수록 좋음)
        saveScore(users[4], targetGame, 38);
        saveScore(users[0], targetGame, 42);
        saveScore(users[2], targetGame, 35);
        saveScore(users[1], targetGame, 28);

        // 숫자 기억 (기억한 자릿수 점수 - 높을수록 좋음)
        saveScore(users[2], memoryGame, 12);
        saveScore(users[0], memoryGame, 9);
        saveScore(users[4], memoryGame, 11);
        saveScore(users[3], memoryGame, 6);

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
