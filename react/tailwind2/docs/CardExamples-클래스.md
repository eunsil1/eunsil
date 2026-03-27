# `src/components/CardExamples.jsx` — Tailwind 클래스 (v4)

## 공통

| 클래스 | 용도 |
|--------|------|
| `space-y-8` | 섹션 간 세로 간격 |
| `text-3xl` `font-bold` `mb-4` `text-gray-800` | h2 |
| `text-gray-600` `mb-6` | 설명 |
| `text-xl` `font-semibold` `mb-4` `text-gray-700` | h3 |
| `grid` `gap-6` | 그리드 간격 |

## 1. 기본 카드

| 클래스 | 용도 |
|--------|------|
| `grid-cols-1` `md:grid-cols-3` | 모바일 1열·중간 이상 3열 |
| `bg-white` `border` `border-gray-200` `rounded-lg` `p-6` | 카드 박스 |
| `shadow-xs` `hover:shadow-sm` `transition` | 그림자·호버 |
| `text-lg` `font-bold` `mb-2` | 카드 제목 |
| `text-gray-600` | 본문 |

## 2. 이미지 카드

| 클래스 | 용도 |
|--------|------|
| `md:grid-cols-2` `lg:grid-cols-3` | 반응형 열 수 |
| `overflow-hidden` `shadow-md` `hover:shadow-lg` | 이미지 클립·그림자 |
| `transform` `hover:-translate-y-1` | 호버 시 살짝 위로 |
| `h-48` | 이미지 영역 높이 |
| `bg-linear-to-br` `from-*` `to-*` | 플레이스홀더 그라데이션 (v4) |
| `p-6` | 본문 패딩 |
| `text-xl` `font-bold` `mb-2` | 제목 |
| `text-gray-600` `mb-4` | 설명 |
| `w-full` `py-2` `bg-*-500` `text-white` `rounded` `hover:bg-*-600` `transition` | 전체 너비 버튼 |

## 3. 프로필 카드

| 클래스 | 용도 |
|--------|------|
| `md:grid-cols-3` | 3열 그리드 |
| `rounded-xl` `shadow-lg` `p-6` `text-center` | 카드·가운데 정렬 |
| `w-24` `h-24` `rounded-full` `mx-auto` `mb-4` | 원형 아바타 |
| `text-xl` `font-bold` `mb-1` | 이름 |
| `text-gray-500` `mb-4` | 직함 |
| `flex` `justify-center` `gap-3` | 버튼 가운데 |
| `px-4` `py-2` `rounded-lg` | 팔로우 버튼 |
| `border` `border-gray-300` `hover:bg-gray-50` | 아웃라인 메시지 버튼 |

## 4. 통계 카드

| 클래스 | 용도 |
|--------|------|
| `md:grid-cols-4` | 4열 |
| `bg-linear-to-br` `from-*-500` `to-*-600` | 카드 배경 그라데이션 (v4) |
| `rounded-lg` `p-6` `text-white` `shadow-lg` | 모서리·패딩·글자색 |
| `text-3xl` `font-bold` `mb-2` | 숫자 |
| `text-{blue\|green\|purple\|orange}-100` | 부제목 색 |
