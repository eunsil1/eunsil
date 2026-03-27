# `src/App.jsx` — Tailwind 클래스 (v4)

## 최상위 래퍼

| 클래스 | 용도 |
|--------|------|
| `min-h-screen` | 최소 높이 뷰포트 전체 |
| `bg-linear-to-br` | 선형 그라데이션 방향 (↘) |
| `from-blue-50` `to-indigo-100` | 그라데이션 시작/끝 색 |

## Header

| 클래스 | 용도 |
|--------|------|
| `bg-white` `shadow-md` | 흰 배경·그림자 |
| `container` `mx-auto` `px-4` `py-6` | 가운데 정렬 컨테이너·좌우 패딩 |
| `text-4xl` `font-bold` | 제목 크기·굵기 |
| `text-transparent` `bg-clip-text` `bg-linear-to-r` `from-blue-600` `to-purple-600` | 그라데이션 텍스트 채우기 |
| `mt-2` `text-gray-600` | 부제 여백·회색 글자 |

## 탭 탐색 영역

| 클래스 | 용도 |
|--------|------|
| `container` `mx-auto` `px-4` `mt-8` | 컨테이너·상단 여백 |
| `flex` `flex-wrap` `gap-2` | 탭 버튼 줄바꿈·간격 |
| `bg-white` `p-2` `rounded-lg` `shadow-xs` | 탭 바 배경·둥근 모서리·얕은 그림자 |

### 탭 버튼 (공통)

| 클래스 | 용도 |
|--------|------|
| `px-6` `py-3` `rounded-md` `font-semibold` | 패딩·모서리·굵은 글씨 |
| `transition-all` `duration-200` | 전환 효과 |

### 탭 버튼 — 선택됨 (`activeTab === tab.id`)

| 클래스 | 용도 |
|--------|------|
| `bg-linear-to-r` `from-blue-500` `to-purple-500` | 보라-파랑 그라데이션 |
| `text-white` `shadow-lg` `transform` `scale-105` | 흰 글자·그림자·살짝 확대 |

### 탭 버튼 — 비선택

| 클래스 | 용도 |
|--------|------|
| `bg-gray-100` `text-gray-700` `hover:bg-gray-200` | 회색 배경·호버 시 진하게 |

## Main (콘텐츠)

| 클래스 | 용도 |
|--------|------|
| `container` `mx-auto` `px-4` `py-8` | 컨테이너·패딩 |
| `bg-white` `rounded-xl` `shadow-lg` `p-8` | 흰 카드·큰 둥근 모서리·그림자 |

## Footer

| 클래스 | 용도 |
|--------|------|
| `bg-gray-800` `text-white` `mt-16` | 어두운 배경·흰 글자·위 여백 |
| `container` `mx-auto` `px-4` `py-6` `text-center` | 정렬·중앙 |
| `text-gray-300` | 본문 문구 색 |
