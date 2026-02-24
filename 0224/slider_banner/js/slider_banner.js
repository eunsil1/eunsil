$(function(){
 var visual = $('#brandVisual > ul > li'); //큰사진
 var button = $('#buttonList li');//pager 버튼
 var current = 0;//현재 사진(처음-초기화)
 var id; //자동 setInterval을 기억하는 변수
//  var i;
 
 button.on('click',function(){
  var i = $(this).index();//현재 클릭한 인덱스 번호(지역변수)
  // alert(i);
  button.removeClass('on');
  button.eq(i).addClass('on');
  move(i);
  return false;
 });

 function timer(){ //3초마다 한번씩 setInterval 안의 함수를 실행시킴
  id = setInterval(function(){
    var n = current + 1;
    if(n === 3) n = 0;

    button.eq(n).trigger('click');
    //컴퓨터가 1씩 증가하면서 버튼을 강제(trigger) 클릭('click')
  },3000);
 }

 timer();

 function move(i){
  if(current === i) return; //current와 클릭한 i 버튼이 같으면 빠져나감
  var cu = visual.eq(current);
  var ne = visual.eq(i);

  cu.css('left', '0').stop().animate({'left':'-100%'},500);
  ne.css('left', '100%').stop().animate({'left':'0%'},500);

  current = i;

 }
});