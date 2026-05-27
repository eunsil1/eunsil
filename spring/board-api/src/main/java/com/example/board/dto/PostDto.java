package com.example.board.dto;

import com.example.board.entity.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class PostDto {

    /* ── 등록 요청 ─────────────────────────────────── */
    public record CreateRequest(
            @NotBlank(message = "제목은 필수입니다.")
            @Size(max = 200, message = "제목은 200자 이내여야 합니다.")
            String title,

            @NotBlank(message = "내용은 필수입니다.")
            String content,

            @NotNull(message = "작성자 ID는 필수입니다.")
            Long memberId
    ) {}

    /* ── 수정 요청 ─────────────────────────────────── */
    public record UpdateRequest(
            @NotBlank(message = "제목은 필수입니다.")
            @Size(max = 200, message = "제목은 200자 이내여야 합니다.")
            String title,

            @NotBlank(message = "내용은 필수입니다.")
            String content
    ) {}

    /* ── 응답 ──────────────────────────────────────── */
    public record Response(
            Long id,
            String title,
            String content,
            Long memberId,
            String authorNickname,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        public static Response from(Post post) {
            return new Response(
                    post.getId(),
                    post.getTitle(),
                    post.getContent(),
                    post.getMember().getId(),
                    post.getMember().getNickname(),
                    post.getCreatedAt(),
                    post.getUpdatedAt()
            );
        }
    }

    /* ── 목록 응답 (내용 생략) ─────────────────────── */
    public record SummaryResponse(
            Long id,
            String title,
            String authorNickname,
            LocalDateTime createdAt
    ) {
        public static SummaryResponse from(Post post) {
            return new SummaryResponse(
                    post.getId(),
                    post.getTitle(),
                    post.getMember().getNickname(),
                    post.getCreatedAt()
            );
        }
    }
}
