# `src/components/LayoutExamples.jsx` — Tailwind 클래스 (v4)

## 공통

| 클래스 | 용도 |
|--------|------|
| `space-y-8` | 섹션 간격 |
| `text-3xl` `font-bold` `mb-4` `text-gray-800` | h2 |
| `text-gray-600` `mb-6` | 설명 |
| `text-xl` `font-semibold` `mb-4` `text-gray-700` | h3 |
| `space-y-4` | 세로 스택 간격 |

## 1. Flexbox

| 클래스 | 용도 |
|--------|------|
| `flex` `gap-4` | 가로 flex·간격 |
| `flex-1` | 동일 비율 확장 (`flex: 1 1 0%`) |
| `flex-[2]` | 임의 값 — 두 번째 박스를 더 넓게 (v2 비율 유틸 대신) |
| `bg-*-100` `border-2` `border-*-300` `rounded-lg` `p-6` `text-center` | 박스 스타일 |
| `font-semibold` `text-*-700` | 텍스트 |

## 2. Grid

| 클래스 | 용도 |
|--------|------|
| `grid` `grid-cols-1` `md:grid-cols-2` `lg:grid-cols-4` `gap-4` | 반응형 열 수 |
| `bg-purple-100` `border-2` `border-purple-300` `rounded-lg` `p-6` `text-center` | 셀 스타일 |

## 3. 반응형 Grid

| 클래스 | 용도 |
|--------|------|
| `grid-cols-1` `sm:grid-cols-2` `lg:grid-cols-3` `xl:grid-cols-4` | 브레이크포인트별 열 |
| `text-sm` `text-gray-500` `mb-4` | 안내 문구 |

## 4. 센터 정렬

| 클래스 | 용도 |
|--------|------|
| `h-64` `bg-gray-100` `rounded-lg` | 외곽 영역 |
| `flex` `items-center` `justify-center` | 수직·수평 가운데 |
| `bg-white` `p-8` `rounded-xl` `shadow-lg` `text-center` | 안쪽 카드 |

## 5. 사이드바

| 클래스 | 용도 |
|--------|------|
| `flex` `gap-4` `h-64` | 가로 레이아웃·고정 높이 |
| `w-64` `bg-gray-800` `text-white` `rounded-lg` `p-6` | 왼쪽 사이드바 |
| `space-y-2` | 메뉴 li 간격 |
| `hover:bg-gray-700` `p-2` `rounded` `cursor-pointer` `transition` | 메뉴 호버 |
| `flex-1` `bg-gray-100` `rounded-lg` `p-6` | 오른쪽 메인 |

## 6. Holy Grail

| 클래스 | 용도 |
|--------|------|
| `border-2` `border-gray-300` `rounded-lg` `overflow-hidden` | 외곽 테두리 |
| `bg-blue-500` `text-white` `p-4` `text-center` `font-bold` | 헤더 |
| `flex` `min-h-[200px]` | 본문 3단 |
| `w-48` `bg-gray-200` `p-4` | 좌·우 사이드 |
| `flex-1` `bg-white` `p-4` | 메인 |
| `bg-gray-800` `text-white` `p-4` `text-center` | 푸터 |
