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