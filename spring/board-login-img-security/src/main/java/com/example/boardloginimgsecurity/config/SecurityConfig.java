package com.example.boardloginimgsecurity.config;

import com.example.boardloginimgsecurity.security.LoginUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(
            LoginUserDetailsService loginUserDetailsService,
            //명부 관리자 - 사용자가 입력한 아이디보고 db에서 해당 회원정보를 가진 객체를 찾아오는 역할
            PasswordEncoder passwordEncoder) { //암호전문가 - 사용자 입력 암호와 db 암호 비교 판독
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(loginUserDetailsService);
        //인증처리(경비원)
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean //url 접근권한 + 로그인/로그아웃 동작 정의
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http, AuthenticationProvider authenticationProvider) throws Exception {
        http.authenticationProvider(authenticationProvider);
        //인증 provider 등록 -> LoginUserDetailService + PasswordEncoder 조합 여기에 들어감
        //로그인 할 때 db 인증을 이 방식으로 해라
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/","/login","/register","/uploads/**","/error").permitAll()
                //.requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/logout").permitAll()
                .requestMatchers(HttpMethod.POST, "/register").permitAll()
                .requestMatchers("/posts/write").authenticated()
                .requestMatchers("/posts/*/edit").authenticated()
                .requestMatchers(HttpMethod.POST, "/posts/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/posts").permitAll() //조회는 누구나
                .requestMatchers(HttpMethod.GET, "/posts/**").permitAll()
                .requestMatchers("/home").authenticated()
                .anyRequest().authenticated() //명시 안한 url -> 전부 로그인 필요
        );

        http.formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .failureUrl("/login?error")
                .permitAll());
        http.logout(logout -> logout
                .logoutRequestMatcher(PathPatternRequestMatcher.pathPattern(HttpMethod.GET, "/logout"))
                .logoutSuccessUrl("/posts"));
        return http.build();

    }

}
