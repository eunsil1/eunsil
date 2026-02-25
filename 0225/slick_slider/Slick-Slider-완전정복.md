# Slick Slider 완전 정복

## 📋 목차
1. [Slick Slider란?](#slick-slider란)
2. [설치 방법](#설치-방법)
3. [기본 사용법](#기본-사용법)
4. [주요 옵션](#주요-옵션)
5. [이벤트](#이벤트)
6. [메서드](#메서드)
7. [실전 예제](#실전-예제)
8. [Vanilla JS 대체 방안](#vanilla-js-대체-방안)
9. [문제 해결](#문제-해결)

---

## Slick Slider란?

**Slick Slider**는 jQuery 기반의 반응형 캐러셀(슬라이더) 플러그인입니다.

### 특징
- ✅ 반응형 디자인
- ✅ 터치 스와이프 지원
- ✅ 여러 슬라이드 동시 표시
- ✅ 무한 루프
- ✅ 자동 재생
- ✅ 다양한 애니메이션 효과
- ✅ 풍부한 옵션과 이벤트

### 단점
- ❌ jQuery 의존성 (jQuery 1.7 이상 필요)
- ❌ 파일 크기가 큼 (~50KB, jQuery 포함 시 ~100KB)
- ❌ 모던 프레임워크와의 호환성 문제

---

## 설치 방법

### 1. CDN 사용 (가장 간단)

```html
<!-- CSS -->
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css"/>

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- Slick Slider -->
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
```

### 2. npm 설치

```bash
npm install slick-carousel
```

```javascript
// CSS import
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

// JavaScript import
import $ from "jquery";
import "slick-carousel";
```

### 3. 파일 다운로드

[공식 GitHub](https://github.com/kenwheeler/slick)에서 다운로드

---

## 기본 사용법

### HTML 구조

```html
<div class="slider">
    <div>슬라이드 1</div>
    <div>슬라이드 2</div>
    <div>슬라이드 3</div>
    <div>슬라이드 4</div>
</div>
```

### JavaScript 초기화

```javascript
$(document).ready(function(){
    $('.slider').slick();
});
```

또는

```javascript
$(function(){
    $('.slider').slick();
});
```

### CSS (선택사항)

```css
.slider {
    width: 100%;
    margin: 0 auto;
}

.slider .slick-slide {
    padding: 0 10px;
}
```

---

## 주요 옵션

### 기본 옵션 예제

```javascript
$('.slider').slick({
    // 슬라이드 표시 개수
    slidesToShow: 3,
    slidesToScroll: 1,
    
    // 무한 루프
    infinite: true,
    
    // 자동 재생
    autoplay: true,
    autoplaySpeed: 3000,
    
    // 화살표 버튼
    arrows: true,
    prevArrow: '<button type="button" class="slick-prev">Previous</button>',
    nextArrow: '<button type="button" class="slick-next">Next</button>',
    
    // 점(dots) 네비게이션
    dots: true,
    
    // 반응형 설정
    responsive: [
        {
            breakpoint: 1024,
            settings: {
                slidesToShow: 2,
                slidesToScroll: 2
            }
        },
        {
            breakpoint: 768,
            settings: {
                slidesToShow: 1,
                slidesToScroll: 1
            }
        }
    ]
});
```

### 전체 옵션 목록

| 옵션 | 타입 | 기본값 | 설명 |
|------|------|--------|------|
| `slidesToShow` | number | 1 | 한 번에 표시할 슬라이드 개수 |
| `slidesToScroll` | number | 1 | 한 번에 스크롤할 슬라이드 개수 |
| `infinite` | boolean | true | 무한 루프 |
| `autoplay` | boolean | false | 자동 재생 |
| `autoplaySpeed` | number | 3000 | 자동 재생 속도 (ms) |
| `speed` | number | 300 | 애니메이션 속도 (ms) |
| `arrows` | boolean | true | 화살표 버튼 표시 |
| `dots` | boolean | false | 점 네비게이션 표시 |
| `fade` | boolean | false | 페이드 효과 |
| `vertical` | boolean | false | 세로 슬라이더 |
| `centerMode` | boolean | false | 중앙 정렬 모드 |
| `centerPadding` | string | '50px' | 중앙 정렬 패딩 |
| `swipe` | boolean | true | 스와이프 제스처 활성화 |
| `touchMove` | boolean | true | 터치 이동 활성화 |
| `pauseOnHover` | boolean | true | 호버 시 일시 정지 |
| `pauseOnFocus` | boolean | true | 포커스 시 일시 정지 |
| `pauseOnDotsHover` | boolean | false | 점 호버 시 일시 정지 |
| `adaptiveHeight` | boolean | false | 슬라이드 높이 자동 조정 |
| `variableWidth` | boolean | false | 가변 너비 슬라이드 |
| `lazyLoad` | string | 'ondemand' | 지연 로딩 ('ondemand', 'progressive') |
| `cssEase` | string | 'ease' | CSS transition easing |
| `easing` | string | 'linear' | jQuery easing (fade 사용 시) |
| `edgeFriction` | number | 0.15 | 끝에서의 마찰력 |
| `waitForAnimate` | boolean | true | 애니메이션 완료 대기 |
| `useTransform` | boolean | true | CSS transform 사용 |
| `rtl` | boolean | false | 오른쪽에서 왼쪽으로 |

---

## 이벤트

### 이벤트 리스너 등록

```javascript
$('.slider').on('beforeChange', function(event, slick, currentSlide, nextSlide){
    console.log('슬라이드 변경 전:', currentSlide, '→', nextSlide);
});

$('.slider').on('afterChange', function(event, slick, currentSlide){
    console.log('슬라이드 변경 후:', currentSlide);
});

$('.slider').on('init', function(event, slick){
    console.log('슬라이더 초기화 완료');
});
```

### 주요 이벤트

| 이벤트 | 설명 | 매개변수 |
|--------|------|----------|
| `init` | 슬라이더 초기화 완료 | event, slick |
| `beforeChange` | 슬라이드 변경 전 | event, slick, currentSlide, nextSlide |
| `afterChange` | 슬라이드 변경 후 | event, slick, currentSlide |
| `breakpoint` | 반응형 브레이크포인트 변경 | event, slick, breakpoint |
| `destroy` | 슬라이더 제거 | event, slick |
| `edge` | 첫 번째 또는 마지막 슬라이드 도달 | event, slick, direction |
| `reInit` | 슬라이더 재초기화 | event, slick |
| `setPosition` | 위치 설정 | event, slick |
| `swipe` | 스와이프 제스처 | event, slick, direction |
| `lazyLoaded` | 지연 로딩 완료 | event, slick, image, imageSource |

### 이벤트 예제

```javascript
// 슬라이더 초기화 후
$('.slider').on('init', function(event, slick){
    console.log('슬라이더가 초기화되었습니다.');
});

// 슬라이드 변경 전
$('.slider').on('beforeChange', function(event, slick, currentSlide, nextSlide){
    // 현재 슬라이드 숨기기
    $('.slider .slick-slide').eq(currentSlide).fadeOut();
});

// 슬라이드 변경 후
$('.slider').on('afterChange', function(event, slick, currentSlide){
    // 새 슬라이드 표시
    $('.slider .slick-slide').eq(currentSlide).fadeIn();
});

// 스와이프 감지
$('.slider').on('swipe', function(event, slick, direction){
    console.log('스와이프 방향:', direction); // 'left' 또는 'right'
});
```

---

## 메서드

### 슬라이더 제어

```javascript
// 다음 슬라이드로 이동
$('.slider').slick('slickNext');

// 이전 슬라이드로 이동
$('.slider').slick('slickPrev');

// 특정 슬라이드로 이동
$('.slider').slick('slickGoTo', 2);

// 자동 재생 시작
$('.slider').slick('slickPlay');

// 자동 재생 정지
$('.slider').slick('slickPause');

// 슬라이더 제거
$('.slider').slick('unslick');

// 현재 슬라이드 인덱스 가져오기
var currentSlide = $('.slider').slick('slickCurrentSlide');
```

### 메서드 목록

| 메서드 | 설명 | 예제 |
|--------|------|------|
| `slick()` | 슬라이더 초기화 | `$('.slider').slick({...})` |
| `slick('slickNext')` | 다음 슬라이드 | `$('.slider').slick('slickNext')` |
| `slick('slickPrev')` | 이전 슬라이드 | `$('.slider').slick('slickPrev')` |
| `slick('slickGoTo', index)` | 특정 슬라이드로 이동 | `$('.slider').slick('slickGoTo', 2)` |
| `slick('slickPlay')` | 자동 재생 시작 | `$('.slider').slick('slickPlay')` |
| `slick('slickPause')` | 자동 재생 정지 | `$('.slider').slick('slickPause')` |
| `slick('unslick')` | 슬라이더 제거 | `$('.slider').slick('unslick')` |
| `slick('slickCurrentSlide')` | 현재 슬라이드 인덱스 | `$('.slider').slick('slickCurrentSlide')` |
| `slick('slickAdd', element)` | 슬라이드 추가 | `$('.slider').slick('slickAdd', '<div>New</div>')` |
| `slick('slickRemove', index)` | 슬라이드 제거 | `$('.slider').slick('slickRemove', 0)` |
| `slick('refresh')` | 슬라이더 새로고침 | `$('.slider').slick('refresh')` |
| `slick('setPosition')` | 위치 재설정 | `$('.slider').slick('setPosition')` |
| `slick('slickFilter', filter)` | 슬라이드 필터링 | `$('.slider').slick('slickFilter', ':even')` |
| `slick('slickUnfilter')` | 필터 제거 | `$('.slider').slick('slickUnfilter')` |

---

## 실전 예제

### 예제 1: 기본 슬라이더

```html
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="slick/slick.css">
    <link rel="stylesheet" href="slick/slick-theme.css">
</head>
<body>
    <div class="slider">
        <div><img src="slide1.jpg"></div>
        <div><img src="slide2.jpg"></div>
        <div><img src="slide3.jpg"></div>
    </div>
    
    <script src="jquery.min.js"></script>
    <script src="slick/slick.min.js"></script>
    <script>
        $(document).ready(function(){
            $('.slider').slick({
                autoplay: true,
                dots: true
            });
        });
    </script>
</body>
</html>
```

### 예제 2: 반응형 슬라이더

```javascript
$('.slider').slick({
    slidesToShow: 3,
    slidesToScroll: 1,
    infinite: true,
    responsive: [
        {
            breakpoint: 1024,
            settings: {
                slidesToShow: 2,
                slidesToScroll: 2
            }
        },
        {
            breakpoint: 768,
            settings: {
                slidesToShow: 1,
                slidesToScroll: 1,
                arrows: false,
                dots: true
            }
        }
    ]
});
```

### 예제 3: 페이드 효과

```javascript
$('.slider').slick({
    fade: true,
    cssEase: 'linear',
    autoplay: true,
    autoplaySpeed: 2000,
    dots: true,
    arrows: true
});
```

### 예제 4: 중앙 정렬 모드

```javascript
$('.slider').slick({
    centerMode: true,
    centerPadding: '60px',
    slidesToShow: 3,
    responsive: [
        {
            breakpoint: 768,
            settings: {
                centerMode: true,
                centerPadding: '40px',
                slidesToShow: 1
            }
        }
    ]
});
```

### 예제 5: 지연 로딩

```javascript
$('.slider').slick({
    lazyLoad: 'ondemand', // 또는 'progressive'
    slidesToShow: 3,
    slidesToScroll: 1
});
```

```html
<div class="slider">
    <div><img data-lazy="slide1.jpg"></div>
    <div><img data-lazy="slide2.jpg"></div>
    <div><img data-lazy="slide3.jpg"></div>
</div>
```

---

## Vanilla JS 대체 방안

jQuery를 사용하지 않고 Slick Slider와 유사한 기능을 구현하려면:

### 1. 순수 JavaScript 구현
- `../vanilla_slider/slick-like/` 폴더 참고
- 터치 스와이프, 여러 슬라이드 표시, 반응형 지원

### 2. 다른 라이브러리 사용

#### Swiper.js
```bash
npm install swiper
```

```javascript
import Swiper from 'swiper';
import 'swiper/css';

const swiper = new Swiper('.swiper', {
    slidesPerView: 3,
    spaceBetween: 30
});
```

#### Glide.js
```bash
npm install @glidejs/glide
```

```javascript
import Glide from '@glidejs/glide';
import '@glidejs/glide/dist/css/glide.core.min.css';

new Glide('.glide', {
    perView: 3
}).mount();
```

#### Splide.js
```bash
npm install @splidejs/splide
```

```javascript
import Splide from '@splidejs/splide';
import '@splidejs/splide/dist/css/themes/splide-default.min.css';

new Splide('.splide', {
    perPage: 3
}).mount();
```

---

## 비교표

### Slick Slider vs Vanilla JS 구현

| 기능 | Slick Slider | Vanilla JS (slick-like) |
|------|-------------|------------------------|
| **jQuery 필요** | ✅ 필요 | ❌ 불필요 |
| **파일 크기** | ~50KB (+jQuery) | ~5KB |
| **여러 슬라이드 표시** | ✅ | ✅ |
| **터치 스와이프** | ✅ | ✅ |
| **자동 재생** | ✅ | ❌ (추가 가능) |
| **무한 루프** | ✅ | ✅ |
| **반응형** | ✅ | ✅ |
| **페이드 효과** | ✅ | ❌ |
| **세로 슬라이더** | ✅ | ❌ |
| **지연 로딩** | ✅ | ❌ |
| **커스터마이징** | ⭐⭐⭐ | ⭐⭐⭐⭐⭐ |
| **학습 곡선** | 낮음 | 중간 |

### 라이브러리 비교

| 라이브러리 | jQuery | 파일 크기 | 특징 |
|-----------|--------|----------|------|
| **Slick Slider** | ✅ 필요 | ~50KB | 가장 인기, 풍부한 옵션 |
| **Swiper.js** | ❌ 불필요 | ~40KB | 모던, 강력한 기능 |
| **Glide.js** | ❌ 불필요 | ~20KB | 가볍고 빠름 |
| **Splide.js** | ❌ 불필요 | ~15KB | 접근성 우수 |
| **Vanilla JS** | ❌ 불필요 | ~5KB | 완전한 제어 |

---

## 문제 해결

### 문제 1: 슬라이더가 초기화되지 않음

**원인**: jQuery가 로드되기 전에 스크립트 실행

**해결책**:
```javascript
$(document).ready(function(){
    $('.slider').slick();
});
```

### 문제 2: 슬라이더가 제대로 표시되지 않음

**원인**: CSS 파일이 로드되지 않음

**해결책**: `slick.css`와 `slick-theme.css` 파일 확인

### 문제 3: 반응형이 작동하지 않음

**원인**: `responsive` 옵션 설정 오류

**해결책**:
```javascript
responsive: [
    {
        breakpoint: 768,
        settings: {
            slidesToShow: 1,
            slidesToScroll: 1
        }
    }
]
```

### 문제 4: 동적으로 추가된 슬라이드가 표시되지 않음

**해결책**:
```javascript
// 슬라이드 추가 후
$('.slider').slick('slickAdd', '<div>New Slide</div>');
$('.slider').slick('setPosition');
```

### 문제 5: 슬라이더가 중복으로 초기화됨

**해결책**:
```javascript
if ($('.slider').hasClass('slick-initialized')) {
    $('.slider').slick('unslick');
}
$('.slider').slick({...});
```

---

## 마무리

**Slick Slider는 강력하지만 jQuery 의존성이 단점입니다.**

**선택 가이드:**
- ✅ **jQuery 프로젝트**: Slick Slider 사용
- ✅ **모던 프로젝트**: Swiper.js 또는 Glide.js 권장
- ✅ **최소한의 의존성**: Vanilla JS 구현 (`../vanilla_slider/slick-like/` 참고)
- ✅ **접근성 중요**: Splide.js 권장

**참고 자료:**
- [Slick Slider 공식 문서](https://kenwheeler.github.io/slick/)
- [Slick Slider GitHub](https://github.com/kenwheeler/slick)
- [Vanilla JS 구현 예제](../vanilla_slider/slick-like/)
- [예제 코드](./example/)
