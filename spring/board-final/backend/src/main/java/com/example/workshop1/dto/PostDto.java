package com.example.workshop1.dto;

import com.example.workshop1.entity.Post;
import com.example.workshop1.entity.PostImage;
import java.time.LocalDateTime;
import java.util.List;

public class PostDto {

    /* ── 목록 응답 ───────────────────────────── */
    public record SummaryResponse(
        Long id, String title, String authorNickname, LocalDateTime createdAt
    ) {
        public static SummaryResponse from(Post p) {
            return new SummaryResponse(
                p.getId(), p.getTitle(),
                p.getMember().getNickname(), p.getCreatedAt());
        }
    }

    /* ── 상세 응답 ───────────────────────────── */
    public record Response(
        Long id, String title, String content,
        Long memberId, String authorNickname,
        List<ImageInfo> images,
        LocalDateTime createdAt, LocalDateTime updatedAt
    ) {
        public static Response from(Post p) {
            List<ImageInfo> imgs = p.getImages().stream()
                .map(ImageInfo::from).toList();
            return new Response(
                p.getId(), p.getTitle(), p.getContent(),
                p.getMember().getId(), p.getMember().getNickname(),
                imgs, p.getCreatedAt(), p.getUpdatedAt());
        }
    }

    /* ── 이미지 정보 ─────────────────────────── */
    public record ImageInfo(Long id, String url, String thumbnailUrl) {
        public static ImageInfo from(PostImage img) {
            return new ImageInfo(
                img.getId(),
                "/uploads/" + img.getStoredName(),
                "/uploads/" + img.getThumbnailName());
        }
    }
}
