package com.example.roomfit.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminDashBoardDto {

    private final long memberCount; //ACTIVE 회원수
    private final long interiorPostCount; //인테리어 VISIBLE 글 수
    private final long communityPostCount;//커뮤니티 VISIBLE 글 수
    private final long pendingReportCount; //PENDING 신고 수
}
