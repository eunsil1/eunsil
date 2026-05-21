package com.example.roomfit.recommend;

import com.example.roomfit.domain.InteriorPost;
import com.example.roomfit.domain.InteriorStyle;
import com.example.roomfit.domain.Member;
import com.example.roomfit.domain.PostStatus;
import com.example.roomfit.domain.UserProfile;
import com.example.roomfit.dto.RecommendResultDto;
import com.example.roomfit.dto.ScorePostDto;
import com.example.roomfit.repository.InteriorPostRepository;
import com.example.roomfit.repository.MemberRepository;
import com.example.roomfit.repository.PostLikeRepository;
import com.example.roomfit.repository.UserProfileRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Builder
public class RecommendEngine {

    private final UserProfileRepository userProfileRepository;
    private final InteriorPostRepository interiorPostRepository;
    private final PostLikeRepository postLikeRepository;
    private final MemberRepository memberRepository;

    //맞춤형 인테리어 포스트(게시물) 추천엔진
    //사용자 성향 기반 (content-based) + 나와 비슷한 유저 + 실시간 인기 트렌드

    //메인 추천 로직(recommend)
    //1. 프로필 검증 : 요청한 유저의 프로필(UserProfile)이 있는지 확인
    //2. 복수 추천 데이터 수집(3가지 경로) 추천후보를 모읍니다.
    //1)유저 프로필 맞춤형 글 10개
    //2)실시간 인기글 5개(popular)
    //3)유사한 취향의 유저들이 좋아하는 글 5개 (recommendBySimilarUsers)
    //3. 결과 결함 및 부가 정보 제공 : 본인글들의 중복 제거해 최종 15개를 추리고,
    //유저 취향에 맞는 색상 팔레트와 가구 배치 팁(LayoutAdvice)에 묶어서 반환

    //알고리즘 1) 유저 프로필 기반 점수 매기기
    //인테리어 스타일 (40점): 유저가 선호하는 스타일(예: 모던, 북유럽 등)과 글의 스타일이 일치하면 가장 큰 점수를 줍니다.
    //방 크기 (최대 25점): 유저의 방 크기와 게시글의 방 크기 차이가 평수 기준으로 2평 이하이면 25점, 5평 이하이면 10점을 줍니다.
    //예산 범위 (최대 20점): 오차 범위가 20% 이하이면 20점, 50% 이하이면 5점을 줍니다.
    //가구 유무 (10점): 유저의 가구 보유 상황과 글의 태그 일치 여부를 봅니다.
    //기본 인기도 (최대 5점): 로그 함수(Math.log)를 이용해 좋아요 수가 많을수록 약간의 가산점을 더합니다.

    //알고리즘 2) 협업 필터링 - 닮은꼴 유저 추천
    //"나와 취향이 비슷한 다른 사람들은 어떤 글에 좋아요를 눌렀을까"를 찾아내는 알고리즘
    //코사인 유사도 (cosineSimilarity): 모든 유저의 방 크기, 예산, 스타일 등을 수치화(벡터 변환)하여 나와 취향이 얼마나 가까운지 방향성을 계산합니다.
    //이웃 선정: 유사도가 0.3보다 높은 상위 5명의 유저(이웃)를 뽑습니다.
    //추천 점수 계산: 이 5명이 좋아요를 누른 글 중 내가 아직 좋아요를 누르지 않은 글을 추출합니다. 나랑 많이 닮은 유저가 좋아한 글일수록 점수를 더 높게 주어 최종 상위 5개를 선별합니다.

    //알고리즘 ③: 실시간 인기도 및 시간 가속도 분해 (popularityScore)
    //단순히 좋아요가 많은 글만 보여주면 옛날 글만 계속 추천되는 문제가 생깁니다. 이를 해결하기 위해 시간에 따른 점수 감쇄(Decay) 로직이 들어가 있습니다.
    //기본 점수: 조회수 * 0.1 + 좋아요 * 2 + 댓글수 * 1.5 로 환산합니다.
    //시간 패널티: 작성된 지 7일(일주일) 주기로 점수가 점점 깎이도록 분모를 키웁니다. 즉, 아무리 인기가 많았던 글이라도 시간이 오래 지나면 점수가 낮아져 최신 트렌드 글이 올라올 수 있도록 돕습니다.

    //5. 부가 기능: 맞춤형 레이아웃 조언 (buildLayoutAdvice)
    //유저의 방 상황에 맞춰 텍스트로 된 인테리어 팁을 조합해 줍니다.
    //방이 10평 미만으로 좁다면? "접이식 테이블을 쓰세요", "침대를 창 반대에 두세요" 같은 팁을 추가합니다.
    //재택근무를 하는 유저라면? "책상은 자연광 옆에 두세요" 같은 라이프스타일 맞춤형 조언을 붙여줍니다.


    @Transactional(readOnly = true)
    public RecommendResultDto recommend(Long memberId) {
        UserProfile profile = userProfileRepository.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalStateException("추천 프로필을 먼저 등록해 주세요."));

        List<InteriorPost> candidates = interiorPostRepository
                .findTop20ByStatusOrderByLikeCountDescViewCountDescCreatedAtDesc(PostStatus.VISIBLE);

        List<ScorePostDto> profileBased = scoreByProfile(profile, candidates, 10);
        List<InteriorPost> popular = interiorPostRepository
                .findTop20ByStatusOrderByLikeCountDescViewCountDescCreatedAtDesc(
                        PostStatus.VISIBLE, PageRequest.of(0, 5));
        List<ScorePostDto> cfBased = recommendBySimilarUsers(memberId, profile, 5);

        List<ScorePostDto> merged = mergeResults(profileBased, cfBased, popular);
        List<String> colors = ColorPaletteTable.get(profile.getPreferredStyle());
        String layout = buildLayoutAdvice(profile);

        return RecommendResultDto.builder()
                .posts(merged)
                .colorPalette(colors)
                .layoutAdvice(layout)
                .preferredStyle(profile.getPreferredStyle())
                .build();
    }

    private List<ScorePostDto> scoreByProfile(UserProfile profile, List<InteriorPost> candidates, int limit) {
        return candidates.stream()
                .map(post -> new ScorePostDto(post, calculateProfileScore(profile, post)))
                .filter(s -> s.score() > 0) //점수 0점 초과만 남김
                .sorted(Comparator.comparingInt(ScorePostDto::score).reversed()) //점수 내림차순
                .limit(limit) //상위 10건만 추출
                .toList();
    }

    int calculateProfileScore(UserProfile profile, InteriorPost post) {
        int score = 0;
        if (post.getStyle() == profile.getPreferredStyle()) {
            score += 40;
        }
        if (post.getRoomSize() != null) {
            double diff = post.getRoomSize().subtract(profile.getRoomSize()).abs().doubleValue();
            if (diff <= 2) {
                score += 25;
            } else if (diff <= 5) {
                score += 10;
            }
        }
        if (profile.getBudget() > 0 && post.getBudget() != null) {
            double ratio = Math.abs(post.getBudget() - profile.getBudget()) / (double) profile.getBudget();
            if (ratio <= 0.2) {
                score += 20;
            } else if (ratio <= 0.5) {
                score += 5;
            }
        }
        if (post.isHasFurnitureTag() == profile.isHasFurniture()) {
            score += 10;
        }
        score += Math.min(5, (int) Math.log(post.getLikeCount() + 1));
        return score;
    }

    private List<ScorePostDto> recommendBySimilarUsers(Long memberId, UserProfile profile, int limit) {
        List<Member> others = memberRepository.findAll().stream()
                .filter(m -> !m.getId().equals(memberId))
                .toList();

        List<SimilarMember> neighbors = others.stream()
                .map(other -> {
                    UserProfile otherProfile = userProfileRepository.findByMemberId(other.getId()).orElse(null);
                    double sim = otherProfile == null ? 0 : cosineSimilarity(profile, otherProfile);
                    return new SimilarMember(other.getId(), sim);
                })
                .filter(s -> s.similarity > 0.3)
                .sorted(Comparator.comparingDouble(SimilarMember::similarity).reversed())
                .limit(5)
                .toList();

        if (neighbors.isEmpty()) {
            return List.of();
        }

        List<Long> neighborIds = neighbors.stream().map(SimilarMember::memberId).toList();
        List<Long> likedPostIds = postLikeRepository.findPostIdsLikedByMembers(neighborIds);
        Set<Long> myLikes = postLikeRepository.findByMemberId(memberId).stream()
                .map(pl -> pl.getPost().getId())
                .collect(Collectors.toSet());

        Map<Long, Double> cfScores = likedPostIds.stream()
                .filter(id -> !myLikes.contains(id))
                .collect(Collectors.groupingBy(id -> id, Collectors.summingDouble(id -> {
                    double sum = 0;
                    for (SimilarMember n : neighbors) {
                        boolean liked = postLikeRepository.existsByMemberIdAndPostId(n.memberId(), id);
                        if (liked) {
                            sum += n.similarity() * 2;
                        }
                    }
                    return sum;
                })));

        return cfScores.entrySet().stream()
                .sorted(Map.Entry.<Long, Double>comparingByValue().reversed())
                .limit(limit)
                .map(e -> interiorPostRepository.findById(e.getKey())
                        .map(p -> new ScorePostDto(p, e.getValue().intValue()))
                        .orElse(null))
                .filter(java.util.Objects::nonNull)
                .toList();
    }

    private double cosineSimilarity(UserProfile a, UserProfile b) {
        double[] va = toVector(a);
        double[] vb = toVector(b);
        double dot = 0, na = 0, nb = 0;
        for (int i = 0; i < va.length; i++) {
            dot += va[i] * vb[i];
            na += va[i] * va[i];
            nb += vb[i] * vb[i];
        }
        if (na == 0 || nb == 0) {
            return 0;
        }
        return dot / (Math.sqrt(na) * Math.sqrt(nb));
    }

    private double[] toVector(UserProfile p) {
        double sizeNorm = p.getRoomSize().doubleValue() / 30.0;
        double budgetNorm = p.getBudget() / 1000.0;
        double styleNorm = p.getPreferredStyle().ordinal() / (double) InteriorStyle.values().length;
        double furniture = p.isHasFurniture() ? 1.0 : 0.0;
        return new double[] {sizeNorm, budgetNorm, styleNorm, furniture};
    }

    private List<ScorePostDto> mergeResults(
            List<ScorePostDto> profileBased,
            List<ScorePostDto> cfBased,
            List<InteriorPost> popular) {
        Set<Long> seen = new LinkedHashSet<>();
        List<ScorePostDto> result = new ArrayList<>();
        for (ScorePostDto dto : profileBased) {
            if (seen.add(dto.post().getId())) {
                result.add(dto);
            }
        }
        for (ScorePostDto dto : cfBased) {
            if (seen.add(dto.post().getId())) {
                result.add(dto);
            }
        }
        for (InteriorPost post : popular) {
            if (seen.add(post.getId())) {
                result.add(new ScorePostDto(post, popularityScore(post)));
            }
        }
        return result.stream().limit(15).toList();
    }

    public static int popularityScore(InteriorPost post) {
        double base = post.getViewCount() * 0.1 + post.getLikeCount() * 2 + post.getCommentCount() * 1.5;
        long days = ChronoUnit.DAYS.between(post.getCreatedAt(), LocalDateTime.now());
        double decay = 1.0 / (1.0 + days / 7.0);
        return (int) (base * decay);
    }

    private String buildLayoutAdvice(UserProfile profile) {
        List<String> lines = new ArrayList<>();
        if (profile.getRoomSize().compareTo(BigDecimal.valueOf(10)) < 0) {
            lines.add("침대 헤드를 창 반대편 벽에 두어 동선을 확보하세요.");
            lines.add("접이식 테이블·소파베드를 검토해 보세요.");
        }
        if (!profile.isHasFurniture()) {
            lines.add("침대·책상·수납 3종을 먼저 배치한 뒤 소품을 추가하세요.");
        }
        if ("재택".equals(profile.getLifeStyle())) {
            lines.add("책상은 자연광 옆, 모니터는 창과 수직으로 배치하세요.");
        }
        if (lines.isEmpty()) {
            lines.add("가구 배치 후 통로 폭 80cm 이상을 유지하면 livable한 원룸이 됩니다.");
        }
        return String.join("\n", lines);
    }

    private record SimilarMember(Long memberId, double similarity) {}
}
