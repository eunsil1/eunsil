# Swiper 완전 정복

## 📋 목차
1. [Swiper란?](#swiper란)
2. [설치 방법](#설치-방법)
3. [기본 사용법](#기본-사용법)
4. [주요 옵션](#주요-옵션)
5. [이벤트](#이벤트)
6. [메서드](#메서드)
7. [실전 예제](#실전-예제)
8. [문제 해결](#문제-해결)

---

## Swiper란?

**Swiper**는 모던하고 강력한 터치 슬라이더 라이브러리입니다. jQuery 없이 순수 JavaScript로 작동합니다.

### 특징
- ✅ jQuery 불필요 (순수 JavaScript)
- ✅ 터치 스와이프 지원 (모바일)
- ✅ 마우스 드래그 지원 (데스크톱)
- ✅ 다양한 전환 효과 (slide, fade, cube, coverflow, flip 등)
- ✅ 무한 루프
- ✅ 자동 재생
- ✅ 반응형 디자인
- ✅ 풍부한 옵션과 이벤트
- ✅ 모던 프레임워크 지원 (React, Vue, Angular 등)

### 단점
- ❌ 파일 크기가 큼 (~40KB)
- ❌ 학습 곡선이 있음

---

## 설치 방법

### 1. CDN 사용 (가장 간단)

```html
<!-- CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />

<!-- JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
```

### 2. npm 설치

```bash
npm install swiper
```

```javascript
// CSS import
import 'swiper/css';

// JavaScript import
import Swiper from 'swiper';
import { Navigation, Pagination } from 'swiper/modules';
```

### 3. 파일 다운로드

[공식 사이트](https://swiperjs.com/)에서 다운로드

---

## 기본 사용법

### HTML 구조

```html
<div class="swiper">
  <div class="swiper-wrapper">
    <div class="swiper-slide">슬라이드 1</div>
    <div class="swiper-slide">슬라이드 2</div>
    <div class="swiper-slide">슬라이드 3</div>
    <div class="swiper-slide">슬라이드 4</div>
  </div>
</div>
```

### JavaScript 초기화

```javascript
const swiper = new Swiper('.swiper', {
  // 옵션 설정
  slidesPerView: 1,
  spaceBetween: 30,
  loop: true
});
```

### CSS (선택사항)

```css
.swiper {
  width: 100%;
  height: 300px;
}

.swiper-slide {
  text-align: center;
  font-size: 18px;
  background: #fff;
  display: flex;
  justify-content: center;
  align-items: center;
}
```

---

## 주요 옵션

### 기본 옵션 예제

```javascript
const swiper = new Swiper('.swiper', {
  // 슬라이드 표시 개수
  slidesPerView: 1,
  spaceBetween: 30,
  
  // 무한 루프
  loop: true,
  
  // 자동 재생
  autoplay: {
    delay: 3000,
    disableOnInteraction: false,
    pauseOnMouseEnter: true
  },
  
  // 네비게이션 화살표
  navigation: {
    nextEl: '.swiper-button-next',
    prevEl: '.swiper-button-prev',
  },
  
  // 페이지네이션 (점)
  pagination: {
    el: '.swiper-pagination',
    clickable: true,
  },
  
  // 반응형 설정
  breakpoints: {
    640: {
      slidesPerView: 2,
      spaceBetween: 20,
    },
    768: {
      slidesPerView: 3,
      spaceBetween: 30,
    },
    1024: {
      slidesPerView: 4,
      spaceBetween: 40,
    },
  }
});
```

### 전체 옵션 목록

| 옵션 | 타입 | 기본값 | 설명 |
|------|------|--------|------|
| `slidesPerView` | number/string | 1 | 한 번에 표시할 슬라이드 개수 ('auto' 가능) |
| `slidesPerGroup` | number | 1 | 한 번에 스크롤할 슬라이드 개수 |
| `spaceBetween` | number | 0 | 슬라이드 간 간격 (px) |
| `loop` | boolean | false | 무한 루프 |
| `speed` | number | 300 | 애니메이션 속도 (ms) |
| `effect` | string | 'slide' | 전환 효과 ('slide', 'fade', 'cube', 'coverflow', 'flip', 'creative', 'cards') |
| `direction` | string | 'horizontal' | 슬라이더 방향 ('horizontal', 'vertical') |
| `autoplay` | object/boolean | false | 자동 재생 설정 |
| `navigation` | object | false | 네비게이션 화살표 설정 |
| `pagination` | object | false | 페이지네이션 설정 |
| `scrollbar` | object | false | 스크롤바 설정 |
| `grabCursor` | boolean | false | 드래그 커서 표시 |
| `centeredSlides` | boolean | false | 중앙 정렬 모드 |
| `freeMode` | boolean | false | 자유 모드 (스냅 없음) |
| `mousewheel` | object/boolean | false | 마우스 휠 제어 |
| `keyboard` | object/boolean | false | 키보드 제어 |
| `touchRatio` | number | 1 | 터치 비율 |
| `touchAngle` | number | 45 | 터치 각도 |
| `threshold` | number | 5 | 스와이프 임계값 |
| `resistance` | boolean | true | 끝에서의 저항 |
| `resistanceRatio` | number | 0.85 | 저항 비율 |
| `watchOverflow` | boolean | false | 슬라이드가 부족할 때 비활성화 |
| `allowTouchMove` | boolean | true | 터치 이동 허용 |
| `simulateTouch` | boolean | true | 데스크톱에서 터치 시뮬레이션 |

### 자동 재생 옵션

```javascript
autoplay: {
  delay: 3000,                    // 재생 간격 (ms)
  stopOnLastSlide: false,        // 마지막 슬라이드에서 정지
  disableOnInteraction: false,    // 상호작용 후 비활성화
  pauseOnMouseEnter: true,        // 마우스 호버 시 일시정지
  reverseDirection: false,        // 역방향 재생
  waitForTransition: true        // 전환 완료 대기
}
```

### 네비게이션 옵션

```javascript
navigation: {
  nextEl: '.swiper-button-next',  // 다음 버튼 선택자
  prevEl: '.swiper-button-prev',  // 이전 버튼 선택자
  hideOnClick: false,            // 클릭 시 숨김
  disabledClass: 'swiper-button-disabled', // 비활성화 클래스
  hiddenClass: 'swiper-button-hidden'      // 숨김 클래스
}
```

### 페이지네이션 옵션

```javascript
pagination: {
  el: '.swiper-pagination',       // 페이지네이션 요소
  type: 'bullets',                // 타입 ('bullets', 'fraction', 'progressbar')
  clickable: true,                // 클릭 가능
  dynamicBullets: false,          // 동적 불릿
  dynamicMainBullets: 1,          // 동적 메인 불릿 개수
  renderBullet: null,             // 커스텀 렌더링 함수
  renderFraction: null,           // 커스텀 분수 렌더링
  renderProgressbar: null         // 커스텀 프로그레스바 렌더링
}
```

---

## 이벤트

### 이벤트 리스너 등록

```javascript
const swiper = new Swiper('.swiper', {
  on: {
    init: function () {
      console.log('슬라이더 초기화 완료');
    },
    slideChange: function () {
      console.log('슬라이드 변경:', this.activeIndex);
    }
  }
});

// 또는 외부에서 이벤트 리스너 추가
swiper.on('slideChange', function () {
  console.log('슬라이드 변경:', this.activeIndex);
});
```

### 주요 이벤트

| 이벤트 | 설명 | 매개변수 |
|--------|------|----------|
| `init` | 슬라이더 초기화 완료 | swiper |
| `beforeInit` | 슬라이더 초기화 전 | swiper |
| `slideChange` | 슬라이드 변경 후 | swiper |
| `slideChangeTransitionStart` | 슬라이드 전환 시작 | swiper |
| `slideChangeTransitionEnd` | 슬라이드 전환 종료 | swiper |
| `slideNextTransitionStart` | 다음 슬라이드 전환 시작 | swiper |
| `slidePrevTransitionStart` | 이전 슬라이드 전환 시작 | swiper |
| `touchStart` | 터치 시작 | swiper, event |
| `touchMove` | 터치 이동 중 | swiper, event |
| `touchEnd` | 터치 종료 | swiper, event |
| `reachBeginning` | 첫 번째 슬라이드 도달 | swiper |
| `reachEnd` | 마지막 슬라이드 도달 | swiper |
| `autoplayStart` | 자동 재생 시작 | swiper |
| `autoplayStop` | 자동 재생 정지 | swiper |
| `autoplayPause` | 자동 재생 일시정지 | swiper |
| `autoplayResume` | 자동 재생 재개 | swiper |
| `destroy` | 슬라이더 제거 | swiper |
| `resize` | 리사이즈 | swiper |

### 이벤트 예제

```javascript
const swiper = new Swiper('.swiper', {
  on: {
    init: function() {
      console.log('슬라이더가 초기화되었습니다.');
    },
    slideChange: function() {
      console.log('현재 슬라이드:', this.activeIndex);
    },
    reachEnd: function() {
      console.log('마지막 슬라이드에 도달했습니다.');
    },
    touchStart: function(swiper, event) {
      console.log('터치 시작');
    },
    touchEnd: function(swiper, event) {
      console.log('터치 종료');
    }
  }
});
```

---

## 메서드

### 슬라이더 제어

```javascript
// 다음 슬라이드로 이동
swiper.slideNext();

// 이전 슬라이드로 이동
swiper.slidePrev();

// 특정 슬라이드로 이동
swiper.slideTo(2); // 인덱스 2로 이동
swiper.slideTo(2, 500); // 500ms 동안 애니메이션

// 자동 재생 시작
swiper.autoplay.start();

// 자동 재생 정지
swiper.autoplay.stop();

// 자동 재생 일시정지
swiper.autoplay.pause();

// 자동 재생 재개
swiper.autoplay.resume();

// 슬라이더 업데이트
swiper.update();

// 슬라이더 제거
swiper.destroy();
```

### 메서드 목록

| 메서드 | 설명 | 예제 |
|--------|------|------|
| `slideNext()` | 다음 슬라이드 | `swiper.slideNext()` |
| `slidePrev()` | 이전 슬라이드 | `swiper.slidePrev()` |
| `slideTo(index, speed)` | 특정 슬라이드로 이동 | `swiper.slideTo(2, 500)` |
| `slideToLoop(index)` | 루프 모드에서 특정 슬라이드로 이동 | `swiper.slideToLoop(2)` |
| `update()` | 슬라이더 업데이트 | `swiper.update()` |
| `updateSlides()` | 슬라이드 업데이트 | `swiper.updateSlides()` |
| `updateSlidesClasses()` | 슬라이드 클래스 업데이트 | `swiper.updateSlidesClasses()` |
| `updateAutoHeight()` | 자동 높이 업데이트 | `swiper.updateAutoHeight()` |
| `destroy()` | 슬라이더 제거 | `swiper.destroy()` |
| `getActiveIndex()` | 현재 활성 슬라이드 인덱스 | `swiper.getActiveIndex()` |
| `getPreviousIndex()` | 이전 슬라이드 인덱스 | `swiper.getPreviousIndex()` |
| `getNextIndex()` | 다음 슬라이드 인덱스 | `swiper.getNextIndex()` |
| `isBeginning` | 첫 번째 슬라이드 여부 | `swiper.isBeginning` |
| `isEnd` | 마지막 슬라이드 여부 | `swiper.isEnd` |

---

## 실전 예제

### 예제 1: 기본 슬라이더

```html
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
    <style>
        .swiper {
            width: 100%;
            height: 300px;
        }
        .swiper-slide {
            text-align: center;
            font-size: 18px;
            background: #fff;
            display: flex;
            justify-content: center;
            align-items: center;
        }
    </style>
</head>
<body>
    <div class="swiper">
        <div class="swiper-wrapper">
            <div class="swiper-slide">Slide 1</div>
            <div class="swiper-slide">Slide 2</div>
            <div class="swiper-slide">Slide 3</div>
        </div>
        <div class="swiper-pagination"></div>
        <div class="swiper-button-next"></div>
        <div class="swiper-button-prev"></div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    <script>
        const swiper = new Swiper('.swiper', {
            autoplay: true,
            pagination: {
                el: '.swiper-pagination',
                clickable: true,
            },
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
        });
    </script>
</body>
</html>
```

### 예제 2: 반응형 슬라이더

```javascript
const swiper = new Swiper('.swiper', {
    slidesPerView: 1,
    spaceBetween: 30,
    breakpoints: {
        640: {
            slidesPerView: 2,
            spaceBetween: 20,
        },
        768: {
            slidesPerView: 3,
            spaceBetween: 30,
        },
        1024: {
            slidesPerView: 4,
            spaceBetween: 40,
        },
    }
});
```

### 예제 3: 페이드 효과

```javascript
const swiper = new Swiper('.swiper', {
    effect: 'fade',
    fadeEffect: {
        crossFade: true
    },
    autoplay: {
        delay: 3000,
    },
    pagination: {
        el: '.swiper-pagination',
        clickable: true,
    },
});
```

### 예제 4: 큐브 효과

```javascript
const swiper = new Swiper('.swiper', {
    effect: 'cube',
    cubeEffect: {
        shadow: true,
        slideShadows: true,
        shadowOffset: 20,
        shadowScale: 0.94,
    },
    pagination: {
        el: '.swiper-pagination',
    },
});
```

### 예제 5: 커스텀 네비게이션

```html
<div class="swiper">
    <div class="swiper-wrapper">
        <div class="swiper-slide">Slide 1</div>
        <div class="swiper-slide">Slide 2</div>
        <div class="swiper-slide">Slide 3</div>
    </div>
    <div class="custom-nav">
        <button class="custom-prev">이전</button>
        <span class="slide-counter">1 / 3</span>
        <button class="custom-next">다음</button>
    </div>
</div>
```

```javascript
const swiper = new Swiper('.swiper', {
    on: {
        init: function() {
            updateCounter();
        },
        slideChange: function() {
            updateCounter();
        }
    }
});

function updateCounter() {
    const current = swiper.activeIndex + 1;
    const total = swiper.slides.length;
    document.querySelector('.slide-counter').textContent = `${current} / ${total}`;
}

document.querySelector('.custom-prev').addEventListener('click', () => {
    swiper.slidePrev();
});

document.querySelector('.custom-next').addEventListener('click', () => {
    swiper.slideNext();
});
```

### 예제 6: 세로 슬라이더

```javascript
const swiper = new Swiper('.swiper', {
    direction: 'vertical',
    slidesPerView: 1,
    spaceBetween: 30,
    mousewheel: true,
    pagination: {
        el: '.swiper-pagination',
        clickable: true,
    },
});
```

### 예제 7: 썸네일 갤러리

```html
<div class="swiper main-swiper">
    <div class="swiper-wrapper">
        <div class="swiper-slide"><img src="img1.jpg"></div>
        <div class="swiper-slide"><img src="img2.jpg"></div>
        <div class="swiper-slide"><img src="img3.jpg"></div>
    </div>
</div>

<div class="swiper thumb-swiper">
    <div class="swiper-wrapper">
        <div class="swiper-slide"><img src="img1-thumb.jpg"></div>
        <div class="swiper-slide"><img src="img2-thumb.jpg"></div>
        <div class="swiper-slide"><img src="img3-thumb.jpg"></div>
    </div>
</div>
```

```javascript
const mainSwiper = new Swiper('.main-swiper', {
    spaceBetween: 10,
});

const thumbSwiper = new Swiper('.thumb-swiper', {
    spaceBetween: 10,
    slidesPerView: 4,
    freeMode: true,
    watchSlidesProgress: true,
});

mainSwiper.controller.control = thumbSwiper;
thumbSwiper.controller.control = mainSwiper;
```

---

## 문제 해결

### 문제 1: 슬라이더가 초기화되지 않음

**원인**: Swiper 스크립트가 로드되기 전에 실행

**해결책**:
```javascript
document.addEventListener('DOMContentLoaded', function(){
    const swiper = new Swiper('.swiper', {
        // 옵션
    });
});
```

### 문제 2: 슬라이더가 제대로 표시되지 않음

**원인**: CSS 파일이 로드되지 않음 또는 높이 설정 없음

**해결책**: CSS 파일 확인 및 높이 설정
```css
.swiper {
    width: 100%;
    height: 300px; /* 높이 필수 */
}
```

### 문제 3: 반응형이 작동하지 않음

**원인**: `breakpoints` 옵션 설정 오류

**해결책**:
```javascript
breakpoints: {
    640: {
        slidesPerView: 2,
    },
    768: {
        slidesPerView: 3,
    }
}
```

### 문제 4: 동적으로 추가된 슬라이드가 표시되지 않음

**해결책**:
```javascript
// 슬라이드 추가 후
swiper.update();
swiper.updateSlides();
swiper.updateSlidesClasses();
```

### 문제 5: 루프 모드에서 슬라이드 개수가 부족함

**해결책**: 최소 슬라이드 개수 확인
```javascript
// 루프 모드는 최소 2개 이상의 슬라이드 필요
if (swiper.slides.length < 2) {
    swiper.params.loop = false;
    swiper.update();
}
```

---

## 비교표

### Swiper vs 다른 라이브러리

| 라이브러리 | jQuery | 파일 크기 | 특징 |
|-----------|--------|----------|------|
| **Swiper** | ❌ 불필요 | ~40KB | 모던, 강력한 기능, 다양한 효과 |
| **Slick Slider** | ✅ 필요 | ~50KB | 가장 인기, 풍부한 옵션 |
| **bxSlider** | ✅ 필요 | ~15KB | 가볍고 간단 |
| **Glide.js** | ❌ 불필요 | ~20KB | 가볍고 빠름 |
| **Splide.js** | ❌ 불필요 | ~15KB | 접근성 우수 |

### 기능 비교

| 기능 | Swiper | Slick | bxSlider |
|------|--------|-------|----------|
| jQuery 필요 | ❌ | ✅ | ✅ |
| 터치 스와이프 | ✅ | ✅ | ✅ |
| 마우스 드래그 | ✅ | ❌ | ❌ |
| 다양한 효과 | ✅ | ⚠️ | ❌ |
| 무한 루프 | ✅ | ✅ | ✅ |
| 자동 재생 | ✅ | ✅ | ✅ |
| 반응형 | ✅ | ✅ | ✅ |
| 세로 슬라이더 | ✅ | ✅ | ✅ |
| 썸네일 갤러리 | ✅ | ⚠️ | ❌ |

---

## 마무리

**Swiper는 모던 웹 개발에 최적화된 강력한 슬라이더 라이브러리입니다.**

**선택 가이드:**
- ✅ **모던 프로젝트**: Swiper 권장
- ✅ **jQuery 프로젝트**: Slick Slider 또는 bxSlider
- ✅ **최소한의 의존성**: Glide.js 또는 Splide.js
- ✅ **고급 효과 필요**: Swiper 권장

**참고 자료:**
- [Swiper 공식 문서](https://swiperjs.com/)
- [Swiper API 문서](https://swiperjs.com/swiper-api)
- [Swiper GitHub](https://github.com/nolimits4web/swiper)
- [예제 코드](./example/)
