# bxSlider 완전 정복

## 📋 목차
1. [bxSlider란?](#bxslider란)
2. [설치 방법](#설치-방법)
3. [기본 사용법](#기본-사용법)
4. [주요 옵션](#주요-옵션)
5. [이벤트](#이벤트)
6. [메서드](#메서드)
7. [Vanilla JS 대체 방안](#vanilla-js-대체-방안)
8. [비교표](#비교표)

---

## bxSlider란?

**bxSlider**는 jQuery 기반의 반응형 콘텐츠 슬라이더 플러그인입니다.

### 특징
- ✅ 반응형 디자인
- ✅ 터치 스와이프 지원
- ✅ 다양한 전환 효과 (fade, slide, horizontal, vertical)
- ✅ 자동 재생
- ✅ 무한 루프
- ✅ 가볍고 빠름
- ✅ 접근성 지원

### 단점
- ❌ jQuery 의존성 (jQuery 1.4 이상 필요)
- ❌ Slick Slider보다 옵션이 적음
- ❌ 최근 업데이트가 적음

---

## 설치 방법

### 1. CDN 사용

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

### 3. 파일 다운로드

[공식 GitHub](https://github.com/stevenwanderski/bxslider-4)에서 다운로드

---

## 기본 사용법

### HTML 구조

```html
<ul class="bxslider">
    <li>슬라이드 1</li>
    <li>슬라이드 2</li>
    <li>슬라이드 3</li>
    <li>슬라이드 4</li>
</ul>
```

### JavaScript 초기화

```javascript
$(document).ready(function(){
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

| 옵션 | 타입 | 기본값 | 설명 |
|------|------|--------|------|
| `mode` | string | 'horizontal' | 슬라이드 모드 ('horizontal', 'vertical', 'fade') |
| `slideWidth` | number | 0 | 슬라이드 너비 (0이면 자동) |
| `minSlides` | number | 1 | 최소 슬라이드 개수 |
| `maxSlides` | number | 1 | 최대 슬라이드 개수 |
| `moveSlides` | number | 0 | 이동할 슬라이드 개수 (0이면 maxSlides) |
| `auto` | boolean | false | 자동 재생 |
| `autoHover` | boolean | false | 호버 시 일시 정지 |
| `pause` | number | 4000 | 자동 재생 간격 (ms) |
| `pager` | boolean | true | 페이지네이션 표시 |
| `controls` | boolean | true | 이전/다음 버튼 표시 |
| `infiniteLoop` | boolean | true | 무한 루프 |
| `speed` | number | 500 | 전환 속도 (ms) |
| `easing` | string | null | easing 함수 |
| `touchEnabled` | boolean | true | 터치 제스처 활성화 |
| `swipeThreshold` | number | 50 | 스와이프 임계값 (px) |
| `oneToOneTouch` | boolean | true | 1:1 터치 비율 |
| `preventDefaultSwipeX` | boolean | true | X축 스와이프 기본 동작 방지 |
| `preventDefaultSwipeY` | boolean | false | Y축 스와이프 기본 동작 방지 |
| `adaptiveHeight` | boolean | false | 슬라이드 높이 자동 조정 |
| `adaptiveHeightSpeed` | number | 500 | 높이 조정 속도 (ms) |
| `video` | boolean | false | 비디오 지원 |
| `useCSS` | boolean | true | CSS transition 사용 |
| `wrapperClass` | string | 'bx-wrapper' | 래퍼 클래스명 |
| `pagerType` | string | 'full' | 페이지네이션 타입 ('full', 'short') |
| `pagerShortSeparator` | string | ' / ' | 짧은 페이지네이션 구분자 |
| `pagerSelector` | string | null | 페이지네이션 선택자 |
| `nextText` | string | 'Next' | 다음 버튼 텍스트 |
| `prevText` | string | 'Prev' | 이전 버튼 텍스트 |
| `nextSelector` | string | null | 다음 버튼 선택자 |
| `prevSelector` | string | null | 이전 버튼 선택자 |
| `autoControls` | boolean | false | 자동 재생 컨트롤 표시 |
| `autoControlsCombine` | boolean | false | 자동 재생 컨트롤 결합 |
| `autoControlsSelector` | string | null | 자동 재생 컨트롤 선택자 |
| `keyboardEnabled` | boolean | false | 키보드 네비게이션 |
| `hideControlOnEnd` | boolean | false | 끝에서 컨트롤 숨기기 |
| `ticker` | boolean | false | 티커 모드 |
| `tickerHover` | boolean | false | 티커 호버 일시 정지 |
| `tickerSpeed` | number | 5000 | 티커 속도 (ms) |
| `captions` | boolean | false | 캡션 표시 |
| `startSlide` | number | 0 | 시작 슬라이드 인덱스 |
| `randomStart` | boolean | false | 랜덤 시작 |
| `shuffleShortcodes` | boolean | false | 쇼트코드 셔플 |
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
```

### 메서드 목록

| 메서드 | 설명 |
|--------|------|
| `goToNextSlide()` | 다음 슬라이드로 이동 |
| `goToPrevSlide()` | 이전 슬라이드로 이동 |
| `goToSlide(index)` | 특정 슬라이드로 이동 |
| `startAuto()` | 자동 재생 시작 |
| `stopAuto()` | 자동 재생 정지 |
| `destroySlider()` | 슬라이더 제거 |
| `reloadSlider(options)` | 슬라이더 재생성 |
| `getCurrentSlide()` | 현재 슬라이드 인덱스 |
| `getSlideCount()` | 총 슬라이드 개수 |

---

## Vanilla JS 대체 방안

jQuery를 사용하지 않고 bxSlider와 유사한 기능을 구현하려면:

### 1. 순수 JavaScript 구현
- `../vanilla_slider/` 폴더의 예제 참고
- 페이드 효과, 슬라이드 효과 등 구현 가능

### 2. 다른 라이브러리 사용
- **Swiper.js**: 모던하고 강력한 슬라이더
- **Glide.js**: 가볍고 빠른 슬라이더
- **Splide.js**: 접근성에 중점을 둔 슬라이더

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
| **옵션 수** | 중간 | 많음 | 커스터마이징 |

---

## 마무리

**bxSlider는 가볍고 사용하기 쉬운 슬라이더입니다.**

**선택 가이드:**
- ✅ **jQuery 프로젝트 + 가벼운 슬라이더**: bxSlider 사용
- ✅ **풍부한 옵션 필요**: Slick Slider 권장
- ✅ **jQuery 없이**: Vanilla JS 구현 또는 다른 라이브러리

**참고 자료:**
- [bxSlider 공식 문서](http://bxslider.com/)
- [bxSlider GitHub](https://github.com/stevenwanderski/bxslider-4)
- [Vanilla JS 구현 예제](../vanilla_slider/)
