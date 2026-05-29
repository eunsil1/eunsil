package com.example.game_ranking.service;

import com.example.game_ranking.dto.SignupRequest;
import com.example.game_ranking.entity.User;
import com.example.game_ranking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    @Transactional
    public User register(SignupRequest request){
        //중복체크
        if(userRepository.existsByUsername(request.getUsername())){
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }
        if(userRepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword())); //비밀번호 암호화해서 저장
        user.setEmail(request.getEmail());

        return userRepository.save(user);
    }
}
