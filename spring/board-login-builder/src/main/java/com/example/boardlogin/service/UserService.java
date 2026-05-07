package com.example.boardlogin.service;


import com.example.boardlogin.domain.User;
import com.example.boardlogin.repository.UserRepository;
import com.example.boardlogin.web.dto.RegisterForm;
import com.example.boardlogin.web.dto.UserProfileDto;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User  findByUsername(String username){
        return userRepository.findByUsername(username).orElse(null);
    }

    public void register(RegisterForm form) {
//      User user =  userRepository.findByUsername(username).orElse(null);
//        if(user != null){
//        }
        if(userRepository.existsByUsername(form.getUsername())){
            throw new IllegalArgumentException("이미 사용중인 아이디입니다.");
        }
        //userRepository.save(new User(username, password, name));
        User user = User.builder()
                .username(form.getUsername())
                .password(form.getPassword())
                .name(form.getName())
                .build();
        userRepository.save(user);

    }

    public User login(String username, String password) {
//        return  userRepository.findByUsername(username)
//                .filter(u-> u.getPassword().equals(password))
//                .orElse(null);
        Optional<User> optionalUser =  userRepository.findByUsername(username);
        if(optionalUser.isPresent()){
           User user  = optionalUser.get();
           if(user.getPassword().equals(password)){
               return user;
           }
        }
        return null;

//        User user = userRepository.findByUsername(username).orElse(null);
//
//        if (user == null) {
//            return null;
//        }
//
//        if (user.getPassword().equals(password)) {
//            return user;
//        }
//
//        return null;

    }

    public UserProfileDto loadProfile(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("회원이 없습니다."));
        return UserProfileDto.builder()
                .username(user.getUsername())
                .name(user.getName())
                .build();
    }
}
