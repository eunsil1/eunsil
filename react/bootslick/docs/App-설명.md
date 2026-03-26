# `bootslick` — `App.jsx` 설명

`App.jsx`는 **Bootstrap 5**로 레이아웃을 잡고, **react-slick**(Slick 슬라이더)으로 이미지 캐러셀을 만든 예제입니다. 하단에는 **Swiper** 기반 컴포넌트(`SwiperSlideComponent`)를 함께 렌더합니다.

---

## 1. 사용 라이브러리

| 패키지 | 역할 |
|--------|------|
| `react-slick` | React용 Slick 슬라이더 래퍼 |
| `slick-carousel` | Slick 본체 + **CSS** (`slick.css`, `slick-theme.css`) |
| `bootstrap` | 그리드·유틸 (`container`, `row`, `col` 등) |
| `swiper` | `SwiperSlideComponent`에서 사용 (별도 페이지) |

**React에 기본 포함되어 있지 않으므로**, 새 프로젝트에서는 아래처럼 **별도 설치**가 필요합니다. (`bootslick`의 `package.json`에는 이미 들어 있음.)

---

## 2. 패키지 설치 (Slick · Swiper)

### Slick (react-slick)

```bash
npm install react-slick slick-carousel
```

| 패키지 | 역할 |
|--------|------|
| `react-slick` | React용 Slick 래퍼 |
| `slick-carousel` | 스타일·동작에 필요한 CSS/에셋 (보통 함께 설치) |

코드에서 흔한 import:

```js
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
```

### Swiper

```bash
npm install swiper
```

```js
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/css";
// 필요한 모듈(페이지네이션, 자동재생 등)은 문서에 따라 추가 import
```

### 한 줄 요약

| 쓰려는 것 | 설치 |
|-----------|------|
| Slick 슬라이더 | `react-slick` + `slick-carousel` |
| Swiper 슬라이더 | `swiper` |

---

## 3. import 정리

```js
import Slider from "react-slick";
import "bootstrap/dist/css/bootstrap.min.css";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import "./App.css";
import SwiperSlideComponent from "./pages/SwiperSlide";
```

- **Slick CSS 2종**은 반드시 불러와야 슬라이더 모양·화살표·점(pager)이 정상 표시됩니다.
- Bootstrap은 **CSS만** 사용하는 구조(이 파일 기준 JS는 없음).

---

## 4. 이미지 데이터 `images`

```js
const images = [
  { id: 1, src: "./images/c1.jpg", alt: "Image 1" },
  // ...
];
```

- `map`으로 슬라이드 하나씩 생성합니다.
- **`src` 경로**: Vite에서는 보통 **`public/images/c1.jpg`** 를 두고 `src="/images/c1.jpg"` 로 쓰는 방식이 흔합니다. `./images/...` 는 빌드/배포 환경에 따라 깨질 수 있어, 이미지가 안 보이면 `public` 폴더와 `"/images/..."` 형태를 확인하세요.

---

## 5. 상태 `currentSlide`

```js
const [currentSlide, setCurrentSlide] = useState(0);
```

- **현재 보이는 슬라이드 인덱스**(0부터)를 저장합니다.
- `beforeChange`에서 `next`로 갱신해, 아래 문구와 맞춥니다.

```text
현재 이미지: {currentSlide + 1} / {images.length}
```

인덱스는 0부터이므로 화면에 보여 줄 때는 `+1` 합니다.

---

## 6. `settings` (Slick 옵션)

| 옵션 | 값 | 의미 |
|------|-----|------|
| `dots` | `true` | 하단 점(pager) 표시 |
| `infinite` | `true` | 끝에서 처음으로 무한 반복 |
| `speed` | `500` | 슬라이드 전환 애니메이션 시간(ms) |
| `slidesToShow` | `3` | 한 화면에 **3장** 노출 |
| `slidesToScroll` | `1` | 한 번에 **1장** 넘김 |
| `autoplay` | `true` | 자동 재생 |
| `autoplaySpeed` | `3000` | 자동 넘김 간격(3초) |
| `beforeChange` | `(current, next) => setCurrentSlide(next)` | 바뀌기 **직전** 다음 인덱스로 state 동기화 |
| `arrows` | `true` | 좌·우 화살표 |
| `fade` | `false` | 페이드 전환 끔(가로 슬라이드) |
| `responsive` | 배열 | 화면 너비에 따라 `slidesToShow` 조정 |

### `responsive` 동작

| `breakpoint` | 의미(대략) | 설정 |
|----------------|------------|------|
| `992` | 992px **미만** | `slidesToShow: 2` |
| `768` | 768px **미만** | `slidesToShow: 1` |

즉 넓은 화면 3장 → 중간 2장 → 모바일 1장입니다.

---

## 7. JSX 구조

```text
<div className="container mt-5">
  <div className="row justify-content-center">
    <div className="col-md-10">
      <h2>이미지 슬라이더</h2>
      <div className="slider-wrapper">
        <Slider {...settings}>
          {images.map(...)}  // 각 슬라이드: img + 캡션
        </Slider>
      </div>
      <p>현재 이미지: {currentSlide + 1} / {images.length}</p>
    </div>
  </div>
</div>
<SwiperSlideComponent />
```

- **`{...settings}`** — `settings` 객체를 Slick에 그대로 펼쳐 넘깁니다.
- **`img`**: `img-fluid`, `w-100` — Bootstrap 반응형 이미지 + 가로 100%.
- **`objectFit: "cover"`**, 고정 `height` — 높이 맞추고 비율은 잘라 맞춤.

---

## 8. `SwiperSlideComponent`

같은 파일 하단에서 **별도 슬라이더(Swiper)** 예제를 렌더합니다. Slick과는 **같은 페이지 안의 다른 라이브러리**입니다.

---

## 9. 관련 파일

| 파일 | 역할 |
|------|------|
| `src/App.jsx` | Slick 슬라이더 + 레이아웃 |
| `src/App.css` | 슬라이더 래퍼·커스텀 스타일 |
| `src/pages/SwiperSlide.jsx` | Swiper 예제 |

---

## 10. 참고 링크

- [react-slick](https://github.com/akiran/react-slick)
- [Slick 옵션](https://kenwheeler.github.io/slick/)
- [Bootstrap 5 — Grid](https://getbootstrap.com/docs/5.3/layout/grid/)
