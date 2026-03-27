import React from 'react'
import { useState, useEffect } from "react";
import "./Slider.css";


function Slider() {
  const [currentSlide, setCurrentSlide] = useState(0);
  const slides = [
    {
      id: 0,
      title: "첫 번째 슬라이드",
      description: "이것은 첫 번째 슬라이드입니다.",
      image: "/images/slide1.jpg",
    },
    {
      id: 1,
      title: "두 번째 슬라이드",
      description: "이것은 두 번째 슬라이드입니다.",
      image: "/images/slide2.jpg",
    },
    {
      id: 2,
      title: "세 번째 슬라이드",
      description: "이것은 세 번째 슬라이드입니다.",
      image: "/images/slide3.jpg",
    },
  ];

  // 자동 슬라이드 기능
  useEffect(()=>{
    const interval = setInterval(()=>{
      setCurrentSlide((prev) => (prev + 1) % slides.length)
    },4000);
    return () => clearInterval(interval)
    //상태가 변할때마다 다시 그려짐 / 청소 안해주면 4초 슬라이드가 계속 생김
    //메모리누수
  },[slides.length])
  //의존성 배열 : 슬라이드 계수가 변경될 때 타이머 새로 설정

  //이전 슬라이드 : 0에서 -1하면 음수가 되지만, 길이를 더해주면 마지막 번호로 보냄
  const goToPrevious = () => {
    setCurrentSlide((prev) => (prev - 1 + slides.length) % slides.length);
  };

  //다음 슬라이드 마지막번호(2)에서 +1을 더하면, 3 % 3 = 0 다시 처음으로 돌아감
  const goToNext = () => {
    setCurrentSlide((prev) => (prev + 1) % slides.length);
  };

  const goToSlide = (index) => {
    setCurrentSlide(index);
  };

  return (
    <div className='slider-container'>
      <h2>슬라이드</h2>
      <div className="slider-wrapper">
        <div className="slider-track" style={{transform:`translateX(-${currentSlide * 100}%)`}}>
          {slides.map((slide)=>(
          <div key={slide.id} className="slide">
            <img src={slide.image} alt={slide.title} className='slide-image' />
            <div className="slide-content">
              <h3>{slide.title}</h3>
              <p>{slide.description}</p>
            </div>
          </div>
          ))}
        </div>
        <button className="slider-button prev" onClick={goToPrevious}> &lt; </button>
        <button className="slider-button next" onClick={goToNext}> &gt; </button>
        <div className="slider-dots">
          {slides.map((_,index)=>(
          <button
            key={index}
            className={`dot ${currentSlide === index ? "active" : ""}`}
            onClick={()=>goToSlide(index)}
            aria-label={`슬라이드 ${index + 1}로 이동`}
          />
          ))}
        </div>
      </div>
    </div>
  )
}

export default Slider