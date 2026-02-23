$(function(){
  // $('.m_menu li ul').css('display','none');
  // $('.m_menu li:first ul').css('display','block');

  $('.sub').hide();
  // $('.sub').eq(0).show();

  //$('.m_menu li:nth-child(1) ul').css('display','block');
  //$('.m_menu li:nth-child(1) ul').show();
  //$('.m_menu li:eq(0) ul').show();
  //$('.m_menu li:first ul').show();

  $('.m_menu > li > a').on('click',function(e){
    //.m_menu 안에 li 자식인 a 클릭
    e.preventDefault();//a링크 금지

    var status = $(this).next('.sub').css('display');
    //클릭한 다음에 있는 .sub의 display 상태(none/block)
    // alert(status);
    if(status === 'none'){
      $('.sub').slideUp();//모든 sub를 닫고
      $(this).next('.sub').slideDown();
    }else{
      $('.sub').slideToggle();
    }
  });
  
});