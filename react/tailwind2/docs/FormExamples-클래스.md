# `src/components/FormExamples.jsx` — Tailwind 클래스 (v4)

## 공통

| 클래스 | 용도 |
|--------|------|
| `space-y-8` | 섹션 간격 |
| `text-3xl` `font-bold` `mb-4` `text-gray-800` | h2 |
| `text-gray-600` `mb-6` | 설명 |
| `text-xl` `font-semibold` `mb-4` `text-gray-700` | h3 |
| `space-y-4` `max-w-md` | 필드 세로 간격·최대 너비 |

## 라벨

| 클래스 | 용도 |
|--------|------|
| `block` `text-sm` `font-medium` `text-gray-700` `mb-2` | 라벨 한 줄 |

## 입력 (input / select 공통 패턴)

| 클래스 | 용도 |
|--------|------|
| `w-full` `px-4` `py-2` | 전체 너비·패딩 |
| `border` `border-gray-300` `rounded-lg` | 테두리 |
| `focus:ring-2` `focus:ring-blue-500` `focus:border-transparent` | 포커스 링 |
| `outline-hidden` `transition` | v4 — 포커스 시 보이지 않는 아웃라인(접근성)·전환 |
| `bg-white` (select) | select 배경 |

## 2. Textarea

| 클래스 | 용도 |
|--------|------|
| `resize-none` | 크기 조절 비활성 |

## 3. 체크박스 / 라디오

| 클래스 | 용도 |
|--------|------|
| `flex` `items-center` `gap-2` `cursor-pointer` | 라벨 한 줄 정렬 |
| `w-4` `h-4` `text-blue-500` `rounded` (checkbox) | 체크박스 크기 |
| `focus:ring-2` `focus:ring-blue-500` | 포커스 |
| `space-y-2` | 라디오 항목 간격 |

## 4. 로그인 폼 박스

| 클래스 | 용도 |
|--------|------|
| `max-w-md` `mx-auto` | 가운데·최대 너비 |
| `bg-white` `border` `border-gray-200` `rounded-xl` `shadow-lg` `p-8` | 카드 |
| `text-2xl` `font-bold` `text-center` `mb-6` | 제목 |
| `py-3` (input) | 입력란 세로 패딩 약간 큼 |

## 로그인 행 (유지 / 링크)

| 클래스 | 용도 |
|--------|------|
| `flex` `items-center` `justify-between` | 좌우 배치 |
| `text-sm` `text-gray-600` | 보조 텍스트 |
| `text-sm` `text-blue-500` `hover:underline` | 링크 |

## 제출 버튼

| 클래스 | 용도 |
|--------|------|
| `w-full` `py-3` `bg-linear-to-r` `from-blue-500` `to-purple-500` | 전체 너비 선형 그라데이션 (v4) |
| `text-white` `rounded-lg` `font-semibold` | 글자·모서리 |
| `hover:shadow-md` `transform` `hover:scale-105` `transition` | 호버 시 강조 |

## 하단 회원가입 문구

| 클래스 | 용도 |
|--------|------|
| `text-center` `text-sm` `text-gray-600` | 가운데·작은 글씨 |
