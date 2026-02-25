# 성남시청 슬라이더 - Swiper 버전

Swiper 라이브러리를 사용하여 구현한 성남시청 슬라이더입니다.

## 📁 파일 구조

```
성남시청슬라이드_swiper/
├── index.html              # 메인 HTML 파일
├── css/
│   └── style.css           # 스타일시트
├── js/
│   └── main.js            # Swiper 초기화 및 컨트롤 코드
├── img/                   # 이미지 파일 (원본 폴더에서 복사 필요)
├── example/
│   ├── index.html         # 다양한 Swiper 예제 모음
│   └── seongnam-example.html  # 성남시청 슬라이더 예제
├── Swiper-완전정복.md      # Swiper 완전 정복 가이드
└── README.md              # 이 파일
```

## 🚀 빠른 시작

1. **CDN 사용** (권장)
   - `index.html` 파일을 브라우저에서 열면 바로 사용 가능합니다.
   - Swiper는 CDN을 통해 자동으로 로드됩니다.

2. **이미지 설정**
   - `img/` 폴더에 이미지 파일을 복사하거나
   - `index.html`에서 이미지 경로를 수정하세요.

## ✨ 주요 기능

- ✅ 자동 재생 (3초 간격)
- ✅ 이전/다음 버튼
- ✅ 일시정지/재생 버튼
- ✅ 현재 슬라이드 번호 표시
- ✅ 부드러운 전환 효과
- ✅ 터치 스와이프 지원 (모바일)
- ✅ 마우스 드래그 지원 (데스크톱)

## 📖 사용법

### 기본 사용

```html
<div class="swiper">
  <div class="swiper-wrapper">
    <div class="swiper-slide"><img src="img/s1.jpg"></div>
    <div class="swiper-slide"><img src="img/s2.jpg"></div>
    <!-- 더 많은 슬라이드... -->
  </div>
</div>
```

```javascript
const swiper = new Swiper('.swiper', {
  autoplay: {
    delay: 3000,
  },
  // 기타 옵션...
});
```

### 커스터마이징

`js/main.js` 파일을 수정하여 옵션을 변경할 수 있습니다:

```javascript
var swiper = new Swiper('.swiper', {
  slidesPerView: 1,        // 표시할 슬라이드 개수
  spaceBetween: 0,         // 슬라이드 간 간격
  loop: false,             // 무한 루프
  autoplay: {
    delay: 3000,           // 자동 재생 간격 (ms)
    pauseOnMouseEnter: true  // 마우스 호버 시 일시정지
  },
  speed: 500,              // 전환 속도 (ms)
  effect: 'slide',         // 전환 효과
});
```

## 📚 더 알아보기

- **Swiper 완전 정복 가이드**: `Swiper-완전정복.md` 파일 참고
- **다양한 예제**: `example/index.html` 파일 참고
- **공식 문서**: [Swiper 공식 사이트](https://swiperjs.com/)

## 🔧 옵션 변경 예제

### 무한 루프 활성화

```javascript
loop: true,
```

### 페이드 효과

```javascript
effect: 'fade',
fadeEffect: {
  crossFade: true
}
```

### 여러 슬라이드 표시

```javascript
slidesPerView: 3,
spaceBetween: 30,
```

### 반응형 설정

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

## 🎯 주요 메서드

```javascript
// 다음 슬라이드로 이동
swiper.slideNext();

// 이전 슬라이드로 이동
swiper.slidePrev();

// 특정 슬라이드로 이동
swiper.slideTo(2);

// 자동 재생 시작
swiper.autoplay.start();

// 자동 재생 정지
swiper.autoplay.stop();
```

## 📝 이벤트

```javascript
swiper.on('slideChange', function () {
  console.log('슬라이드 변경:', this.activeIndex);
});

swiper.on('init', function () {
  console.log('슬라이더 초기화 완료');
});
```

## 🔄 다른 슬라이더와 비교

| 기능 | Swiper | Slick | bxSlider |
|------|--------|-------|----------|
| jQuery 필요 | ❌ | ✅ | ✅ |
| 터치 스와이프 | ✅ | ✅ | ✅ |
| 마우스 드래그 | ✅ | ❌ | ❌ |
| 다양한 효과 | ✅ | ⚠️ | ❌ |
| 파일 크기 | ~40KB | ~50KB | ~15KB |

## 💡 팁

1. **이미지 최적화**: 슬라이더에 사용할 이미지는 웹 최적화를 권장합니다.
2. **반응형**: 모바일 환경을 고려하여 `breakpoints` 옵션을 활용하세요.
3. **접근성**: 키보드 네비게이션을 위해 `keyboard` 옵션을 활성화할 수 있습니다.

## 📄 라이선스

이 프로젝트는 교육 목적으로 제작되었습니다.

## 🤝 기여

버그 리포트나 개선 사항은 이슈로 등록해주세요.
