package com.example.workshop01.config;

import com.example.workshop01.security.CustomOAuth2UserService;
import com.example.workshop01.security.CustomOidcUserService;
import com.example.workshop01.security.LoginUserDetailsService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOidcUserService   customOidcUserService;
    private final LoginUserDetailsService loginUserDetailsService;

    @Value("${app.frontend-url:http://localhost:5174}")
    private String frontendUrl;

    public SecurityConfig(
            @Lazy CustomOAuth2UserService customOAuth2UserService,
            @Lazy CustomOidcUserService customOidcUserService,
            LoginUserDetailsService loginUserDetailsService) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.customOidcUserService   = customOidcUserService;
        this.loginUserDetailsService = loginUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(loginUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity http,
            AuthenticationProvider authenticationProvider) throws Exception {
        AuthenticationManagerBuilder builder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.authenticationProvider(authenticationProvider);
        return builder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthenticationProvider authenticationProvider) throws Exception {

        http
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOriginPatterns(List.of(
                            "http://localhost:5173",
                            "http://localhost:5174",
                            "http://localhost:5175"
                    ));
                    config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
                    config.setAllowedHeaders(List.of("*"));
                    config.setAllowCredentials(true);
                    return config;
                }))
                .csrf(csrf -> csrf.disable())
                .authenticationProvider(authenticationProvider)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/register", "/uploads/**", "/error").permitAll()
                        .requestMatchers("/oauth2/**", "/login/oauth2/**").permitAll()
                        .requestMatchers(HttpMethod.GET,  "/logout").permitAll()
                        .requestMatchers(HttpMethod.POST, "/register").permitAll()
                        .requestMatchers(HttpMethod.GET,  "/posts").permitAll()
                        .requestMatchers(HttpMethod.GET,  "/posts/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/members").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/logout").permitAll()
                        .requestMatchers(HttpMethod.GET,  "/api/posts").permitAll()
                        .requestMatchers(HttpMethod.GET,  "/api/posts/**").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex
                        .defaultAuthenticationEntryPointFor(
                                (req, res, e) -> res.sendError(
                                        HttpServletResponse.SC_UNAUTHORIZED, "로그인이 필요합니다."),
                                PathPatternRequestMatcher.withDefaults().matcher("/api/**")
                        )
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .userInfoEndpoint(ui -> ui
                                .userService(customOAuth2UserService)
                                .oidcUserService(customOidcUserService))
                        .successHandler((req, res, auth) ->
                                res.sendRedirect(frontendUrl + "/posts"))
                        .failureHandler((req, res, e) ->
                                res.sendRedirect(frontendUrl + "/login?error=oauth"))
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(
                                PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.GET, "/logout"))
                        .logoutSuccessUrl("/posts")
                );

        return http.build();
    }
}