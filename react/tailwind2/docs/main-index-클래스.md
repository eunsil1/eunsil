# `src/main.jsx` · `src/index.css`

## `main.jsx`

Tailwind **클래스 문자열 없음**. `App.jsx`와 `./index.css`만 불러옵니다.

| 항목 | 설명 |
|------|------|
| `ReactDOM.createRoot` | React 18 마운트 |
| `React.StrictMode` | 개발 시 검사 |
| `import './index.css'` | Tailwind v4 진입 (`@import "tailwindcss"`) |

---

## `index.css` (Tailwind CSS **v4**)

| 구문 / 규칙 | 용도 |
|-------------|------|
| `@import "tailwindcss";` | v4 단일 진입 (구 `@tailwind base/components/utilities` 대체) |
| `@theme { ... }` | 테마 확장 — 예: `--color-primary`, `--color-secondary` → `bg-primary` 등 |
| `body { ... }` | 전역 폰트·마진 (일반 CSS) |

빌드는 **`@tailwindcss/vite`** 플러그인(`vite.config.js`)으로 처리합니다. PostCSS용 `tailwindcss` 플러그인은 사용하지 않습니다.

---

## 제거된 파일 (v3 → v4)

- `tailwind.config.js` — 설정은 `index.css`의 `@theme` 등으로 이전 가능
- `postcss.config.js` — Vite 전용 플러그인 사용 시 불필요
