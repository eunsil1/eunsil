# `src/components/ButtonExamples.jsx` — Tailwind 클래스 (v4)

## 공통 (섹션 래퍼)

| 클래스 | 용도 |
|--------|------|
| `space-y-8` | 세로로 자식 간격 |
| `text-3xl` `font-bold` `mb-4` `text-gray-800` | 페이지 소제목 |
| `text-gray-600` `mb-6` | 설명 문단 |
| `text-xl` `font-semibold` `mb-4` `text-gray-700` | 각 섹션 제목 (h3) |

## 1. 기본 버튼

| 클래스 | 용도 |
|--------|------|
| `flex` `flex-wrap` `gap-4` | 버튼 가로 배치·줄바꿈 |
| `px-6` `py-2` `rounded` | 패딩·모서리 |
| `bg-{blue\|gray\|green\|red}-500` `text-white` | 배경색 |
| `hover:bg-*-600` `transition` | 호버 시 진한 색·전환 |

## 2. 아웃라인 버튼

| 클래스 | 용도 |
|--------|------|
| `border-2` `border-*-500` `text-*-500` | 테두리·글자색 |
| `hover:bg-*-500` `hover:text-white` | 호버 시 채우기 |

## 3. 크기

| 클래스 | 용도 |
|--------|------|
| `flex` `flex-wrap` `items-center` `gap-4` | 세로 가운데 정렬 |
| `px-3` `py-1` `text-sm` | Small |
| `px-6` `py-2` | Medium |
| `px-8` `py-3` `text-lg` | Large |

## 4. 그라디언트 버튼

| 클래스 | 용도 |
|--------|------|
| `bg-linear-to-r` + `from-*` `to-*` | 선형 그라데이션 (v4) |
| `rounded-lg` `shadow-md` | 모서리·그림자 |
| `hover:shadow-lg` `transform` `hover:scale-105` | 호버 시 그림자·확대 |

## 5. 아이콘 버튼

| 클래스 | 용도 |
|--------|------|
| `flex` `items-center` `gap-2` | 아이콘·텍스트 정렬 |

## 6. 로딩 & 비활성

| 클래스 | 용도 |
|--------|------|
| `animate-spin` (아이콘 span) | 회전 애니메이션 |
| `bg-gray-300` `text-gray-500` `cursor-not-allowed` | 비활성 스타일 |
