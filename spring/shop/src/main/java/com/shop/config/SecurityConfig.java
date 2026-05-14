package com.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
//                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form
                        .loginPage("/members/login")
                                .defaultSuccessUrl("/")
                                .usernameParameter("email")
                                .failureUrl("/members/login/error")
                                .permitAll()
                        )
                .logout(logout -> logout
                                .logoutUrl("/members/logout")
                                .logoutSuccessUrl("/")
                        )
                // 1. 인가 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                        .requestMatchers("/", "/members/**", "/item/**", "/images/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated() //그외에는 인증필요
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                        //CustomAuthenticationEntryPoint() -> 인증 실패
                );
                // 로그인 페이지는 누구나 접근 가능하게
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
