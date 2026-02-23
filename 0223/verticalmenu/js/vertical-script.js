
var menuLiA = document.querySelectorAll('.m_menu>li>a');

menuLiA.forEach(function(item){
  item.addEventListener('click',function(){
    var subHeight = getComputedStyle(this.nextElementSibling).height;
    // getComputedStyle - css값을 가져오는 함수
    // alert(subHeight);
    if(subHeight === '0px'){
      slideUp();
      this.nextElementSibling.style.height = '108px';
    }else{
      this.nextElementSibling.style.height = '0px';
    }
  });

});

var sub = document.querySelectorAll('.sub');
function slideUp(){
  sub.forEach(function(item){
    item.style.height = '0px';
  });
}