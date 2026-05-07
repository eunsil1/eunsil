package com.example.boardloginimg.service;


import com.example.boardloginimg.domain.User;
import com.example.boardloginimg.repository.UserRepository;
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

    public void register(String username, String password, String name) {
//      User user =  userRepository.findByUsername(username).orElse(null);
//        if(user != null){
//        }
        if(userRepository.existsByUsername(username)){
            throw new IllegalArgumentException("이미 사용중인 아이디입니다.");
        }
        userRepository.save(new User(username, password, name));
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
}
