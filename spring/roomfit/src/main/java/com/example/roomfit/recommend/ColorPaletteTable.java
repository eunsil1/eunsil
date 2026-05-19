package com.example.roomfit.recommend;

import com.example.roomfit.domain.InteriorStyle;
import java.util.List;
import java.util.Map;

public final class ColorPaletteTable {
    //스타일별 추천 색상표
    //Map.of(..) 불변 Map, 앱 기동 시 한번만 만들어짐
    private static final Map<InteriorStyle, List<String>> PALETTES = Map.of(
            InteriorStyle.MINIMAL, List.of("#F5F5F5", "#E0E0E0", "#333333", "#FFFFFF"),
            InteriorStyle.SCANDINAVIAN, List.of("#FFFFFF", "#D4C4B0", "#5C7A6B", "#F0EBE3"),
            InteriorStyle.MODERN, List.of("#1A1A1A", "#C0C0C0", "#FFFFFF", "#4A4A4A"),
            InteriorStyle.EMOTIONAL, List.of("#F8E8EE", "#C9B8D9", "#E8D5B7", "#FFFFFF"),
            InteriorStyle.BUDGET, List.of("#FAFAFA", "#B0BEC5", "#FF7043", "#EEEEEE"),
            InteriorStyle.BEGINNER, List.of("#FFF8E1", "#90CAF9", "#FFFFFF", "#8D6E63"));

    private ColorPaletteTable() {
    }

    //등록된 스타일 -> 해당 4색
    //없거나 null에 대비 -> .MINIMAL 팔레트 기본값
    public static List<String> get(InteriorStyle style) {
        return PALETTES.getOrDefault(style, PALETTES.get(InteriorStyle.MINIMAL));
    }
}

//PALETTES 내용
//InteriorStyle enum 하나당 색 4개 (벽·포인트·액센트 등을 상징하는 조합):
//
//스타일   색 예시 (의도)
//MINIMAL
//밝은 회색·흰·진회
//        SCANDINAVIAN
//흰·베이지·그린 톤
//MODERN
//블랙·실버·화이트
//        EMOTIONAL
//파스텔 핑크·라벤더
//        BUDGET
//저비용·오렌지 포인트
//BEGINNER
//밝은 노랑·블루 (쉬운 톤)