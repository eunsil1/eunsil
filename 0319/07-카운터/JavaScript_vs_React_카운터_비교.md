# 🔢 카운터: JavaScript vs React 비교

## 📋 목차

1. [개요](#개요)
2. [코드 비교](#코드-비교)
3. [상태 관리](#상태-관리)
4. [DOM 업데이트](#dom-업데이트)
5. [이벤트 처리](#이벤트-처리)
6. [장단점](#장단점)
7. [언제 무엇을 사용할까?](#언제-무엇을-사용할까)

---

## 개요

| 구분 | JavaScript | React |
|------|------------|-------|
| **파일 위치** | `07-카운터/index.html`, `main.js` | `counter-react/src/App.jsx` |
| **실행 방법** | HTML 파일 직접 열기 | `npm run dev` |
| **코드량** | ~35줄 | ~24줄 |

---

## 코드 비교

### JavaScript (Vanilla)

```javascript
// 1. 변수로 상태 저장
let count = 0;

// 2. DOM 요소 직접 접근
const countEl = document.getElementById('count');
const btnDecrease = document.getElementById('btnDecrease');
const btnIncrease = document.getElementById('btnIncrease');
const btnReset = document.getElementById('btnReset');

// 3. 화면 업데이트 함수 (직접 호출 필요)
function updateDisplay() {
    countEl.textContent = count;
}

// 4. 버튼 클릭 시: 변수 변경 + 수동 업데이트
function increase() {
    count++;
    updateDisplay();  // ← 반드시 호출!
}
function decrease() {
    count--;
    updateDisplay();
}
function reset() {
    count = 0;
    updateDisplay();
}

// 5. 이벤트 리스너 등록
btnIncrease.addEventListener('click', increase);
btnDecrease.addEventListener('click', decrease);
btnReset.addEventListener('click', reset);

updateDisplay();  // 초기 표시
```

### React

```jsx
import { useState } from 'react';

function Counter() {
  // 1. useState로 상태 관리
  const [count, setCount] = useState(0);

  // 2. 상태 변경 함수
  const increase = () => setCount(count + 1);
  const decrease = () => setCount(count - 1);
  const reset = () => setCount(0);

  // 3. JSX + onClick 이벤트
  return (
    <div className="counter-box">
      <h1>🔢 카운터 (React)</h1>
      <div className="count">{count}</div>
      <div className="buttons">
        <button onClick={decrease}>− 감소</button>
        <button onClick={reset}>초기화</button>
        <button onClick={increase}>+ 증가</button>
      </div>
    </div>
  );
}
```

---

## 상태 관리

| 구분 | JavaScript | React |
|------|------------|-------|
| **저장 방식** | 일반 변수 `let count` | `useState(0)` 훅 |
| **변경 방법** | `count++`, `count--` 직접 수정 | `setCount(count + 1)` 함수 호출 |
| **특징** | 변수 변경 후 화면 업데이트를 직접 호출 | `setCount` 호출 시 자동 리렌더링 |

### JavaScript 흐름

```
클릭 → count 변수 변경 → updateDisplay() 호출 → DOM 직접 수정
```

### React 흐름

```
클릭 → setCount() 호출 → 상태 변경 → React가 자동으로 리렌더링
```

---

## DOM 업데이트

| 구분 | JavaScript | React |
|------|------------|-------|
| **방식** | `element.textContent = count` 직접 수정 | `{count}` 바인딩, React가 Virtual DOM으로 처리 |
| **개발자 역할** | 변경 시마다 `updateDisplay()` 호출 | 상태만 변경하면 됨 |
| **실수 가능성** | `updateDisplay()` 누락 시 화면 안 바뀜 | 자동 반영 |

---

## 이벤트 처리

| 구분 | JavaScript | React |
|------|------------|-------|
| **등록 방식** | `addEventListener('click', handler)` | `onClick={handler}` |
| **바인딩** | HTML과 JS 분리, `id`로 연결 | JSX 내부에 인라인 |
| **이벤트 객체** | `addEventListener` 콜백에서 `event` 전달 | `onClick={(e) => ...}` 형태로 사용 |

---

## 장단점

### JavaScript

| 장점 | 단점 |
|------|------|
| 별도 빌드 없이 바로 실행 | 상태 변경 시 DOM 업데이트를 직접 처리 |
| 의존성 없음 | 규모가 커지면 DOM 조작이 복잡해짐 |
| 학습 곡선이 낮음 | `updateDisplay()` 누락 시 버그 발생 |

### React

| 장점 | 단점 |
|------|------|
| 상태 변경 시 자동 리렌더링 | npm, 빌드 도구 필요 |
| 선언적 UI (선언만 하면 됨) | 초기 설정이 필요 |
| 컴포넌트 재사용 용이 | 러닝 커브 존재 |

---

## 언제 무엇을 사용할까?

| 상황 | 추천 |
|------|------|
| 간단한 페이지, 빠른 프로토타입 | **JavaScript** |
| SPA, 복잡한 UI, 상태가 많은 앱 | **React** |
| React 학습 전 기초 이해 | **JavaScript** |
| 팀 프로젝트, 유지보수 중요 | **React** |
