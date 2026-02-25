$(function(){
  var slider = $('.slick-slider').slick({
    // slidesToShow: 1,
    // slidesToScroll: 1,
    autoplay: true,
    // autoplaySpeed: 3000,
    // speed: 500,
    arrows: false,
    dots: false,
    // pauseOnHover: true,
    // infinite: true,
    // fade: false,
    // cssEase: 'ease-in-out',
  });

  slider.on('init',function(event,slick){
    //전체 슬라이드 갯수
    $('#total-slides').text(slick.slideCount);
    //초기 슬라이드 갯수
    $('#current-slide').text(slick.currentSlide + 1);
  });

  slider.on('afterChange',function(event,slick,currentSlide){
    //슬라이드 변경 시 현재 번호
    $('#current-slide').text(slick.currentSlide + 1);
  });

  // 이전 버튼
  $('.prev').click(function(e){
    e.preventDefault();
    slider.slick('slickPrev');
    return false;
  });

  // 다음 버튼
  $('.next').click(function(e){
    e.preventDefault();
    slider.slick('slickNext');
    return false;
  });

  // 일시정지/재생 버튼
  var isPaused = false;
  $('.pause').click(function(e){
    e.preventDefault();
    if(!isPaused){
      slider.slick('slickPause');
      $(this).addClass('on');
      isPaused = true;
    } else {
      slider.slick('slickPlay');
      $(this).removeClass('on');
      isPaused = false;
    }
    return false;
  });
});
