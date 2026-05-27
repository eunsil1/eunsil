package com.example.workshop1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Workshop4Application {
    public static void main(String[] args) {
        SpringApplication.run(Workshop4Application.class, args);
    }
}
