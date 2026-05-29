package com.example.workshop01.service;


import com.example.workshop01.domain.User;
import com.example.workshop01.repository.UserRepository;
import com.example.workshop01.security.OAuthUserProfile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User  findByUsername(String username){
        return userRepository.findByUsername(username).orElse(null);
    }

    public void register(String username, String password, String name) {
        if(userRepository.existsByUsername(username)){
            throw new IllegalArgumentException("이미 사용중인 아이디입니다.");
        }
        userRepository.save(new User(username, passwordEncoder.encode(password), name));
    }

    @Transactional
    public User registerOrUpdateOAuthUser(String registrationId, OAuthUserProfile profile) {
        return userRepository
                .findByOauthProviderAndOauthProviderSubject(registrationId, profile.getProviderUserId())
                .map(existing -> updateOAuthProfile(existing, profile))
                .orElseGet(() -> {
                    if (profile.getEmail() != null && !profile.getEmail().isBlank()) {
                        return userRepository.findByEmail(profile.getEmail())
                                .map(existing -> linkOAuthAccount(existing, registrationId, profile))
                                .orElseGet(() -> createOAuthUser(registrationId, profile));
                    }
                    return createOAuthUser(registrationId, profile);
                });
    }

    private User updateOAuthProfile(User user, OAuthUserProfile profile) {
        user.setName(profile.getName());
        if (profile.getEmail() != null && !profile.getEmail().isBlank()) {
            user.setEmail(profile.getEmail());
        }
        return userRepository.save(user);
    }

    private User linkOAuthAccount(User user, String registrationId, OAuthUserProfile profile) {
        user.setOauthProvider(registrationId);
        user.setOauthProviderSubject(profile.getProviderUserId());
        user.setName(profile.getName());
        if (profile.getEmail() != null && !profile.getEmail().isBlank()) {
            user.setEmail(profile.getEmail());
        }
        return userRepository.save(user);
    }

    private User createOAuthUser(String registrationId, OAuthUserProfile profile) {
        User user = new User(
                generateUniqueUsername(registrationId, profile.getProviderUserId()),
                passwordEncoder.encode(UUID.randomUUID().toString()),
                profile.getName());
        user.setEmail(profile.getEmail());
        user.setOauthProvider(registrationId);
        user.setOauthProviderSubject(profile.getProviderUserId());
        return userRepository.save(user);
    }

    private String generateUniqueUsername(String registrationId, String providerUserId) {
        String base = registrationId + "_" + providerUserId;
        if (base.length() > 45) {
            base = base.substring(0, 45);
        }
        String username = base;
        int suffix = 1;
        while (userRepository.existsByUsername(username)) {
            username = base + "_" + suffix++;
            if (username.length() > 50) {
                username = registrationId + "_" + suffix;
            }
        }
        return username;
    }




    }

