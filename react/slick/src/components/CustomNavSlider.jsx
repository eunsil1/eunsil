import React from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

const CustomNavSlider = () => {
  const settings = {
    dots: true,
    arrows: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 3000,
    pauseOnHover: true,
    className: "custom-nav-slider",
  };

  const slides = [
    { id: 1, text: "Custom 1", image: "/images/slide1.jpg" },
    { id: 2, text: "Custom 2", image: "/images/slide2.jpg" },
    { id: 3, text: "Custom 3", image: "/images/slide3.jpg" },
    { id: 4, text: "Custom 4", image: "/images/pic_1.jpg" },
  ];

  return (
    <Slider {...settings}>
      {slides.map((slide) => (
        <div key={slide.id} className="slide">
          <div
            className="slide-content"
            style={{
              backgroundImage: `url(${slide.image})`,
              backgroundSize: "cover",
              backgroundPosition: "center",
            }}
          >
            <h3>{slide.text}</h3>
          </div>
        </div>
      ))}
    </Slider>
  );
};

export default CustomNavSlider;
