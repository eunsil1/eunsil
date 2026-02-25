# bxSlider 완전 정복

## 📋 목차
1. [bxSlider란?](#bxslider란)
2. [설치 방법](#설치-방법)
3. [기본 사용법](#기본-사용법)
4. [주요 옵션](#주요-옵션)
5. [이벤트](#이벤트)
6. [메서드](#메서드)
7. [실전 예제](#실전-예제)
8. [Vanilla JS 대체 방안](#vanilla-js-대체-방안)
9. [문제 해결](#문제-해결)
10. [커스텀 컨트롤 구현](#커스텀-컨트롤-구현)
10. [커스텀 컨트롤 구현](#커스텀-컨트롤-구현)

---

## bxSlider란?

**bxSlider**는 jQuery 기반의 반응형 콘텐츠 슬라이더 플러그인입니다.

### 특징
- ✅ 반응형 디자인
- ✅ 터치 스와이프 지원
- ✅ 다양한 전환 효과 (fade, slide, horizontal, vertical)
- ✅ 자동 재생
- ✅ 무한 루프
- ✅ 가볍고 빠름 (~15KB)
- ✅ 접근성 지원
- ✅ 티커 모드 지원

### 단점
- ❌ jQuery 의존성 (jQuery 1.4 이상 필요)
- ❌ Slick Slider보다 옵션이 적음
- ❌ 최근 업데이트가 적음 (마지막 업데이트: 2017년)

---

## 설치 방법

### 1. CDN 사용 (가장 간단)

```html
<!-- CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.css">

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- bxSlider -->
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
```

### 2. npm 설치

```bash
npm install bxslider
```

```javascript
// CSS import
import 'bxslider/dist/jquery.bxslider.css';

// JavaScript import
import $ from 'jquery';
import 'bxslider';
```

### 3. 파일 다운로드

[공식 GitHub](https://github.com/stevenwanderski/bxslider-4)에서 다운로드

---

## 기본 사용법

### HTML 구조

**중요**: bxSlider는 `<ul>` 또는 `<div>` 요소를 사용합니다.

```html
<!-- ul 사용 (권장) -->
<ul class="bxslider">
    <li>슬라이드 1</li>
    <li>슬라이드 2</li>
    <li>슬라이드 3</li>
    <li>슬라이드 4</li>
</ul>

<!-- div 사용 -->
<div class="bxslider">
    <div>슬라이드 1</div>
    <div>슬라이드 2</div>
    <div>슬라이드 3</div>
    <div>슬라이드 4</div>
</div>
```

### JavaScript 초기화

```javascript
$(document).ready(function(){
    $('.bxslider').bxSlider();
});
```

또는

```javascript
$(function(){
    $('.bxslider').bxSlider();
});
```

### CSS (선택사항)

```css
.bxslider {
    margin: 0;
    padding: 0;
    list-style: none;
}

.bxslider li {
    margin: 0;
    padding: 0;
}
```

---

## 주요 옵션

### 기본 옵션 예제

```javascript
$('.bxslider').bxSlider({
    // 모드 설정
    mode: 'horizontal', // 'horizontal', 'vertical', 'fade'
    
    // 슬라이드 표시 개수
    slideWidth: 0, // 0이면 자동
    minSlides: 1,
    maxSlides: 1,
    
    // 자동 재생
    auto: true,
    autoHover: true,
    pause: 4000,
    
    // 네비게이션
    pager: true,
    controls: true,
    
    // 무한 루프
    infiniteLoop: true,
    
    // 속도
    speed: 500
});
```

### 전체 옵션 목록

#### 모드 및 레이아웃

| 옵션 | 타입 | 기본값 | 설명 |
|------|------|--------|------|
| `mode` | string | 'horizontal' | 슬라이드 모드 ('horizontal', 'vertical', 'fade') |
| `slideWidth` | number | 0 | 슬라이드 너비 (0이면 자동) |
| `minSlides` | number | 1 | 최소 슬라이드 개수 |
| `maxSlides` | number | 1 | 최대 슬라이드 개수 |
| `moveSlides` | number | 0 | 이동할 슬라이드 개수 (0이면 maxSlides) |
| `shuffleShortcodes` | boolean | false | 쇼트코드 셔플 |
| `adaptiveHeight` | boolean | false | 슬라이드 높이 자동 조정 |
| `adaptiveHeightSpeed` | number | 500 | 높이 조정 속도 (ms) |

#### 자동 재생

| 옵션 | 타입 | 기본값 | 설명 |
|------|------|--------|------|
| `auto` | boolean | false | 자동 재생 |
| `autoHover` | boolean | false | 호버 시 일시 정지 |
| `pause` | number | 4000 | 자동 재생 간격 (ms) |
| `autoControls` | boolean | false | 자동 재생 컨트롤 표시 |
| `autoControlsCombine` | boolean | false | 자동 재생 컨트롤 결합 |
| `autoControlsSelector` | string | null | 자동 재생 컨트롤 선택자 |

#### 네비게이션

| 옵션 | 타입 | 기본값 | 설명 |
|------|------|--------|------|
| `pager` | boolean | true | 페이지네이션 표시 |
| `pagerType` | string | 'full' | 페이지네이션 타입 ('full', 'short') |
| `pagerShortSeparator` | string | ' / ' | 짧은 페이지네이션 구분자 |
| `pagerSelector` | string | null | 페이지네이션 선택자 |
| `controls` | boolean | true | 이전/다음 버튼 표시 |
| `nextText` | string | 'Next' | 다음 버튼 텍스트 |
| `prevText` | string | 'Prev' | 이전 버튼 텍스트 |
| `nextSelector` | string | null | 다음 버튼 선택자 |
| `prevSelector` | string | null | 이전 버튼 선택자 |
| `hideControlOnEnd` | boolean | false | 끝에서 컨트롤 숨기기 |

#### 애니메이션

| 옵션 | 타입 | 기본값 | 설명 |
|------|------|--------|------|
| `speed` | number | 500 | 전환 속도 (ms) |
| `easing` | string | null | easing 함수 |
| `useCSS` | boolean | true | CSS transition 사용 |

#### 터치 및 제스처

| 옵션 | 타입 | 기본값 | 설명 |
|------|------|--------|------|
| `touchEnabled` | boolean | true | 터치 제스처 활성화 |
| `swipeThreshold` | number | 50 | 스와이프 임계값 (px) |
| `oneToOneTouch` | boolean | true | 1:1 터치 비율 |
| `preventDefaultSwipeX` | boolean | true | X축 스와이프 기본 동작 방지 |
| `preventDefaultSwipeY` | boolean | false | Y축 스와이프 기본 동작 방지 |

#### 기타

| 옵션 | 타입 | 기본값 | 설명 |
|------|------|--------|------|
| `infiniteLoop` | boolean | true | 무한 루프 |
| `startSlide` | number | 0 | 시작 슬라이드 인덱스 |
| `randomStart` | boolean | false | 랜덤 시작 |
| `ticker` | boolean | false | 티커 모드 |
| `tickerHover` | boolean | false | 티커 호버 일시 정지 |
| `tickerSpeed` | number | 5000 | 티커 속도 (ms) |
| `captions` | boolean | false | 캡션 표시 |
| `video` | boolean | false | 비디오 지원 |
| `keyboardEnabled` | boolean | false | 키보드 네비게이션 |
| `wrapperClass` | string | 'bx-wrapper' | 래퍼 클래스명 |

#### 콜백 함수

| 옵션 | 타입 | 기본값 | 설명 |
|------|------|--------|------|
| `onSliderLoad` | function | null | 슬라이더 로드 콜백 |
| `onSlideBefore` | function | null | 슬라이드 전환 전 콜백 |
| `onSlideAfter` | function | null | 슬라이드 전환 후 콜백 |
| `onSlideNext` | function | null | 다음 슬라이드 콜백 |
| `onSlidePrev` | function | null | 이전 슬라이드 콜백 |

---

## 이벤트

### 이벤트 리스너 등록

```javascript
var slider = $('.bxslider').bxSlider({
    onSliderLoad: function(currentIndex){
        console.log('슬라이더 로드:', currentIndex);
    },
    onSlideBefore: function($slideElement, oldIndex, newIndex){
        console.log('슬라이드 전환 전:', oldIndex, '→', newIndex);
    },
    onSlideAfter: function($slideElement, oldIndex){
        console.log('슬라이드 전환 후:', oldIndex);
    },
    onSlideNext: function($slideElement, oldIndex){
        console.log('다음 슬라이드:', oldIndex);
    },
    onSlidePrev: function($slideElement, oldIndex){
        console.log('이전 슬라이드:', oldIndex);
    }
});
```

### 주요 이벤트

| 이벤트 | 설명 | 매개변수 |
|--------|------|----------|
| `onSliderLoad` | 슬라이더 로드 완료 | currentIndex |
| `onSlideBefore` | 슬라이드 전환 전 | $slideElement, oldIndex, newIndex |
| `onSlideAfter` | 슬라이드 전환 후 | $slideElement, oldIndex |
| `onSlideNext` | 다음 슬라이드 | $slideElement, oldIndex |
| `onSlidePrev` | 이전 슬라이드 | $slideElement, oldIndex |

### 이벤트 예제

```javascript
// 슬라이더 초기화 후
var slider = $('.bxslider').bxSlider({
    onSliderLoad: function(currentIndex){
        console.log('슬라이더가 로드되었습니다. 현재 슬라이드:', currentIndex);
    }
});

// 슬라이드 변경 전
var slider = $('.bxslider').bxSlider({
    onSlideBefore: function($slideElement, oldIndex, newIndex){
        // 현재 슬라이드 숨기기
        $slideElement.fadeOut();
        console.log('슬라이드 변경 전:', oldIndex, '→', newIndex);
    }
});

// 슬라이드 변경 후
var slider = $('.bxslider').bxSlider({
    onSlideAfter: function($slideElement, oldIndex){
        // 새 슬라이드 표시
        $slideElement.fadeIn();
        console.log('슬라이드 변경 후:', oldIndex);
    }
});
```

---

## 메서드

### 슬라이더 제어

```javascript
var slider = $('.bxslider').bxSlider();

// 다음 슬라이드로 이동
slider.goToNextSlide();

// 이전 슬라이드로 이동
slider.goToPrevSlide();

// 특정 슬라이드로 이동
slider.goToSlide(2);

// 자동 재생 시작
slider.startAuto();

// 자동 재생 정지
slider.stopAuto();

// 슬라이더 제거
slider.destroySlider();

// 슬라이더 재생성
slider.reloadSlider();

// 현재 슬라이드 인덱스 가져오기
var currentSlide = slider.getCurrentSlide();

// 총 슬라이드 개수 가져오기
var slideCount = slider.getSlideCount();
```

### 메서드 목록

| 메서드 | 설명 | 예제 |
|--------|------|------|
| `goToNextSlide()` | 다음 슬라이드로 이동 | `slider.goToNextSlide()` |
| `goToPrevSlide()` | 이전 슬라이드로 이동 | `slider.goToPrevSlide()` |
| `goToSlide(index)` | 특정 슬라이드로 이동 | `slider.goToSlide(2)` |
| `startAuto()` | 자동 재생 시작 | `slider.startAuto()` |
| `stopAuto()` | 자동 재생 정지 | `slider.stopAuto()` |
| `destroySlider()` | 슬라이더 제거 | `slider.destroySlider()` |
| `reloadSlider(options)` | 슬라이더 재생성 | `slider.reloadSlider({auto: true})` |
| `getCurrentSlide()` | 현재 슬라이드 인덱스 | `slider.getCurrentSlide()` |
| `getSlideCount()` | 총 슬라이드 개수 | `slider.getSlideCount()` |

### ⚠️ 중요: 메서드 호출 방식

bxSlider는 **초기화 시 반환된 객체**에서 직접 메서드를 호출해야 합니다.

**올바른 방식:**
```javascript
var slider = $('.bxslider').bxSlider({
    auto: true
});

// 반환된 객체에서 직접 메서드 호출
slider.goToNextSlide();
slider.getCurrentSlide();
slider.startAuto();
```

**잘못된 방식:**
```javascript
// ❌ 이렇게 호출하면 작동하지 않음
$('.bxslider').bxSlider('goToNextSlide');
$('.bxslider').getCurrentSlide();
```

---

## 실전 예제

### 예제 1: 기본 슬라이더 (점 형태 pager)

```html
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="jquery.bxslider.css">
</head>
<body>
    <ul class="bxslider">
        <li><img src="slide1.jpg"></li>
        <li><img src="slide2.jpg"></li>
        <li><img src="slide3.jpg"></li>
    </ul>
    
    <script src="jquery.min.js"></script>
    <script src="jquery.bxslider.min.js"></script>
    <script>
        $(document).ready(function(){
            $('.bxslider').bxSlider({
                pager: true,
                pagerType: 'full', // 점 형태
                controls: true
            });
        });
    </script>
</body>
</html>
```

### 예제 1-2: 숫자 형태 pager (1 / 4)

```javascript
$('.bxslider').bxSlider({
    pager: true,
    pagerType: 'short', // 숫자 형태
    pagerShortSeparator: ' / ',
    controls: true
});
```

### 예제 2: 페이드 효과

```javascript
$('.bxslider').bxSlider({
    mode: 'fade',
    auto: true,
    pause: 3000,
    speed: 1000,
    pager: true,
    controls: true
});
```

### 예제 3: 여러 슬라이드 표시

```javascript
$('.bxslider').bxSlider({
    minSlides: 1,
    maxSlides: 3,
    slideWidth: 300,
    slideMargin: 10,
    pager: true,
    controls: true
});
```

### 예제 4: 세로 슬라이더

```javascript
$('.bxslider').bxSlider({
    mode: 'vertical',
    slideHeight: 300,
    minSlides: 2,
    maxSlides: 3,
    moveSlides: 1,
    auto: true,
    pause: 3000
});
```

### 예제 5: 티커 모드

```javascript
$('.bxslider').bxSlider({
    mode: 'horizontal',
    ticker: true,
    tickerHover: true,
    tickerSpeed: 5000,
    minSlides: 1,
    maxSlides: 1
});
```

### 예제 6: 반응형 슬라이더

```javascript
$('.bxslider').bxSlider({
    minSlides: 1,
    maxSlides: 3,
    slideWidth: 300,
    slideMargin: 10,
    responsive: true, // 반응형 자동 조정
    pager: true,
    controls: true
});
```

### 예제 7: 비디오 슬라이더

```html
<ul class="bxslider">
    <li>
        <iframe src="https://www.youtube.com/embed/VIDEO_ID"></iframe>
    </li>
    <li>
        <iframe src="https://www.youtube.com/embed/VIDEO_ID2"></iframe>
    </li>
</ul>
```

```javascript
$('.bxslider').bxSlider({
    video: true,
    useCSS: false,
    pager: true,
    controls: true
});
```

### 예제 8: 커스텀 컨트롤 (Play/Stop, Next/Prev, 슬라이드 번호)

커스텀 컨트롤을 만들어서 Play/Pause 토글, Prev/Next 버튼, 슬라이드 번호를 표시하는 예제입니다.

#### HTML 구조

```html
<div class="slider-wrapper">
    <div class="custom-controls">
        <button class="play-pause-btn" id="playPauseBtn">⏸</button>
        <button class="prev-btn-custom" id="prevBtnCustom">‹</button>
        <div class="slide-counter" id="slideCounter">1 / 5</div>
        <button class="next-btn-custom" id="nextBtnCustom">›</button>
    </div>
    <ul class="slider slider-custom-controls">
        <li>Slide 1</li>
        <li>Slide 2</li>
        <li>Slide 3</li>
        <li>Slide 4</li>
        <li>Slide 5</li>
    </ul>
</div>
```

#### CSS

```css
.slider-wrapper {
    position: relative;
}

.custom-controls {
    position: absolute;
    bottom: 20px;
    left: 50%;
    transform: translateX(-50%);
    display: flex;
    align-items: center;
    gap: 15px;
    z-index: 1000;
    background: rgba(0, 0, 0, 0.7);
    padding: 10px 20px;
    border-radius: 30px;
}

.play-pause-btn,
.prev-btn-custom,
.next-btn-custom {
    background: rgba(255, 255, 255, 0.9);
    border: none;
    width: 40px;
    height: 40px;
    border-radius: 50%;
    font-size: 18px;
    cursor: pointer;
    transition: all 0.3s ease;
}

.slide-counter {
    color: #fff;
    font-size: 16px;
    font-weight: bold;
    min-width: 60px;
    text-align: center;
}

.slider-custom-controls .bx-wrapper .bx-controls-direction {
    display: none; /* 기본 화살표 숨기기 */
}

.slider-custom-controls .bx-wrapper .bx-pager {
    display: none; /* 기본 pager 숨기기 */
}
```

#### JavaScript

```javascript
var isPlaying = true;
var playPauseBtn = $('#playPauseBtn');
var prevBtn = $('#prevBtnCustom');
var nextBtn = $('#nextBtnCustom');
var slideCounter = $('#slideCounter');
var currentSlideIndex = 0;
var totalSlides = $('.slider-custom-controls li').length;

// 슬라이드 번호 업데이트 함수
function updateSlideCounter(index) {
    if (index !== undefined) {
        currentSlideIndex = index;
    }
    slideCounter.text((currentSlideIndex + 1) + ' / ' + totalSlides);
}

// 슬라이더 초기화
var customControlSlider = $('.slider-custom-controls').bxSlider({
    auto: true,
    pause: 3000,
    pager: false,
    controls: false, // 기본 컨트롤 숨기기
    onSliderLoad: function(currentIndex) {
        currentSlideIndex = currentIndex;
        updateSlideCounter(currentIndex);
    },
    onSlideAfter: function($slideElement, oldIndex) {
        currentSlideIndex = oldIndex;
        updateSlideCounter(oldIndex);
    }
});

// 초기 슬라이드 번호 표시
updateSlideCounter(0);

// Play/Pause 버튼
playPauseBtn.on('click', function(e) {
    e.preventDefault();
    e.stopPropagation();
    
    if (isPlaying) {
        customControlSlider.stopAuto();
        playPauseBtn.text('▶');
        isPlaying = false;
    } else {
        customControlSlider.startAuto();
        playPauseBtn.text('⏸');
        isPlaying = true;
    }
    return false;
});

// Prev 버튼
prevBtn.on('click', function(e) {
    e.preventDefault();
    e.stopPropagation();
    
    customControlSlider.goToPrevSlide();
    // 수동 클릭 시 자동 재생 재시작
    if (isPlaying) {
        customControlSlider.stopAuto();
        setTimeout(function() {
            customControlSlider.startAuto();
        }, 100);
    }
    return false;
});

// Next 버튼
nextBtn.on('click', function(e) {
    e.preventDefault();
    e.stopPropagation();
    
    customControlSlider.goToNextSlide();
    // 수동 클릭 시 자동 재생 재시작
    if (isPlaying) {
        customControlSlider.stopAuto();
        setTimeout(function() {
            customControlSlider.startAuto();
        }, 100);
    }
    return false;
});
```

#### 주요 포인트

1. **메서드 호출**: 초기화 시 반환된 객체(`customControlSlider`)에서 직접 메서드 호출
2. **슬라이드 번호**: 콜백 함수(`onSliderLoad`, `onSlideAfter`)에서 인덱스를 받아 업데이트
3. **자동 재생 제어**: `startAuto()`, `stopAuto()` 메서드로 제어
4. **이벤트 처리**: `preventDefault()`, `stopPropagation()`으로 이벤트 충돌 방지

---

## Vanilla JS 대체 방안

jQuery를 사용하지 않고 bxSlider와 유사한 기능을 구현하려면:

### 1. 순수 JavaScript 구현
- `../vanilla_slider/` 폴더의 예제 참고
- 페이드 효과 (`fade/`), 슬라이드 효과 (`slide/`) 등 구현 가능

### 2. 다른 라이브러리 사용

#### Swiper.js
```bash
npm install swiper
```

```javascript
import Swiper from 'swiper';
import 'swiper/css';

const swiper = new Swiper('.swiper', {
    effect: 'fade', // 페이드 효과
    autoplay: {
        delay: 3000
    }
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
    type: 'carousel',
    perView: 3
}).mount();
```

---

## 비교표

### bxSlider vs Slick Slider vs Vanilla JS

| 기능 | bxSlider | Slick Slider | Vanilla JS |
|------|----------|--------------|------------|
| **jQuery 필요** | ✅ 필요 | ✅ 필요 | ❌ 불필요 |
| **파일 크기** | ~15KB (+jQuery) | ~50KB (+jQuery) | ~5KB |
| **페이드 효과** | ✅ | ✅ | ✅ |
| **세로 슬라이더** | ✅ | ✅ | ❌ |
| **여러 슬라이드 표시** | ✅ | ✅ | ✅ |
| **터치 스와이프** | ✅ | ✅ | ✅ |
| **자동 재생** | ✅ | ✅ | ✅ |
| **무한 루프** | ✅ | ✅ | ✅ |
| **티커 모드** | ✅ | ❌ | ❌ |
| **비디오 지원** | ✅ | ❌ | ❌ |
| **옵션 수** | 중간 | 많음 | 커스터마이징 |
| **학습 곡선** | 낮음 | 낮음 | 중간 |

### 라이브러리 비교

| 라이브러리 | jQuery | 파일 크기 | 특징 |
|-----------|--------|----------|------|
| **bxSlider** | ✅ 필요 | ~15KB | 가볍고 간단 |
| **Slick Slider** | ✅ 필요 | ~50KB | 풍부한 옵션 |
| **Swiper.js** | ❌ 불필요 | ~40KB | 모던, 강력한 기능 |
| **Glide.js** | ❌ 불필요 | ~20KB | 가볍고 빠름 |
| **Vanilla JS** | ❌ 불필요 | ~5KB | 완전한 제어 |

---

## 문제 해결

### 문제 1: 슬라이더가 초기화되지 않음

**원인**: jQuery가 로드되기 전에 스크립트 실행

**해결책**:
```javascript
$(document).ready(function(){
    $('.bxslider').bxSlider();
});
```

### 문제 2: 슬라이더가 제대로 표시되지 않음

**원인**: CSS 파일이 로드되지 않음

**해결책**: `jquery.bxslider.css` 파일 확인

### 문제 3: 여러 슬라이드가 표시되지 않음

**원인**: `maxSlides` 옵션 미설정

**해결책**:
```javascript
$('.bxslider').bxSlider({
    minSlides: 1,
    maxSlides: 3,
    slideWidth: 300
});
```

### 문제 4: 페이드 효과가 작동하지 않음

**원인**: `mode` 옵션 설정 오류

**해결책**:
```javascript
$('.bxslider').bxSlider({
    mode: 'fade'
});
```

### 문제 5: 자동 재생이 작동하지 않음

**원인**: `auto` 옵션 미설정

**해결책**:
```javascript
$('.bxslider').bxSlider({
    auto: true,
    pause: 4000
});
```

### 문제 6: 슬라이더가 중복으로 초기화됨

**해결책**:
```javascript
if ($('.bxslider').data('bxSlider')) {
    $('.bxslider').destroySlider();
}
$('.bxslider').bxSlider({...});
```

### 문제 7: 커스텀 컨트롤 버튼이 작동하지 않음

**원인**: 메서드 호출 방식 오류

**해결책**: 초기화 시 반환된 객체에서 직접 메서드 호출
```javascript
// ✅ 올바른 방식
var slider = $('.bxslider').bxSlider({...});
slider.goToNextSlide();
slider.getCurrentSlide();

// ❌ 잘못된 방식
$('.bxslider').bxSlider('goToNextSlide');
```

### 문제 8: 슬라이드 번호가 업데이트되지 않음

**원인**: 콜백 함수에서 인덱스를 받지 않음

**해결책**: 콜백 함수에서 인덱스를 직접 사용
```javascript
var currentSlideIndex = 0;

var slider = $('.bxslider').bxSlider({
    onSliderLoad: function(currentIndex) {
        currentSlideIndex = currentIndex;
        updateCounter();
    },
    onSlideAfter: function($slideElement, oldIndex) {
        currentSlideIndex = oldIndex;
        updateCounter();
    }
});
```

### 문제 9: 페이드 모드에서 화살표가 사라짐

**원인**: z-index가 낮거나 슬라이드에 가려짐

**해결책**: 높은 z-index와 명시적 표시 설정
```css
.slider-fade .bx-wrapper .bx-controls-direction a {
    z-index: 9999 !important;
    display: block !important;
    visibility: visible !important;
    opacity: 1 !important;
}
```

### 문제 10: 페이드 모드에서 슬라이드가 좌측 상단에 위치

**원인**: 슬라이드 위치와 높이 설정 오류

**해결책**: 명시적인 위치와 높이 설정
```css
.slider-fade .bx-wrapper .bx-viewport .bxslider li {
    position: absolute !important;
    top: 0 !important;
    left: 0 !important;
    width: 100% !important;
    height: 600px !important;
    display: flex !important;
    align-items: center !important;
    justify-content: center !important;
}
```

```javascript
$('.slider-fade').bxSlider({
    mode: 'fade',
    adaptiveHeight: false,
    slideHeight: 600
});
```

### 문제 11: 티커 모드가 너무 빠름

**원인**: `tickerSpeed` 값이 너무 작음

**해결책**: `tickerSpeed` 값을 크게 설정
```javascript
$('.bxslider').bxSlider({
    ticker: true,
    tickerSpeed: 20000, // 20초 (값이 클수록 느림)
    speed: 2000, // 전환 속도
    easing: 'linear' // 선형 애니메이션
});
```

### 문제 12: Pager가 세로로 표시됨

**원인**: CSS flex-direction 설정 오류

**해결책**: flex-direction을 row로 설정
```css
.bx-wrapper .bx-pager.bx-default-pager {
    display: flex !important;
    flex-direction: row !important;
    justify-content: center !important;
    flex-wrap: nowrap !important;
}
```

### 문제 13: Pager가 숫자로 표시됨 (점 형태가 아님)

**원인**: `pagerType` 옵션 또는 CSS 설정 오류

**해결책**: 
```javascript
// 점 형태로 표시하려면
$('.bxslider').bxSlider({
    pager: true,
    pagerType: 'full' // 명시적으로 full 지정
});
```

```css
/* CSS로 강제 설정 */
.bx-wrapper .bx-pager.bx-default-pager a:not(.bx-pager-link) {
    text-indent: -9999px !important;
    width: 10px !important;
    height: 10px !important;
    background: #666 !important;
}
```

---

## 커스텀 컨트롤 구현

### 개요

bxSlider의 기본 컨트롤 대신 커스텀 컨트롤을 만들어서 더 세밀한 제어가 가능합니다.

### 구현 요소

1. **Play/Pause 버튼**: 자동 재생 토글
2. **Prev/Next 버튼**: 슬라이드 이동
3. **슬라이드 번호**: 현재/전체 슬라이드 표시

### 구현 단계

#### 1단계: HTML 구조 생성

```html
<div class="slider-wrapper">
    <div class="custom-controls">
        <button id="playPauseBtn">⏸</button>
        <button id="prevBtn">‹</button>
        <div id="slideCounter">1 / 5</div>
        <button id="nextBtn">›</button>
    </div>
    <ul class="slider">
        <li>Slide 1</li>
        <li>Slide 2</li>
        <li>Slide 3</li>
    </ul>
</div>
```

#### 2단계: CSS 스타일링

```css
.slider-wrapper {
    position: relative;
}

.custom-controls {
    position: absolute;
    bottom: 20px;
    left: 50%;
    transform: translateX(-50%);
    display: flex;
    align-items: center;
    gap: 15px;
    z-index: 1000;
    background: rgba(0, 0, 0, 0.7);
    padding: 10px 20px;
    border-radius: 30px;
}
```

#### 3단계: JavaScript 구현

```javascript
// 슬라이더 초기화 (기본 컨트롤 숨기기)
var slider = $('.slider').bxSlider({
    auto: true,
    pause: 3000,
    pager: false,
    controls: false,
    onSlideAfter: function($slideElement, oldIndex) {
        // 슬라이드 번호 업데이트
        updateCounter(oldIndex);
    }
});

// 슬라이드 번호 업데이트 함수
function updateCounter(index) {
    var current = index + 1;
    var total = slider.getSlideCount();
    $('#slideCounter').text(current + ' / ' + total);
}

// Play/Pause 버튼
$('#playPauseBtn').on('click', function() {
    if (isPlaying) {
        slider.stopAuto();
        $(this).text('▶');
        isPlaying = false;
    } else {
        slider.startAuto();
        $(this).text('⏸');
        isPlaying = true;
    }
});

// Prev/Next 버튼
$('#prevBtn').on('click', function() {
    slider.goToPrevSlide();
});

$('#nextBtn').on('click', function() {
    slider.goToNextSlide();
});
```

### 핵심 포인트

1. **메서드 호출**: 초기화 시 반환된 객체에서 직접 호출
2. **콜백 활용**: `onSlideAfter`에서 인덱스 받아서 업데이트
3. **상태 관리**: `isPlaying` 변수로 재생 상태 추적
4. **이벤트 처리**: `preventDefault()`로 기본 동작 방지

---

## 마무리

**bxSlider는 가볍고 사용하기 쉬운 슬라이더입니다.**

**선택 가이드:**
- ✅ **jQuery 프로젝트 + 가벼운 슬라이더**: bxSlider 사용
- ✅ **티커 모드 필요**: bxSlider 권장
- ✅ **풍부한 옵션 필요**: Slick Slider 권장
- ✅ **jQuery 없이**: Vanilla JS 구현 또는 다른 라이브러리

**참고 자료:**
- [bxSlider 공식 문서](http://bxslider.com/)
- [bxSlider GitHub](https://github.com/stevenwanderski/bxslider-4)
- [Vanilla JS 구현 예제](../vanilla_slider/)
- [예제 코드](./example/)

---

## 최신 예제 목록

현재 `example/index.html`에 포함된 예제들:

1. **예제 1**: 기본 슬라이더 (점 형태 pager)
2. **예제 1-2**: 숫자 형태 pager (1 / 4)
3. **예제 1-3**: 커스텀 숫자 pager (1, 2, 3, 4)
4. **예제 2**: 페이드 효과
5. **예제 3**: 여러 슬라이드 표시 (3개씩)
6. **예제 4**: 자동 재생 슬라이더
7. **예제 5**: 세로 슬라이더
8. **예제 6**: 티커 모드 (연속 스크롤)
9. **예제 7**: 커스텀 컨트롤 (Play/Stop 토글, Next/Prev, 슬라이드 번호)

모든 예제는 `example/index.html` 파일에서 확인할 수 있습니다.
