
document.addEventListener('DOMContentLoaded',() => {
  const imgs = document.querySelector('.imgs');
  const items = document.querySelectorAll('.imgs li');
  const pager = document.querySelectorAll('.pager li');
  const prevBtn = document.querySelector('.prev');
  const nextBtn = document.querySelector('.next');
  const wrap = document.getElementById('wrap');

  const count = items.length; //실제 이미지 갯수 (5개)
  let i = 1; //현재 인덱스(1번부터 시작)
  let timer;

  //1. 무한루프를 위한 앞뒤 복제본 생성 및 추가

  const firstClone = items[0].cloneNode(true); //첫장복사
  const lastClone = items[count - 1].cloneNode(true);
  //끝이미지 복사

  imgs.appendChild(firstClone); //firstClone 맨뒤에 추가
  imgs.insertBefore(lastClone,items[0]); //인덱스 0번 앞에 lastClone 삽입

  //2. 초기 위치설정
  imgs.style.marginLeft = '-100%';

  //3. 슬라이드 이동 핵심 함수
  function move(index, speed=0.6){
    //애니메이션 적용
    imgs.style.transition = speed > 0 ? `margin-Left ${speed}s ease ` : `none`;  
    imgs.style.marginLeft = `${-index*100}%`;
    //페이저(점) 상태 업데이트(나머지 연산자를 이용해 복제본 구간에도 정확한 인덱스 표시)
    let pagerIdx = (index - 1 + count) % count;
    //p 현재 도트요소(점) - idx 현재 도트의 인덱스 번호
    pager.forEach((p, idx) => {
      p.classList.toggle('on',idx === pagerIdx);
    //element.classList.togglt(클래스명, 조건);
    //   pager.forEach((p) => {
    //   p.classList.remove('on');
    //   });
    // pager[pagerIdx].classList.add('on');

      
    });
    i = index;

  }

  //4. 무한루프 '순간 점프' 로직(가장 중요)
  imgs.addEventListener('transitionend',() => {
    if(i === 0){
      move(count,0)
    }
    //첫번째 복제본(6번)에 도달하면 실제 첫번째(1번) 위치로 순간이동
    else if(i === count + 1){
      move(1,0);
    }
  });

  //5. 버튼 및 페이저 클릭 이벤트 바인딩
  nextBtn.onclick = () => move(i+1);
  prevBtn.onclick = () => move(i-1);

  pager.forEach((p, idx) => {
    p.onclick = () => move(idx + 1);
});

  //6. 자동 재생 기능
  const startTimer = () => {
    timer = setInterval(()=> move(i+1), 3000);
  };

  const stopTimer = () => {
    clearInterval(timer);
  };

  //마우스 오버 시 일시 정지
  wrap.onmouseenter = stopTimer;
  wrap.onmouseleave = startTimer;

  startTimer();
  

});