# jQuery 선택자 다양한 구현 방법

## 문제 코드

```javascript
onSlideAfter:function(){
    let k = slider.getCurrentSlide(); // 현재 슬라이드 번호 인덱스
    $('.slider li').find('h1').removeClass('on');
    $('.slider li .text'+k).addClass('on');  // 이 부분을 다른 방법으로 구현
}
```

현재 방식: 문자열 연결을 사용한 클래스 선택자 (`'.text'+k`)

---

## 구현 방법 비교

### 방법 1: `eq()` 메서드 사용 (권장)

```javascript
onSlideAfter:function(){
    let k = slider.getCurrentSlide();
    $('.slider li').find('h1').removeClass('on');
    $('.slider li').eq(k).find('h1').addClass('on');
}
```

**장점:**
- ✅ 인덱스 기반으로 직접 접근 가능
- ✅ 클래스명에 의존하지 않음 (text0, text1 등 불필요)
- ✅ 코드가 더 명확하고 읽기 쉬움
- ✅ 유지보수 용이

**단점:**
- ❌ HTML 구조 변경 시 주의 필요

---

### 방법 2: `children()` 사용

```javascript
onSlideAfter:function(){
    let k = slider.getCurrentSlide();
    $('.slider li h1').removeClass('on');
    $('.slider li').eq(k).children('h1').addClass('on');
}
```

**장점:**
- ✅ 직접 자식 요소만 선택
- ✅ 더 정확한 선택

**단점:**
- ❌ h1이 직접 자식이 아닐 경우 작동 안 함

---

### 방법 3: `nth-child()` 선택자 사용

```javascript
onSlideAfter:function(){
    let k = slider.getCurrentSlide();
    $('.slider li h1').removeClass('on');
    $('.slider li:nth-child(' + (k + 1) + ') h1').addClass('on');
}
```

**장점:**
- ✅ CSS 선택자와 유사한 방식
- ✅ 한 번에 선택 가능

**단점:**
- ❌ nth-child는 1부터 시작하므로 k+1 필요
- ❌ 문자열 연결 여전히 사용

---

### 방법 4: 데이터 속성(data-*) 사용

**HTML 수정:**
```html
<ul class="slider">
    <li data-slide="0">
        <a href="#"><img src="img/w1.JPG" alt=""></a>
        <h1>fashion style1</h1>
    </li>
    <li data-slide="1">
        <a href="#"><img src="img/w2.JPG" alt=""></a>
        <h1>fashion style2</h1>
    </li>
    <!-- ... -->
</ul>
```

**JavaScript:**
```javascript
onSlideAfter:function(){
    let k = slider.getCurrentSlide();
    $('.slider li h1').removeClass('on');
    $('.slider li[data-slide="' + k + '"] h1').addClass('on');
}
```

**또는:**
```javascript
onSlideAfter:function(){
    let k = slider.getCurrentSlide();
    $('.slider li h1').removeClass('on');
    $('.slider li').filter('[data-slide="' + k + '"]').find('h1').addClass('on');
}
```

**장점:**
- ✅ 의미 있는 데이터 속성 사용
- ✅ 클래스명에 의존하지 않음
- ✅ HTML 구조와 로직 분리

**단점:**
- ❌ HTML 수정 필요
- ❌ 약간의 오버헤드

---

### 방법 5: `each()`와 인덱스 비교

```javascript
onSlideAfter:function(){
    let k = slider.getCurrentSlide();
    $('.slider li h1').removeClass('on');
    $('.slider li').each(function(index){
        if(index === k){
            $(this).find('h1').addClass('on');
        }
    });
}
```

**장점:**
- ✅ 명시적인 인덱스 비교
- ✅ 추가 로직 삽입 용이

**단점:**
- ❌ 불필요한 반복 (성능 저하)
- ❌ 코드가 길어짐

---

### 방법 6: 현재 슬라이드 요소 직접 사용

```javascript
onSlideAfter:function($slideElement, oldIndex, newIndex){
    $('.slider li h1').removeClass('on');
    $slideElement.find('h1').addClass('on');
}
```

**장점:**
- ✅ bxSlider가 제공하는 현재 슬라이드 요소 직접 사용
- ✅ 가장 효율적이고 정확함
- ✅ 인덱스 계산 불필요

**단점:**
- ❌ bxSlider 콜백 함수의 매개변수 활용 필요

---

### 방법 7: 배열 인덱싱 방식

```javascript
onSlideAfter:function(){
    let k = slider.getCurrentSlide();
    let $slides = $('.slider li');
    $slides.find('h1').removeClass('on');
    $slides.eq(k).find('h1').addClass('on');
}
```

**장점:**
- ✅ jQuery 객체를 변수에 저장하여 재사용
- ✅ 성능 최적화 가능

**단점:**
- ❌ 변수 관리 필요

---

## 성능 비교

| 방법 | 성능 | 가독성 | 유지보수성 |
|------|------|--------|-----------|
| 방법 1: `eq()` | ⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ |
| 방법 2: `children()` | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ |
| 방법 3: `nth-child()` | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐ |
| 방법 4: `data-*` | ⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ |
| 방법 5: `each()` | ⭐⭐ | ⭐⭐ | ⭐⭐⭐ |
| 방법 6: 콜백 매개변수 | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ |
| 방법 7: 배열 인덱싱 | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ |

---

## 최종 권장 방법

### 🏆 방법 6: 콜백 매개변수 사용 (가장 권장)

```javascript
$(function(){
    let slider = $('.slider').bxSlider({
        auto:true,
        controls:false,
        onSlideBefore:function($slideElement, oldIndex, newIndex){
            let n = slider.getCurrentSlide(); 
            $('#page ul li').removeClass('on');
            $('#page ul li').eq(n).addClass('on');
            let w = $('#page ul li').width();
            if(n == 0){
                 $('#focus').css('left','0');
            }else{
                 $('#focus').stop().animate({left:n*w},600);
            }
        },
        onSlideAfter:function($slideElement, oldIndex, newIndex){
            // 모든 h1에서 'on' 클래스 제거
            $('.slider li h1').removeClass('on');
            // 현재 슬라이드의 h1에 'on' 클래스 추가
            $slideElement.find('h1').addClass('on');
        }
    });

    $('.left_btn').click(function(){
        slider.goToPrevSlide();
    });

    $('.right_btn').click(function(){
        slider.goToNextSlide();
    });
});
```

**이유:**
- ✅ bxSlider가 제공하는 현재 슬라이드 요소를 직접 사용
- ✅ 인덱스 계산 불필요
- ✅ 가장 효율적이고 정확함
- ✅ 코드가 간결하고 명확함

---

### 🥈 방법 1: `eq()` 메서드 사용 (대안)

```javascript
onSlideAfter:function(){
    let k = slider.getCurrentSlide();
    $('.slider li h1').removeClass('on');
    $('.slider li').eq(k).find('h1').addClass('on');
}
```

**이유:**
- ✅ 클래스명에 의존하지 않음
- ✅ 코드가 명확함
- ✅ HTML 수정 불필요

---

## 전체 코드 예시

### 개선된 전체 코드 (방법 6 사용)

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="css/jquery.bxslider7.css">
    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="js/jquery.bxslider.js"></script>
    <style>
        *{margin: 0;padding:0;}
        ul,li{list-style: none;}
        a{text-decoration: none;color:#333;}
        #img_slider{
            width:640px;
            margin:0 auto;
            position:relative;
        }
        #btn p{
            position: absolute;
            top:40%;
        }
        #btn  p.left_btn{
            left: 20px;
        }
        #btn p.right_btn{
            right:20px;
        }
        #btn p a{font-size: 40px;font-weight: bold;color:#000;}
        #btn p a:hover{color:#bbb;}

        #page{
            width:640px; margin: 0 auto;overflow: hidden;
        }
        #page ul{position: relative;}
        #page ul li{
            float: left;
            width:14%;
            height:30px;
            line-height: 30px;
            box-sizing: border-box;
            text-align: center;
        }
        #page ul #focus{
            position: absolute;
            width:14%;
            top:0;left:0;
            border:2px solid purple;
            box-sizing: border-box;
            height:30px;
            border-radius: 5px;
        }
        #page ul li.on a{
            color:#bbb;
        }

        .slider li{
            position: relative;
        }
        .slider li h1{
            position: absolute;
            left:20px;
            top:60px;
            z-index: 999;
            transform: translateY(60px);
            opacity: 0;
            transition: all 0.6s;
        }
        .slider li h1.on{
            opacity: 1;
            transform: translateY(0px);
        }
    </style>
    <script>
        $(function(){
            let slider = $('.slider').bxSlider({
                auto:true,
                controls:false,
                onSlideBefore:function($slideElement, oldIndex, newIndex){
                    let n = slider.getCurrentSlide(); 
                    $('#page ul li').removeClass('on');
                    $('#page ul li').eq(n).addClass('on');
                    let w = $('#page ul li').width();
                    if(n == 0){
                         $('#focus').css('left','0');
                    }else{
                         $('#focus').stop().animate({left:n*w},600);
                    }
                },
                onSlideAfter:function($slideElement, oldIndex, newIndex){
                    // 방법 6: 콜백 매개변수 사용 (권장)
                    $('.slider li h1').removeClass('on');
                    $slideElement.find('h1').addClass('on');
                    
                    // 또는 방법 1: eq() 사용
                    // let k = slider.getCurrentSlide();
                    // $('.slider li h1').removeClass('on');
                    // $('.slider li').eq(k).find('h1').addClass('on');
                }
            });

            $('.left_btn').click(function(){
                slider.goToPrevSlide();
            });

            $('.right_btn').click(function(){
                slider.goToNextSlide();
            });
        });
    </script>
</head>
<body>
     <div id="img_slider">
        <ul class="slider">
            <li>
                <a href="#"><img src="img/w1.JPG" alt=""></a>
                <h1 class="on">fashion style1</h1>  <!-- text0 클래스 제거 가능 -->
            </li>
            <li>
                <a href="#"><img src="img/w2.JPG" alt=""></a>
                <h1>fashion style2</h1>  <!-- text1 클래스 제거 가능 -->
            </li>
            <!-- 나머지도 동일하게 수정 -->
        </ul>
        <!-- ... 나머지 HTML ... -->
    </div>
</body>
</html>
```

---

## 추가 개선 사항

### HTML 클래스명 정리

원래 코드는 `text0`, `text1` 등의 클래스가 필요했지만, 개선된 방법을 사용하면 이런 클래스가 불필요합니다:

**Before:**
```html
<h1 class="text0 on">fashion style1</h1>
<h1 class="text1">fashion style2</h1>
```

**After:**
```html
<h1 class="on">fashion style1</h1>
<h1>fashion style2</h1>
```

---

## 요약

**원래 코드의 문제점:**
- 클래스명에 인덱스 번호가 포함되어 있어 유지보수 어려움
- 문자열 연결로 인한 실수 가능성
- HTML 구조 변경 시 JavaScript도 수정 필요

**개선된 방법의 장점:**
- 클래스명에 의존하지 않음
- 인덱스 기반 직접 접근
- 코드가 더 명확하고 읽기 쉬움
- 유지보수 용이

**최종 추천:**
- **방법 6** (콜백 매개변수 사용) - 가장 효율적
- **방법 1** (`eq()` 사용) - 가장 간단하고 명확
