package com.example.board.dto;

import com.example.board.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class MemberDto {

    /* ── 요청 DTO ──────────────────────────────────── */
    public record RegisterRequest(
            @NotBlank(message = "아이디는 필수입니다.")
            @Size(min = 3, max = 50, message = "아이디는 3~50자 이내여야 합니다.")
            String username,

            @NotBlank(message = "비밀번호는 필수입니다.")
            @Size(min = 4, message = "비밀번호는 4자 이상이어야 합니다.")
            String password,

            @NotBlank(message = "닉네임은 필수입니다.")
            @Size(max = 50, message = "닉네임은 50자 이내여야 합니다.")
            String nickname
    ) {}

    /* ── 응답 DTO ──────────────────────────────────── */
    public record Response(
            Long id,
            String username,
            String nickname,
            LocalDateTime createdAt
    ) {
        public static Response from(Member member) {
            return new Response(
                    member.getId(),
                    member.getUsername(),
                    member.getNickname(),
                    member.getCreatedAt()
            );
        }
    }
}
