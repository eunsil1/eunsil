package com.example.game_ranking.config;

import com.example.game_ranking.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    // 비밀번호 암호화 도구
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        //누구나 접근 가능
                        .requestMatchers("/api/auth/**", "/api/games/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/scores/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/scores/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/scores/**").authenticated()
                        .requestMatchers("/h2-console/**").permitAll()

                        //그 외에는 로그인 필요
                        .anyRequest().authenticated()
                );
        // 폼 로그인 사용
//            .formLogin(form -> form
//                    .loginProcessingUrl("/api/auth/login") //로그인 url 처리
//                    .defaultSuccessUrl("/api/games", true)
//                    .permitAll()
//            )
//            .logout(logout -> logout
//                    .logoutUrl("/api/auth/logout")
//                    .permitAll()
//            );
        return http.build();
    }

    // ⭐ CORS 상세 설정 Bean 추가
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5173"));  // React 주소
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);  // 세션 쿠키 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

        @Bean
        public AuthenticationManager authenticationManager (
                AuthenticationConfiguration config) throws Exception {
            return config.getAuthenticationManager();
        }
    }

