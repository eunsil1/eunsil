# Tailwind1 — 파일별 클래스 정리 (목차)

이 프로젝트는 **Tailwind CSS v4** + **`@tailwindcss/vite`** 를 사용합니다. v3와 이름이 바뀐 유틸이 있으므로(예: `bg-linear-to-r`, `shadow-xs`, `outline-hidden`) 아래 표는 **현재 소스 기준**입니다.

각 소스 파일에서 쓴 **Tailwind 유틸리티 클래스**를 용도별로 묶었습니다. (동일 클래스는 섹션마다 반복될 수 있음)

| 문서 | 대상 파일 |
|------|-----------|
| [App-클래스.md](./App-클래스.md) | `src/App.jsx` |
| [ButtonExamples-클래스.md](./ButtonExamples-클래스.md) | `src/components/ButtonExamples.jsx` |
| [CardExamples-클래스.md](./CardExamples-클래스.md) | `src/components/CardExamples.jsx` |
| [FormExamples-클래스.md](./FormExamples-클래스.md) | `src/components/FormExamples.jsx` |
| [LayoutExamples-클래스.md](./LayoutExamples-클래스.md) | `src/components/LayoutExamples.jsx` |
| [NavigationExamples-클래스.md](./NavigationExamples-클래스.md) | `src/components/NavigationExamples.jsx` |
| [main-index-클래스.md](./main-index-클래스.md) | `src/main.jsx`, `src/index.css` |

### v4 참고 (이름만 다른 경우)

- 선형 그라데이션: `bg-gradient-to-*` → **`bg-linear-to-*`**
- 작은 그림자: `shadow-sm` → **`shadow-xs`** (v3 `shadow-sm`에 가깝게 쓰려면 v4에서는 `shadow-xs` 등으로 조정)
- 포커스 아웃라인 숨김(접근성 유지): `outline-none` → **`outline-hidden`**
