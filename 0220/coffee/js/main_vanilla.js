document.addEventListener('DOMContentLoaded',() => {
  const imgs = document.querySelector('.imgs');
  const items = document.querySelectorAll('.imgs li');
  const pager = document.querySelectorAll('.pager li');
  const prevBtn = document.querySelector('.prev');
  const nextBtn = document.querySelector('.next');
  const wrap = document.getElementById('wrap');

  const count = items.length;
  let i = 1;
  let timer;

  const firstClone = items[0].cloneNode(true);
  const lastClone = items[count-1].cloneNode(true);

  imgs.appendChild(firstClone);
  imgs.insertBefore(lastClone,items[0]);

  imgs.style.marginLeft = '-100%';

  function move(index,speed=0.6){
    imgs.style.transition = speed > 0 ? `margin-Left ${speed}s ease` : `none`;
    imgs.style.marginLeft = `${-index * 100}%`;

    let pagerIdx = (index - 1 + count) % count;
    pager.forEach((p,idx) => {
      p.classList.toggle('on',idx === pagerIdx);
    });
    i = index;
  }

  imgs.addEventListener('transitionend',() =>{
    if(i === 0){
      move(count, 0)
    }
    else if(i === count + 1){
      move(1,0);
    }
  });

  nextBtn.onclick = () => move(i+1);
  prevBtn.onclick = () => move(i-1);

  const startTimer = () => {
    timer = setInterval(() => move(i+1), 3000);
  };

  const stopTimer = () => {
    clearInterval(timer);
  };

  wrap.onmouseenter = stopTimer;
  wrap.onmouseleave = startTimer;

  startTimer();
});