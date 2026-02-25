document.addEventListener('DOMContentLoaded', function(){
  var swiper = new Swiper('.swiper', {
    // 기본 설정
    slidesPerView: 1,
    spaceBetween: 0,
    loop: false,
    
    // 자동 재생 설정
    autoplay: {
      delay: 3000,
      disableOnInteraction: false,
      pauseOnMouseEnter: true
    },
    
    // 전환 효과
    speed: 500,
    effect: 'slide',
    
    // 이벤트 콜백
    on: {
      init: function() {
        // 초기화 시 전체 슬라이드 개수 표시
        var totalSlides = this.slides.length;
        document.getElementById('total-slides').textContent = totalSlides;
      },
      slideChange: function() {
        // 슬라이드 변경 시 현재 슬라이드 번호 업데이트
        var currentIndex = this.realIndex + 1;
        document.getElementById('current-slide').textContent = currentIndex;
      }
    }
  });

  // 이전 버튼
  document.querySelector('.prev').addEventListener('click', function(e){
    e.preventDefault();
    swiper.slidePrev();
    return false;
  });

  // 다음 버튼
  document.querySelector('.next').addEventListener('click', function(e){
    e.preventDefault();
    swiper.slideNext();
    return false;
  });

  // 일시정지/재생 버튼
  var pauseButton = document.querySelector('.pause');
  var isPaused = false;
  
  pauseButton.addEventListener('click', function(e){
    e.preventDefault();
    if(!isPaused){
      swiper.autoplay.stop();
      this.classList.add('on');
      isPaused = true;
    } else {
      swiper.autoplay.start();
      this.classList.remove('on');
      isPaused = false;
    }
    return false;
  });
});
