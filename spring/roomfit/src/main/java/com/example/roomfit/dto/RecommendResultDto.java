package com.example.roomfit.dto;

import com.example.roomfit.domain.InteriorStyle;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
//RecommendEngine이 계산한 결과를 컨트롤러 -> 타임리프로 넘기는 한 덩어리 응답 모델
public class RecommendResultDto {

    private final List<ScorePostDto> posts;
    private final List<String> colorPalette; //(hex 색 4개 - 스타일별)
    private final String layoutAdvice;
    private final InteriorStyle preferredStyle; //내 선호 스타일
}
