$(function () {

  var $slider = $('.slick-slider');

  $slider.on('init reInit afterChange', function (event, slick, currentSlide) {
    var i = (currentSlide ? currentSlide : 0) + 1;
    $('#current-slide').text(i < 10 ? '0' + i : i);
    $('#total-slides').text(slick.slideCount);
  });

  $slider.slick({
    autoplay: true,
    autoplaySpeed: 2000,
    arrows: false,
    dots: false,
    pauseOnHover: false
  });

  // 이전 버튼
  $('.prev').click(function (e) {
    e.preventDefault();
    $slider.slick('slickPrev');
  });

  // 다음 버튼
  $('.next').click(function (e) {
    e.preventDefault();
    $slider.slick('slickNext');
  });

  // 일시정지 / 재생
  var isPlaying = true;

  $('.pause').click(function (e) {
    e.preventDefault();

    if (isPlaying) {
      $slider.slick('slickPause');
      $(this).addClass('play');
    } else {
      $slider.slick('slickPlay');
      $(this).removeClass('play');
    }

    isPlaying = !isPlaying;
  });

});