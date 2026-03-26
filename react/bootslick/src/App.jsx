import React, { useState } from "react";
import Slider from "react-slick";
import "bootstrap/dist/css/bootstrap.min.css";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import "./App.css";
import SwiperSlideComponent from "./pages/SwiperSlide";

export default function App() {
  const [currentSlide, setCurrentSlide] = useState(0);

  const images = [
    {
      id: 1,
      src: "./images/c1.jpg",
      alt: "Image 1",
    },
    {
      id: 2,
      src: "./images/c2.jpg",
      alt: "Image 2",
    },
    {
      id: 3,
      src: "./images/c3.jpg",
      alt: "Image 3",
    },
    {
      id: 4,
      src: "./images/c4.jpg",
      alt: "Image 4",
    },
    {
      id: 5,
      src: "./images/c5.jpg",
      alt: "Image 5",
    },
    {
      id: 6,
      src: "./images/c6.jpg",
      alt: "Image 6",
    },
  ];
  const settings = {
    dots: true, //pager
    infinite: true, //무한반복
    speed: 500, //속도
    slidesToShow: 3, //한 화면에 3개
    slidesToScroll: 1, //1장씩 넘김
    autoplay: true, //자동 넘김
    autoplaySpeed: 3000, //자동 넘김 간격(3초)
    beforeChange: (current, next) => setCurrentSlide(next), //상태업데이트
    //다음슬라이드 인덱스를 setCurrentSlide(next) 업데이트
    arrows: true, //좌우화살표
    fade: false,
    responsive: [
      {
        breakpoint: 992,
        settings: {
          slidesToShow: 2,
        },
      },
      {
        breakpoint: 768,
        settings: {
          slidesToShow: 1,
        },
      },
    ],
  };

  return (
    <>
      <div className="container mt-5">
        <div className="row justify-content-center">
          <div className="col-md-10">
            <h2 className="text-center mb-4">이미지 슬라이더</h2>
            <div className="slider-wrapper">
              <Slider {...settings}>
                {/* Slide 안에  image.map(돌려 이미지 생성)  */}
                {images.map((image) => (
                  <div key={image.id} className="slide-item">
                    <div className="px-2">
                      <img
                        src={image.src}
                        alt={image.alt}
                        className="img-fluid w-100"
                        style={{ height: "400px", objectFit: "cover" }}
                      />
                      <div className="slider-caption mt-2">
                        <h6>{image.alt}</h6>
                      </div>
                    </div>
                  </div>
                ))}
              </Slider>
            </div>
            <div className="text-center mt-3">
              <p className="text-muted">
                현재 이미지: {currentSlide + 1} / {images.length}
              </p>
            </div>
          </div>
        </div>
      </div>
      <SwiperSlideComponent />
    </>
  );
}
