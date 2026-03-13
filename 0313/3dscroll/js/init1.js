document.addEventListener('DOMContentLoaded',()=>{
  const articles = document.querySelectorAll('section > article');
  const navItems = document.querySelectorAll('.scrollNav li');
  window.addEventListener('scroll',()=>{
    const sct = window.scrollY || window.pageYOffset;
    
    

    articles.forEach((article, i)=>{

      //translateZ변경
      article.style.transform = `translateZ(${(-5000*i)+sct}px)`;

      //클래스 추가 제거
      if(sct >= i*5000 && sct < (i+1)*5000){
        //모든 article과 nav에서 on을 제거
        articles.forEach(el => el.classList.remove('on'));
        navItems.forEach(el => el.classList.remove('on'));
        article.classList.add('on');
        if(navItems[i]) navItems[i].classList.add('on');
      }
    });
    
  });
  navItems.forEach((item, index)=>{
  item.addEventListener('click',function(){
    window.scrollTo({top:5000*index,behavior:'smooth'});
  });
});
  
 document.body.addEventListener("mousemove", function (e) {

      let posX = e.pageX / 100;
      let posY = e.pageY / 150;

      const setStyle = (selector, styles) => {
          const el = document.querySelector(selector);
          if (el) {
              Object.assign(el.style, styles);
          }
      };

        setStyle(".obj11", { left: (-270 - posX) + "px", bottom: (-100 - posY) + "px" });
        setStyle(".obj12", { right: (-590 - posX) + "px", top: (-90 + posY) + "px" });
        setStyle(".obj13", { left: (-100 + posX) + "px", bottom: (20 + posY) + "px" });

        setStyle(".obj21", { right: (-710 - posX) + "px", bottom: (-420 - posY) + "px" });
        setStyle(".obj22", { right: (-50 + posX) + "px", bottom: (-100 + posY) + "px" });

        setStyle(".obj31", { right: (110 - posX) + "px", bottom: (-70 - posY) + "px" });
        setStyle(".obj32", { left: (100 - posX) + "px", bottom: (-160 - posY) + "px" });

        setStyle(".obj41", { left: (350 + posX) + "px", bottom: (-150 - posY) + "px" });
        setStyle(".obj42", { right: (170 + posX) + "px", top: (-260 - posY) + "px" });
        setStyle(".obj43", { right: (-100 + posX) + "px", bottom: (-120 + posY) + "px" });

        setStyle(".obj51", { left: (-100 - posX) + "px", bottom: (-300 - posY) + "px" });
        setStyle(".obj52", { right: (30 + posX) + "px", top: (180 - posY) + "px" });
        setStyle(".obj53", { left: (-30 + posX) + "px", bottom: (0 - posY) + "px" });

    });




});

