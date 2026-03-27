# `src/components/NavigationExamples.jsx` — Tailwind 클래스 (v4)

## 공통

| 클래스 | 용도 |
|--------|------|
| `space-y-8` | 섹션 간격 |
| `text-3xl` `font-bold` `mb-4` `text-gray-800` | h2 |
| `text-gray-600` `mb-6` | 설명 |
| `text-xl` `font-semibold` `mb-4` `text-gray-700` | h3 |

## 1. 기본 네비게이션 바

| 클래스 | 용도 |
|--------|------|
| `bg-white` `border` `border-gray-200` `rounded-lg` `shadow-xs` | nav 박스 |
| `px-6` `py-4` `flex` `items-center` `justify-between` | 내부 한 줄 정렬 |
| `text-2xl` `font-bold` `text-blue-600` | 로고 |
| `hidden` `md:flex` `gap-6` | 중간 이상에서만 링크 가로 배치 |
| `text-gray-700` `hover:text-blue-600` `transition` `font-medium` | 링크 |
| `px-4` `py-2` `bg-blue-500` `text-white` `rounded-lg` `hover:bg-blue-600` `transition` | 로그인 버튼 |

## 2. 그라디언트 네비게이션

| 클래스 | 용도 |
|--------|------|
| `bg-linear-to-r` `from-blue-500` `to-purple-600` `rounded-lg` `shadow-lg` | 배경 (v4 선형 그라데이션) |
| `text-white` (로고·링크) | 밝은 배경 위 글자 |
| `hover:text-gray-200` | 링크 호버 |
| `bg-white` `text-purple-600` `hover:bg-gray-100` | CTA 버튼 |

## 3. 모바일 반응형 메뉴

| 클래스 | 용도 |
|--------|------|
| `bg-gray-800` `rounded-lg` `shadow-lg` | 어두운 nav |
| `md:hidden` | 햄버거는 작은 화면에서만 |
| `text-white` `p-2` `hover:bg-gray-700` `rounded` `transition` | 햄버거 버튼 |
| `hidden` `md:flex` `gap-6` | 데스크톱 링크 |
| `md:hidden` `mt-4` `space-y-2` `pb-2` | 모바일 펼침 메뉴 영역 |
| `block` `text-white` `hover:bg-gray-700` `p-2` `rounded` `transition` | 세로 링크 |

## 4. 탭 네비게이션

| 클래스 | 용도 |
|--------|------|
| `border-b` `border-gray-200` | 하단 구분선 |
| `flex` `gap-2` | 탭 가로 |
| `px-6` `py-3` `border-b-2` `border-blue-500` `text-blue-600` `font-semibold` | 활성 탭 |
| `border-transparent` `text-gray-500` `hover:text-gray-700` `hover:border-gray-300` `transition` | 비활성 탭 |

## 5. Breadcrumb

| 클래스 | 용도 |
|--------|------|
| `flex` `items-center` `gap-2` `text-sm` | 한 줄·작은 글씨 |
| `text-blue-600` `hover:underline` | 링크 |
| `text-gray-400` | 구분자 `/` |
| `text-gray-600` | 현재 페이지 (링크 아님) |

## 6. 수직 사이드바

| 클래스 | 용도 |
|--------|------|
| `flex` `gap-4` | 사이드+메인 |
| `w-64` `bg-white` `border` `border-gray-200` `rounded-lg` `p-4` | 사이드 폭 |
| `text-sm` `font-semibold` `text-gray-500` `uppercase` `mb-2` | 섹션 제목 |
| `space-y-1` | 메뉴 항목 간격 |
| `flex` `items-center` `gap-3` `px-3` `py-2` `rounded-lg` | 메뉴 한 줄 |
| `bg-blue-50` `text-blue-700` `font-medium` | 선택된 항목 |
| `text-gray-700` `hover:bg-gray-50` `transition` | 일반 항목 |
| `flex-1` `bg-gray-50` `border` `border-gray-200` `rounded-lg` `p-6` | 오른쪽 콘텐츠 |

## 7. Pill 네비게이션

| 클래스 | 용도 |
|--------|------|
| `flex` `flex-wrap` `gap-2` `bg-gray-100` `p-2` `rounded-lg` | pill 컨테이너 |
| `px-4` `py-2` `bg-blue-500` `text-white` `rounded-full` `font-medium` `shadow-xs` | 선택 pill |
| `bg-white` `text-gray-700` `rounded-full` `hover:bg-gray-50` `transition` | 비선택 pill |
