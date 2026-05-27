package com.example.workshop1.dto;

import com.example.workshop1.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class MemberDto {

    public record RegisterRequest(
        @NotBlank(message = "아이디는 필수입니다.")
        @Size(min = 3, max = 50)
        String username,

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Size(min = 4)
        String password,

        @NotBlank(message = "닉네임은 필수입니다.")
        @Size(max = 50)
        String nickname
    ) {}

    public record Response(Long id, String username, String nickname, LocalDateTime createdAt) {
        public static Response from(Member m) {
            return new Response(m.getId(), m.getUsername(), m.getNickname(), m.getCreatedAt());
        }
    }
}
