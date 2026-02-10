const targetLink = document.querySelectorAll('.tab_menu li a'); //탭버튼

const tabContent = document.querySelectorAll('#tabContent > div'); //탭내용

targetLink.forEach(function(link){
  link.addEventListener('click',function(e){
    e.preventDefault();
    let orgTarget = e.target.getAttribute('href');
    //alert(orgTarget);

    let tabTarget = orgTarget.replace('#',''); //#제거
    
    tabContent.forEach(function(content){
      content.style.display = 'none';
    }); //모든 탭내용 안보이게

    document.getElementById(tabTarget).style.display = 'block';
    // tabId를 찾아서 해하는 것을 보이도록 합니다.

    //targetLink.forEach()

    targetLink.forEach(function(eva){
      eva.classList.remove('active');
    });
    // class 모두 제거 후 추가
    e.target.classList.add('active');
  });
});