package com.example.roomfit.service;

import com.example.roomfit.dto.RecommendResultDto;
import com.example.roomfit.recommend.RecommendEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommendService {

    //recommendEngine - 복잡한 알고리즘 계산 수행
    //RecommendService - 비즈니스 요구사항을 처리
    //복잡한 계산은 엔진이 하고, 외부에서 그 기능을 쓸수 있게 서비스
    private final RecommendEngine recommendEngine;

    public RecommendResultDto getRecommendations(Long memberId){
        return recommendEngine.recommend(memberId);
    }
}
