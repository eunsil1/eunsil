$(window).scroll(function(){//화면 위를 스크롤 할때
      var sct = $(this).scrollTop(); //현재 스크롤 위치값(스크롤양)
      $('.s_Top').text(sct);

      if(sct >= 450 && sct < 1000){
        $('.left1').addClass('on');
      }else{
        $('.left1').removeClass('on');
      }

      if(sct >= 1000 && sct < 1800){
        $('.right1').addClass('on');
      }else{
        $('.right1').removeClass('on');
      }
      
      if(sct >= 2500){
        $('.s4_1').addClass('active');
        setTimeout(function(){
          $('.s4_2').addClass('active');
        },400);
        setTimeout(function(){
          $('.s4_3').addClass('active');
        },800);
        setTimeout(function(){
          $('.s4_4').addClass('active');
        },1200);
      }

      // if(sct >= $('.container > div').eq(0).offset().top){
      //   $('nav ul li').removeClass('on');
      //   $('nav ul li').eq(0).addClass('on');
      // }

      // if(sct >= $('.container > div').eq(1).offset().top){
      //   $('nav ul li').removeClass('on');
      //   $('nav ul li').eq(1).addClass('on');
      // }

      // if(sct >= $('.container > div').eq(2).offset().top){
      //   $('nav ul li').removeClass('on');
      //   $('nav ul li').eq(2).addClass('on');
      // }

      // if(sct >= $('.container > div').eq(3).offset().top){
      //   $('nav ul li').removeClass('on');
      //   $('nav ul li').eq(3).addClass('on');
      // }

      // if(sct >= $('.container > div').eq(4).offset().top){
      //   $('nav ul li').removeClass('on');
      //   $('nav ul li').eq(4).addClass('on');
      // }

      // for(let i = 0; i < $('.container > div').length; i++){
      //   if(sct >= $('.container > div').eq(i).offset().top){
      //     $('nav ul li').removeClass('on');
      //     $('nav ul li').eq(i).addClass('on');
      //   }
      // }

      $('.container > div').each(function(index){
        if(sct >= $(this).offset().top){
        $('nav ul li').removeClass('on').eq(index).addClass('on');
        }
        
      });
      


    });

$('nav ul li').click(function(){
  var i = $(this).index();
  var offset_t= $('.container > div').eq(i).offset().top;
  //alert(offset_t);
  $('html, body').stop().animate({scrollTop:offset_t},1000);
  // $('nav ul li').removeClass('on');
  // $('nav ul li').eq(i).addClass('on');
  return false;
});

$('.container > div').mousewheel(function(event,d){
  console.log(d); //마우스 휠을 위로 하면 양수, 밑으로 하면 음수
  if(d > 0){
    let preVal= $(this).prev().offset().top;
    $('html,css').stop().animate({screenTop:preVal},1000,'easeOutBounce');
  }
  if(d < 0){ //휠 아래로 동작감지(-1)
    let nextVal = $(this).next().offset().top;
    $('html,css').stop().animate({scrollTop:nextVal},1000,'easeOutBounce');
  }
  
  // 크롬에서 jquery.easing.1.3.js 검색  easing(이징) 효과 모음 - jQuery 공작소

  
});

$('#popup').draggable();

if($.cookie('pop') != 'no'){
  $('#popup').show();
}

$('#popup area:eq(0)').click(function(){
  $('#popup').fadeOut('fast');
  //닫기 버튼 클릭하면 팝업을 서서히 숨긴다.
});

$('#popup area:eq(1)').click(function(){
  $.cookie('pop','no',{expires:1});
  $('#popup').fadeOut('fast');
  //닫기 버튼 클릭하면 팝업을 서서히 숨긴다.
});

$('#notice_wrap').draggable();
if($.cookie('popup') == 'none'){
  $('#notice_wrap').hide();
}
// popup 변수에 none이 저장되어있으면 $('#notice_wrap')을 숨겨라

let chk = $('#expiresChk');
$('.closeBtn').on('click',closePop);

function closePop(){
  if(chk.is(':checked')){ //chk 체크가 되어있다면
    $.cookie('popup','none',{expires:3});
  }
  $('#notice_wrap').fadeOut();
};
