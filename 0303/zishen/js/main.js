$(function(){

  $(window).resize(function(){
    var w = $(this).width();
    console.log(w);
    if(w <= 850){

    }else{
      if($('.mobile_nav').hasClass('active')){
      $('.mobile_nav').removeClass('active');
      $('.transparency').removeClass('active');
      $('.container').removeClass('active');
      $('.mobile_nav .sub').css('display','none');
    }
    
    }

  });
  $(window).trigger('resize');
  // 로딩 후 최초 리사이즈를 강제로 해줌

  $('.nav ul').hover(function(){
    $(this).addClass('over');
  },function(){
    $(this).removeClass('over');
  });

  $('.mobile_tab').click(function(){
    $('.mobile_nav').addClass('active');
    $('.transparency').addClass('active');
    $('.container').addClass('active');
  });

  $('.transparency').click(function(){
    $('.mobile_nav').removeClass('active');
    $('.transparency').removeClass('active');
    $('.container').removeClass('active');
    $('.mobile_nav .sub').css('display','none');
    return false;
  });

  $('.mobile_nav > ul > li > a').click(function(){
    var k = $(this).next('.sub').css('display');
    // alert(k);
    if(k == 'none'){
      $('.mobile_nav .sub').slideUp(300);
      $(this).next('.sub').slideDown(300);
    }else{
      $(this).next('.sub').slideUp(300);
    }
    return false;
  });

  //스크롤 시 아이템 페이드 애니메이션

  $('.item').css({
    'opacity':'0',
    'transform':'translateY(30px)',
    'transition':'opacity 0.6s ease, transform 0.6s ease'
  });
  $('.item').each(function(index){
    $(this).css('transition-delay',(index+0.1) +'s');
  });
  // 'transition-delay : 0;, transition-delay: 0.1;, transition-delay: 0.2;


  // $(window).on('scroll resize',function(){
  //   $('.item').each(function(){
  //     // 요소의 위치 정보
  //     var elementTop = $(this).offset().top;
  //     var elementBottom = elementTop + $(this).outerHeight();

  //     var viewportTop = $(window).scrollTop();
      
  //     // 요소가 뷰포트 안에 보이는지 확인
  //     if(elementBottom > viewportTop && elementTop < viewportBottom){
  //       // 보이면 페이드인 효과 적용
  //       $(this).css({
  //         'opacity': '1',
  //         'transform': 'translateY(0)'
  //       });
  //     }


  //   });
    
  // });

  $(window).on('scroll', function() {

    $('item').each(function() {

        // 🔹 요소 위치
        var elementTop = $(this).offset().top;
        var elementHeight = $(this).outerHeight();

        // 🔹 화면(뷰포트) 위치
        var viewportTop = $(window).scrollTop();
        var viewportBottom = viewportTop + $(window).height();

        // 🔹 요소의 중간 지점
        var elementMiddle = elementTop + (elementHeight / 2);

        // 🔥 요소의 중간이 화면 아래쪽보다 위에 오면 실행
        if (elementMiddle < viewportBottom) {
  
        }

    });
});
});